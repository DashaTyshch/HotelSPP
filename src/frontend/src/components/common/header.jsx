import React from 'react';
import { makeStyles, ThemeProvider } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';

const useStyles = makeStyles(theme => ({
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title: {
        flexGrow: 1,
    },
}));

export const Header = props => {

    return (
        <AppBar position="static" color="secondary">
            <Toolbar>
                <Typography variant="h6" className={useStyles().title}>
                    HotelSPP
                </Typography>
                <Button color="inherit" onClick={props.openLogin}>Ввійти</Button>
            </Toolbar>
        </AppBar>
    );
};