import React from "react";
import {Redirect, Route} from "react-router-dom";
import { withRouter } from "react-router";
import {connect} from "react-redux";

const PrivateRoute = ({ component: Component, user, role, ...rest }) => (
    <Route {...rest} render={(props) => (
        user != null && user != undefined
            ? user.role == role ? <Component {...props} /> : <Redirect to='/' />
            : <Redirect to='/' />
    )} />
);

const mapStateToProps = (state) => {
    return {
        user: state.user
    };
};
const mapDispatchToProps = (dispatch) => {
    return {
    };
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(PrivateRoute));