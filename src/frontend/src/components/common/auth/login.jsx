import React, {useState} from "react";
import Typography from "@material-ui/core/Typography";
import {Button, TextField} from "@material-ui/core";
import {useStyles} from "./authStyles";
import Alert from '@material-ui/lab/Alert';

export default function Login(props){
    const [phone, setPhone] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const fetchLogin = () => fetch("/api/auth/signIn", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            Accept: 'application/json',
        },
        body: JSON.stringify({login: phone, password: password})
    })
        .then(function (res) {
            if (res.ok) {
                return res.text();
            } else {
                return res.json()
                    .then(function (err) {
                        throw new Error(err.message);
                    });
            }
        });

    const handleSubmit = e => {
        e.preventDefault();
        setError("");
        fetchLogin()
            .then(data => {
                localStorage.setItem("token", data);
                location.reload();
            })
            .catch(err => {
                setPassword("");
                setError(err.message);
            });
    };

    return (
        <>
            <Typography variant="body1" align="center">
                Ввійдіть у систему
            </Typography>
            <div className={useStyles().paper}>
                <form className={useStyles().form} onSubmit={handleSubmit}>
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

                    {error !== "" &&
                        <Alert variant="outlined" severity="error" onClose={() => setError("")}>{error}</Alert>
                    }

                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        className={useStyles().submit}>
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