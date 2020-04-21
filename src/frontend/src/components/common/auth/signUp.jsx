import React, {useState} from "react";
import Typography from "@material-ui/core/Typography";
import {Button, TextField} from "@material-ui/core";
import {useStyles} from "./authStyles";
import Alert from '@material-ui/lab/Alert';

export default function SignUp(props){
    const [phone, setPhone] = useState("");
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const fetchSignUp = () => fetch("/api/auth/signUp", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            Accept: 'application/json',
        },
        body: JSON.stringify({phone, name, surname, password, email})
    })
        .then(function (res) {
            if (res.ok) {
                return res.text();
            } else {
                return res.text()
                    .then(function (err) {
                        throw new Error(err);
                    });
            }
        });

    const handleSubmit = e => {
        e.preventDefault();
        setError("");
        fetchSignUp()
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
                Зареєструйтеся у системі
            </Typography>
            <div className={useStyles().paper}>
                <form className={useStyles().form} onSubmit={handleSubmit} autocomplete="off">
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
                        autoComplete='off'
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
                        autoComplete='off'
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