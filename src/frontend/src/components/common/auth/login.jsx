import React, {useState} from "react";
import Typography from "@material-ui/core/Typography";
import {Button, TextField} from "@material-ui/core";
import {userLoginFetch} from "../../../store/actions";
import {withRouter} from "react-router";
import {connect} from "react-redux";
import {useStyles} from "./authStyles";

function Login(props){
    const [phone, setPhone] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = e => {
        e.preventDefault();
        props.userLoginFetch(phone, password);
    };

    return (
        <>
            <Typography variant="body1" align="center">
                Ввійдіть у систему
            </Typography>
            <div className={useStyles().paper}>
                <form className={useStyles().form} noValidate onSubmit={handleSubmit}>
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
                        value={phone}
                        onChange={(e) => setPhone(e.target.value)}
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
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
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
                        <span onClick={props.goToSignUp}>Зареєструватися</span>
                    </div>
                </form>
            </div>
        </>
    )
}

const mapStateToProps = (state) => {
    return {
    };
};
const mapDispatchToProps = (dispatch) => {
    return {
        userLoginFetch: (phone, pwd) => dispatch(userLoginFetch(phone, pwd))
    };
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Login));