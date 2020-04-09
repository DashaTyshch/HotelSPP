import React, { useState } from "react";
import connect from "react-redux/es/connect/connect";
import {Button, TextField} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Grid from "@material-ui/core/Grid";

import { getToken } from "../../store/actions";
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

function NewRoomContainer(props) {
    const [roomType, setRoomType] = useState("");
    const [description, setDescription] = useState("");
    const [places, setPlaces] = useState(1);
    const [price, setPrice] = useState(0);
    const [amount, setAmount] = useState(1);

    const handleSubmit = e => {
        e.preventDefault();
        fetch("/api/room_type/create", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
                'Authorization': `Bearer ${getToken()}`
            },
            body: JSON.stringify({name: roomType, description, places, price, amount})
        })
            .then(response => response.text())
            .then(data => {
                alert(data);
            })

            .catch(err => {
                console.log("fetch error" + err);
            });
    };

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

export default connect(mapStateToProps, mapDispatchToProps)(NewRoomContainer);