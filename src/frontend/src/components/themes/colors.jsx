import React from 'react';
import { createMuiTheme } from '@material-ui/core/styles';

export const colorTheme = createMuiTheme({
    palette: {
        primary: {
            main: "#D1E8E2",
            contrastText: "#000",
        },
        secondary: {
            main: "#2C3531",
            contrastText: "#fff",

        },
        turquoise: {
            backgroundColor: '#31708E',
            color: '#fff',
        },
        beige: {
            backgroundColor: "#D9B08C",
            color: "#000"
        },
        lightBeige: {
            backgroundColor: "#FFCB9A",
            color: "#000"
        },
        blue: {
            backgroundColor: "#5085A5",
            color: "#fff"
        },
        lightBlue: {
            backgroundColor: "#8FC1E3",
            color: "#fff"
        }
    },
});