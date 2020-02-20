import React from "react";
import { Provider } from 'react-redux'
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';

import {ThemeProvider} from '@material-ui/core/styles';

import {colorTheme} from "./themes/colors.jsx";
import AppContainer from "./common/appContainer.jsx";

import reducer from "../store/reducer";

const store = createStore(reducer, applyMiddleware(thunk));

function App(props) {

    return (
        <Provider store={store}>
            <ThemeProvider theme={colorTheme}>
                <AppContainer />
            </ThemeProvider>
        </Provider>
    );
}

export default App;