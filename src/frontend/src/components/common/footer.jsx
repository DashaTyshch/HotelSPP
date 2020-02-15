import React from 'react';
import { makeStyles, ThemeProvider } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles(theme => ({
    footer: {
        padding: theme.spacing(2),
        marginTop: 'auto',
        backgroundColor: theme.palette.secondary.main,
        color: theme.palette.secondary.contrastText
    },
}));

export const Footer = props => {

    return(
        <footer className={useStyles().footer}>
            <Container maxWidth="sm">
                <Typography variant="body2" align="center">
                    {'Copyright © 2020 HotelSPP.'}
                </Typography>
            </Container>
        </footer>
    );
};