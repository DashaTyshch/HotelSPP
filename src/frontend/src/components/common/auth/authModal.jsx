import React, {useState} from "react";
import { connect } from 'react-redux';
import { withRouter } from "react-router";
import {Dialog, DialogTitle} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";

import {setLoginOpen} from "../../../store/actions";
import Login from "./login.jsx";
import SignUp from "./signUp.jsx";

const useStyles = makeStyles(theme => ({
    title: {
        textAlign: "center",
        color: theme.palette.turquoise.backgroundColor,
        letterSpacing: "0.009em",
        fontWeight: "bold",
    },
}));

function AuthModal(props) {
    const [isLogin, setIsLogin] = useState(true);

    return(
        <Dialog onClose={() => props.onSetLoginOpen(false)}  open={props.open}>
            <DialogTitle className={useStyles().title}>HotelSPP</DialogTitle>
            {isLogin
                ? <Login goToSignUp={() => setIsLogin(false)}/>
                : <SignUp goToLogin={() => setIsLogin(true)}/>}
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

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(AuthModal));