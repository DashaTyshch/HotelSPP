import React, {useEffect} from "react";
import { withRouter } from "react-router";

import {makeStyles} from '@material-ui/core/styles';
import Header from "./header.jsx";
import LoginModal from "./auth/authModal.jsx";
import {Footer} from "./footer.jsx";
import {fetchUserInfo} from "../../store/actions";
import {connect} from "react-redux";
import Main from "./main.jsx";

const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
        flexDirection: 'column',
        minHeight: '100vh',
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
                <Main/>

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

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(AppContainer));