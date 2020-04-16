import React, { useState, useCallback } from "react";
import connect from "react-redux/es/connect/connect";
import { withRouter } from "react-router";
import {Button, TextField} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Grid from "@material-ui/core/Grid";
import {useDropzone} from 'react-dropzone';

import { getToken } from "../../store/actions";
import styled from 'styled-components';
import Paper from "@material-ui/core/Paper";
import Carousel from "react-material-ui-carousel";

const useStyles = makeStyles(theme => ({
    form: {
        margin: '0 auto',
        width: '90%',
        '& label.Mui-focused': {
            color: theme.palette.turquoise.backgroundColor,
        },
        '& .MuiInput-underline:after': {
            borderBottomColor: theme.palette.turquoise.backgroundColor,
        },
    },
    infoBlock: {
        display: "flex",
        flexWrap: "wrap",
        justifyContent: "space-evenly",
        alignItems: "flex-start",
        width: "100%",
    },
    infoBlockCol: {
        display: "flex",
        flexDirection: "column",
    },
    button: {
        margin: theme.spacing(3),
        '&:hover': {
            backgroundColor: theme.palette.lightBlue.backgroundColor,
            color: theme.palette.blue.color
        },
    },
    confirmBtn: {
        backgroundColor: theme.palette.blue.backgroundColor,
        color: theme.palette.blue.color,
    },
}));

const Image = styled.img`
    object-fit: cover;
    width: 100%;
    display: block;
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    max-height: 300px;
`;

function NewRoomContainer(props) {
    const [roomType, setRoomType] = useState("");
    const [description, setDescription] = useState("");
    const [places, setPlaces] = useState(1);
    const [price, setPrice] = useState(0);
    const [amount, setAmount] = useState(1);
    const [images, setImages] = useState([]);

    const handleSubmit = e => {
        e.preventDefault();

        fetch("/api/room_type/create", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
                'Authorization': `Bearer ${getToken()}`
            },
            body: JSON.stringify({name: roomType, description, places, price, amount, images})
        })
            .then(response => response.text())
            .then(data => {
                alert(data);
            })

            .catch(err => {
                console.log("fetch error" + err);
            });
    };

    const onDrop = useCallback(acceptedFiles => {
        acceptedFiles.forEach((file) => {
            const reader = new FileReader();

            reader.onabort = () => console.log('file reading was aborted');
            reader.onerror = () => console.log('file reading has failed');
            reader.onload = () => {
                const result = reader.result;
                setImages([...images, result]);
            };
            reader.readAsDataURL(file);
        })
    }, [images]);
    const {getRootProps, getInputProps} = useDropzone({onDrop});

    return (
        <>
            <div>
                <form className={useStyles().form} noValidate autoComplete="off" onSubmit={handleSubmit}>
                    <Grid container justify="center"
                          alignItems="center" direction="column">
                        <TextField size="normal" margin="normal" required
                                   id="roomType"
                                   label="Тип номеру"
                                   name="roomType"
                                   autoComplete="off"
                                   autoFocus
                                   value={roomType}
                                   onChange={(e) => setRoomType(e.target.value)}
                        />

                        <div {...getRootProps()} onDrop={onDrop}>
                            <input {...getInputProps()} />

                            {images.length > 0 &&
                                <Carousel interval={9000}>
                                    {images.map((image) =>{
                                        return <>
                                            <Paper>
                                                <Image src={image} alt=""/>
                                            </Paper>
                                        </>
                                    })}
                                </Carousel>
                            }

                            <p>Перетягніть сюди фотографії, щоб завантажити</p>
                        </div>

                        <div className={useStyles().infoBlock}>
                            <div className={useStyles().infoBlockCol}>
                                <TextField size="normal" margin="normal"
                                           id="description"
                                           label="Опис"
                                           multiline
                                           rows="6"
                                           variant="outlined"
                                           value={description}
                                           onChange={(e) => setDescription(e.target.value)}
                                />
                                <TextField size="normal" margin="normal" required
                                           id="places"
                                           label="Кількість спальних місць"
                                           type="number"
                                           InputProps={{ inputProps: { min: 1} }}
                                           InputLabelProps={{
                                               shrink: true,
                                           }}
                                           value={places}
                                           onChange={(e) => setPlaces(parseInt(e.target.value))}/>

                                <TextField size="normal" margin="normal" required
                                           id="price"
                                           variant="outlined"
                                           label="Ціна за добу"
                                           type="number"
                                           value={price}
                                           onChange={(e) => setPrice(parseInt(e.target.value))}/>
                            </div>

                            <TextField size="normal" margin="normal" required
                                       id="amount"
                                       label="Загальна кількість номерів"
                                       type="number"
                                       InputLabelProps={{
                                           shrink: true,
                                       }}
                                       InputProps={{ inputProps: { min: 1} }}
                                       value={amount}
                                       onChange={(e) => setAmount(parseInt(e.target.value))}
                            />
                        </div>

                        <div>
                            <Button
                                //fullWidth
                                variant="contained"
                                color="secondary"
                                className={useStyles().button}
                            >
                                Скасувати
                            </Button>

                            <Button
                                type="submit"
                                //fullWidth
                                variant="contained"
                                className={`${useStyles().button} ${useStyles().confirmBtn}`}
                            >
                                Створити
                            </Button>
                        </div>
                    </Grid>
                </form>
            </div>
        </>
    );
}

const mapStateToProps = (state) => {
    return {
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
    };
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(NewRoomContainer));