import React, {useEffect, useState} from "react";
import { useParams } from "react-router-dom";

import styled from 'styled-components';
import Carousel from '@brainhubeu/react-carousel';
import {Button, TextField} from "@material-ui/core";
import Alert from "@material-ui/lab/Alert";
import makeStyles from "@material-ui/core/styles/makeStyles";
import {getToken} from "../../store/actions";

const useStyles = makeStyles(theme => ({
    form: {
        width: '100%',
        alignItems: 'center',
        display: 'flex',
        flexDirection: 'column',
        '& label.Mui-focused': {
            color: theme.palette.turquoise.backgroundColor,
        },
        '& .MuiInput-underline:after': {
            borderBottomColor: theme.palette.turquoise.backgroundColor,
        },
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
        backgroundColor: theme.palette.blue.backgroundColor,
        color: theme.palette.blue.color,
        '&:hover': {
            backgroundColor: theme.palette.lightBlue.backgroundColor,
            color: theme.palette.lightBlue.color
        },
    },
}));

const Title = styled.h1`
  text-align: center;
  color: #31708E;
  letter-spacing: 0.05em;
`;

const Image = styled.img`
    width: 100%;
`;

const InfoContainer = styled.div`
    margin-top: 15px;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
    align-items: flex-start;
    width: 100%;
`;

const InfoColumn = styled.div`
    display: flex;
    flex-direction: column;
    font-size: large;
`;

const Label = styled.span`
    font-weight: 500;
    color: #2C3531;
`;

const Info = styled.span`
    color: #5085A5;
    font-weight: 700;
    font-size: larger;
`;

const BookingContainer = styled.div`
    box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.4);
    border-radius: 5px;
    width: 400px;
    padding: 10px;
    background-color: #D1E8E2;
    display: flex;
    flexDirection: column;
    alignItems: center;
`;

export default function RoomTypeContainer(props) {
    let { id } = useParams();
    const styles = useStyles();
    const dates = JSON.parse(localStorage.getItem('dates'));
    const [roomType, setRoomType] = useState(null);
    const [comment, setComment] = useState("");
    const [message, setMessage] = useState("");

    useEffect( () => {
        fetch(`/api/room_type/get?name=${id}`, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                setRoomType(data);
            })
            .catch(err => {
                console.log("fetch error" + err);
            });
    }, []);

    const calcTotalPrice = () => {
        const start = new Date(new Date(dates[0]).toDateString());
        const end = new Date(new Date(dates[1]).toDateString());
        return (end.getTime() - start.getTime()) / (60*60*1000*24) * roomType.price;
    };

    const handleSubmit = e => {
        e.preventDefault();
        fetch(`/api/order/create`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
                'Authorization': `Bearer ${getToken()}`,
            },
            body: JSON.stringify([{
                start_date: dates[0],
                end_date: dates[1],
                price: roomType.price,
                period_price: calcTotalPrice(),
                comment,
                room_type_id: roomType.id}])
        })
            .then(response => response.json())
            .then(data => {
                setMessage('Ваше бронювання створене!');
            })
            .catch(err => {
                console.log("fetch error" + err);
            });
    };

    return (
        <>
            {roomType !== null &&
                <>
                    <Title>{roomType.name}</Title>

                    {roomType.images.length > 0 &&
                        <Carousel autoPlay={7000} animationSpeed={2000} offset={15} stopAutoPlayOnHover
                                  slidesPerPage={roomType.images.length < 3? roomType.images.length : 3}
                                  arrows centered itemWidth={500}
                                  clickToChange
                                  breakpoints={{
                                      640: {
                                          slidesPerPage: 1,
                                          arrows: false,
                                          itemWidth: 300
                                      },
                                      900: {
                                          slidesPerPage: roomType.images.length < 2? 1 : 2,
                                          arrows: false,
                                          itemWidth: 400
                                      }
                                  }}>
                            {roomType.images.map((image) =>{
                                return <Image src={image.image} alt=""/>
                            })}
                        </Carousel>
                    }
                    <InfoContainer>
                        <InfoColumn>
                            <h4>{roomType.description}</h4>
                            <div><Label>Кількість спальних місць: </Label><Info>{roomType.places}</Info></div>
                            <div><Label>Ціна за добу: </Label><Info>{roomType.price}UAH</Info></div>
                            <div><Label>Загальна ціна: </Label><Info>{calcTotalPrice()}UAH</Info></div>
                        </InfoColumn>

                        <InfoColumn>
                            <BookingContainer>
                                <form className={styles.form} onSubmit={handleSubmit}>
                                    <div><Label>Дата заїзду: {new Date(dates[0]).toDateString()}</Label></div>
                                    <div><Label>Дата виїзду: {new Date(dates[1]).toDateString()}</Label></div>
                                    <TextField size="normal" margin="normal"
                                               InputProps={{
                                                   style: {background: "#FFFFFF",
                                                            boxShadow: "4px 4px 10px rgba(0, 0, 0, 0.4)",
                                                            borderRadius: "5px"},
                                                }}
                                               id="comment"
                                               label="Коментар"
                                               multiline
                                               rows="8"
                                               cols="8"
                                               variant="outlined"
                                               value={comment}
                                               onChange={(e) => setComment(e.target.value)}
                                    />
                                    {message !== "" &&
                                        <Alert variant="outlined" severity="success" onClose={() => setMessage("")}>{message}</Alert>
                                    }

                                    <Button color="secondary" className={styles.submit}
                                        type="submit"
                                        fullWidth
                                        variant="contained">
                                        Додати до бронювання
                                    </Button>
                                </form>
                            </BookingContainer>
                        </InfoColumn>
                    </InfoContainer>
                </>
            }
        </>
    );
}