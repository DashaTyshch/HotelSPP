import React, { useState, useCallback } from "react";
import connect from "react-redux/es/connect/connect";
import { withRouter } from "react-router";
import {Button, TextField, Snackbar} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Grid from "@material-ui/core/Grid";
import {useDropzone} from 'react-dropzone';
import Alert from '@material-ui/lab/Alert';

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

const LinkToRoom = styled.span`
    cursor: pointer;
    color: #5085A5;
    font-weight: bold;
`;

function NewRoomContainer(props) {
    const [roomType, setRoomType] = useState("");
    const [description, setDescription] = useState("");
    const [places, setPlaces] = useState(1);
    const [price, setPrice] = useState(0);
    const [amount, setAmount] = useState(1);
    const [images, setImages] = useState([]);
    const [message, setMessage] = useState(null);

    const fetchCreateRoom = () => fetch("/api/room_type/create", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            Accept: 'application/json',
            'Authorization': `Bearer ${getToken()}`
        },
        body: JSON.stringify({name: roomType, description, places, price, amount, images})
    }).then(function (res) {
        if (res.ok) {
            return res.text();
        } else {
            return res.text()
                .then(function (err) {
                    throw new Error(err);
                });
        }
    });

    const redirectToRoomPage = (room) => {
        window.location.href = `#/roomType/${room}`;
    };

    const handleSubmit = e => {
        e.preventDefault();
        fetchCreateRoom()
            .then(data => {
                setMessage({text: data, severity: 'success'});
            })
            .catch(err => setMessage({text: err.message, severity: 'error'}));
    };

    function readFileAsync(file) {
        return new Promise((resolve, reject) => {
            let reader = new FileReader();
            reader.onload = () => {
                resolve(reader.result);
            };
            reader.onerror = reject;
            reader.readAsDataURL(file);
        })
    }

    const onDrop = useCallback(acceptedFiles => {
        const promises = acceptedFiles.map(readFileAsync);
        Promise.all(promises)
            .then(results => setImages([...images, ...results]));
    }, [images]);
    const {getRootProps, getInputProps} = useDropzone({onDrop});

    return (
        <>
            <div>
                <form className={useStyles().form} autoComplete="off" onSubmit={handleSubmit}>
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
                                variant="contained"
                                color="secondary"
                                className={useStyles().button}>
                                Скасувати
                            </Button>

                            <Button
                                type="submit"
                                variant="contained"
                                className={`${useStyles().button} ${useStyles().confirmBtn}`}>
                                Створити
                            </Button>
                        </div>
                    </Grid>
                </form>
            </div>
            {message != null &&
                <Snackbar
                    open={true}
                    onClose={() => setMessage(null)}
                    anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
                    autoHideDuration={6000}>
                    <Alert onClose={() => setMessage(null)} severity={message.severity}>
                        {message.severity === 'error'
                            ? message.text
                            :  <><span>Створено! Щоб переглянути номер, натисніть </span><LinkToRoom onClick={()=>redirectToRoomPage(message.text)}>тут</LinkToRoom></>
                        }
                    </Alert>
                </Snackbar>
            }
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