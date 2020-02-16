import React from "react";
import { connect } from 'react-redux';

import {Dialog, DialogTitle, Button, TextField} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Typography from "@material-ui/core/Typography";

import {setLoginOpen} from "../../store/actions";

const useStyles = makeStyles(theme => ({
    paper: {
        margin: theme.spacing(0, 0, 3, 0),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
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
    signUp: {
        textAlign: "center",
        '& span:hover': {
            color: theme.palette.beige.backgroundColor,
            textDecoration: "underline",
            cursor: "pointer"
        },
    }
}));

function LoginModal(props) {

    return(
        <Dialog onClose={() => props.onSetLoginOpen(false)}  open={props.open}>
            <DialogTitle className={useStyles().title}>HotelSPP</DialogTitle>
            <Typography variant="body1" align="center">
                Ввійдіть у систему
            </Typography>
            <div className={useStyles().paper}>
                <form className={useStyles().form} noValidate>
                    <TextField
                        size="small"
                        margin="normal"
                        required
                        fullWidth
                        id="phone"
                        label="Номер телефону"
                        name="phone"
                        autoComplete="phone"
                        autoFocus
                    />
                    <TextField
                        size="small"
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Пароль"
                        type="password"
                        id="password"
                    />

                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        className={useStyles().submit}
                    >
                        Ввійти
                    </Button>
                    <div className={useStyles().signUp}>
                        {'Ще не створили акаунт? '}
                        <span>Зареєструватися</span>
                    </div>
                </form>
            </div>
        </Dialog>
    );
}

const mapStateToProps = (state) => {
    return {
        open: state.loginOpen,
    };
};
const mapDispatchToProps = (dispatch) => {
    return {
        onSetLoginOpen: (isOpen) => dispatch(setLoginOpen(isOpen)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(LoginModal);