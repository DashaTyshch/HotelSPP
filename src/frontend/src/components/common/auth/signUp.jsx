import React, {useState} from "react";
import Typography from "@material-ui/core/Typography";
import {Button, TextField} from "@material-ui/core";
import {userSignUpFetch} from "../../../store/actions";
import {withRouter} from "react-router";
import {connect} from "react-redux";
import {useStyles} from "./authStyles";

function SignUp(props){
    const [phone, setPhone] = useState("");
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = e => {
        e.preventDefault();
        props.userSignUpFetch({phone, name, surname, password, email});
    };

    return (
        <>
            <Typography variant="body1" align="center">
                Зареєструйтеся у системі
            </Typography>
            <div className={useStyles().paper}>
                <form className={useStyles().form} noValidate onSubmit={handleSubmit}>
                    <TextField
                        required
                        size="small"
                        margin="normal"
                        fullWidth
                        label="Ім'я"
                        autoFocus
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />
                    <TextField
                        required
                        size="small"
                        margin="normal"
                        fullWidth
                        label="Прізвище"
                        value={surname}
                        onChange={(e) => setSurname(e.target.value)}
                    />
                    <TextField
                        size="small"
                        margin="normal"
                        required
                        fullWidth
                        label="Номер телефону"
                        value={phone}
                        onChange={(e) => setPhone(e.target.value)}
                    />
                    <TextField
                        required
                        size="small"
                        margin="normal"
                        fullWidth
                        type="email"
                        label="Пошта"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <TextField
                        size="small"
                        margin="normal"
                        required
                        fullWidth
                        label="Пароль"
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />

                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        className={useStyles().submit}
                    >
                        Зареєструватися
                    </Button>
                    <div className={useStyles().signUp}>
                        {'Уже є акаунт акаунт? '}
                        <span onClick={props.goToLogin}>Увійти у систему</span>
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
        userSignUpFetch: (model) => dispatch(userSignUpFetch(model))
    };
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(SignUp));