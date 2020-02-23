import React from "react";
import connect from "react-redux/es/connect/connect";
import {Button, TextField} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";

const useStyles = makeStyles(theme => ({
    title: {
        textAlign: "center",
        color: theme.palette.turquoise.backgroundColor,
        letterSpacing: "0.009em",
        fontWeight: "bold",
    },
    form: {
        width: '80%',
        '& label.Mui-focused': {
            color: theme.palette.turquoise.backgroundColor,
        },
        '& .MuiInput-underline:after': {
            borderBottomColor: theme.palette.turquoise.backgroundColor,
        },
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
        backgroundColor: theme.palette.lightBlue.backgroundColor,
        color: theme.palette.lightBlue.color,
        '&:hover': {
            backgroundColor: theme.palette.blue.backgroundColor,
            color: theme.palette.blue.color
        },
    },
}));

function NewRoomContainer(props) {

    return (
        <>
            <div>
                <form className={useStyles().form} noValidate>
                    <TextField size="normal" margin="normal" required
                        //fullWidth
                        id="roomType"
                        label="Тип номеру"
                        name="roomType"
                        autoComplete="off"
                        autoFocus
                        //value={phone}
                        //onChange={(e) => setPhone(e.target.value)}
                    />
                    <TextField size="normal" margin="normal"
                        id="places"
                        label="Кількість спальних місць"
                        type="number"
                               InputProps={{ inputProps: { min: 1} }}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                    <TextField size="normal" margin="normal"
                        id="amount"
                        label="Загальна кількість номерів"
                        type="number"
                               InputProps={{ inputProps: { min: 1} }}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />

                    <TextField size="normal" margin="normal"
                        id="description"
                        label="Опис"
                        multiline
                        rows="6"
                        //defaultValue="Default Value"
                        variant="outlined"
                    />

                    <Button
                        //type="submit"
                        //fullWidth
                        variant="contained"
                        className={useStyles().submit}
                    >
                        Скасувати
                    </Button>
                    <Button
                        type="submit"
                        //fullWidth
                        variant="contained"
                        className={useStyles().submit}
                    >
                        Створити
                    </Button>
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