import React, {useEffect} from "react";
import { Switch, Route } from 'react-router-dom';

import Container from "@material-ui/core/Container/Container";
import RoomsContainer from "../rooms/roomsContainer.jsx";
import NewRoomContainer from "../rooms/newRoomContainer.jsx";
import connect from "react-redux/es/connect/connect";
import makeStyles from "@material-ui/core/styles/makeStyles";

const useStyles = makeStyles(theme => ({
    main: {
        marginTop: theme.spacing(8),
        marginBottom: theme.spacing(2),
        maxWidth: '100%'
    },
}));

function Main(props) {


    return (
        <>
            <Container component="main" className={useStyles().main} maxWidth="sm">
                <Switch>
                    <Route exact path='/' component={RoomsContainer}/>
                    <Route path='/newRoom' component={NewRoomContainer}/>
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

export default connect(mapStateToProps, mapDispatchToProps)(Main);