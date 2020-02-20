import React, {useEffect} from "react";

import Container from '@material-ui/core/Container';
import {makeStyles} from '@material-ui/core/styles';
import Header from "./header.jsx";
import LoginModal from "./loginModal.jsx";
import {Footer} from "./footer.jsx";
import RoomCard from "../room/roomCard.jsx";
import {fetchUserInfo} from "../../store/actions";
import {connect} from "react-redux";

const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
        flexDirection: 'column',
        minHeight: '100vh',
    },
    main: {
        marginTop: theme.spacing(8),
        marginBottom: theme.spacing(2),
        maxWidth: '100%'
    },
}));

function AppContainer(props) {

    useEffect( () => {
        props.fetchUserInfo();
    }, []);

    return (
        <>
            <div className={useStyles().root}>
                <Header/>
                <Container component="main" className={useStyles().main} maxWidth="sm">
                    <RoomCard/>
                </Container>

                <Footer/>
            </div>
            <LoginModal/>
        </>
    );
}

const mapStateToProps = (state) => {
    return {
    };
};
const mapDispatchToProps = (dispatch) => {
    return {
        fetchUserInfo: () => dispatch(fetchUserInfo())
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(AppContainer);