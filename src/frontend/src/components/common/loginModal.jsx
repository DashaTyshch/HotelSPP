import React from "react";
import {Dialog, DialogTitle, Button, Grid, Link, TextField} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";

const useStyles = makeStyles(theme => ({
    paper: {
        margin: theme.spacing(3, 0),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    form: {
        width: '80%',
        marginTop: theme.spacing(1),
        '& label.Mui-focused': {
            color: theme.palette.secondary.main,
        },
        '& .MuiInput-underline:after': {
            borderBottomColor: theme.palette.turquoise,
        },
    },
        submit: {
        margin: theme.spacing(3, 0, 2),
    },
}));

export default function LoginModal(props) {

    return(
        <Dialog onClose={props.close}  open={props.open}>
            <DialogTitle style={{textAlign: "center"}}>Ввійдіть у систему</DialogTitle>
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
                        color="primary"
                        className={useStyles().submit}
                    >
                        Sign In
                    </Button>
                    <Grid container>
                        <Grid item xs>
                            <Link href="#" variant="body2">
                                Forgot password?
                            </Link>
                        </Grid>
                        <Grid item>
                            <Link href="#" variant="body2">
                                {"Don't have an account? Sign Up"}
                            </Link>
                        </Grid>
                    </Grid>
                </form>
            </div>
        </Dialog>
    );
};
