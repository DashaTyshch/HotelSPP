import React from "react";
import { Provider } from 'react-redux'
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { HashRouter  } from 'react-router-dom';

import {ThemeProvider} from '@material-ui/core/styles';

import {colorTheme} from "./themes/colors.jsx";
import AppContainer from "./common/appContainer.jsx";

import reducer from "../store/reducer";

const store = createStore(reducer, applyMiddleware(thunk));

function App(props) {

    return (
        <HashRouter >
            <Provider store={store}>
                <ThemeProvider theme={colorTheme}>
                    <AppContainer/>
                </ThemeProvider>
            </Provider>
        </HashRouter >
    );
}

export default App;