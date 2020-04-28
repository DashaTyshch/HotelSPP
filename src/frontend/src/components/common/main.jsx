import React from "react";
import { Switch, Route } from 'react-router-dom';
import { withRouter } from "react-router";

import { Container } from 'react-bootstrap';
import RoomsContainer from "../rooms/roomsContainer.jsx";
import NewRoomContainer from "../rooms/newRoomContainer.jsx";
import connect from "react-redux/es/connect/connect";
import makeStyles from "@material-ui/core/styles/makeStyles";
import RoomTypeContainer from "../rooms/roomTypeContainer.jsx";
import Profile from "../profile/profile.jsx";
import BookingsContainer from "../booking/bookingsContainer.jsx";
import PrivateRoute from "./privateRoute.jsx";
import {userRole} from "../../constants/enums";

const useStyles = makeStyles(theme => ({
    main: {
        marginTop: theme.spacing(2),
        marginBottom: theme.spacing(2),
        maxWidth: '96%'
    },
}));

function Main(props) {

    return (
        <>
            <Container className={useStyles().main}>
                <Switch>
                    <Route exact path='/' component={RoomsContainer}/>
                    <Route path='/roomType/:id' component={RoomTypeContainer}/>
                    <Route path='/newRoom' component={NewRoomContainer}/>
                    <PrivateRoute path='/bookings' component={BookingsContainer} role={userRole.HEAD}/>
                    <PrivateRoute path='/profile' component={Profile} role={userRole.USER}/>
                </Switch>
            </Container>
        </>
    );
}

const mapStateToProps = (state) => {
    return {
    };
};
const mapDispatchToProps = (dispatch) => {
    return {
    };
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Main));