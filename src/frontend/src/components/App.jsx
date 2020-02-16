import React from "react";
import { Provider } from 'react-redux'
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';

import {makeStyles, ThemeProvider} from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';

import {Footer} from "./common/footer.jsx";
import Header from "./common/header.jsx";
import {colorTheme} from "./themes/colors.jsx";
import LoginModal from "./common/loginModal.jsx";
import RoomCard from "./room/roomCard.jsx";

import reducer from "../store/reducer";

const store = createStore(reducer, applyMiddleware(thunk));

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

function App(props) {

    return (
        <Provider store={store}>
            <ThemeProvider theme={colorTheme}>
                <div className={useStyles().root}>
                    <Header/>
                    <Container component="main" className={useStyles().main} maxWidth="sm">
                        <RoomCard/>
                    </Container>

                    <Footer/>
                </div>
                <LoginModal/>
            </ThemeProvider>
        </Provider>
    );
}

export default App;