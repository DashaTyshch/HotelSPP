import React from 'react';
import { connect } from 'react-redux';
import { withRouter } from "react-router";

import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';

import {setLoginOpen, logOut} from "../../store/actions";

const useStyles = makeStyles(theme => ({
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title: {
        flexGrow: 1,
    },
}));

const Header = props => {

    return (
        <AppBar position="static" color="secondary">
            <Toolbar>
                <Typography variant="h6" className={useStyles().title}>
                    HotelSPP
                </Typography>
                {props.user == null ?
                    <Button color="inherit" onClick={() => props.onSetLoginOpen(true)}>Ввійти</Button>
                    : <Button color="inherit" onClick={props.logOut}>Вийти</Button>
                }
            </Toolbar>
        </AppBar>
    );
};

const mapStateToProps = (state) => {
    return {
        user: state.user
    };
};
const mapDispatchToProps = (dispatch) => {
    return {
        onSetLoginOpen: (isOpen) => dispatch(setLoginOpen(isOpen)),
        logOut: () => dispatch(logOut()),
    };
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Header));