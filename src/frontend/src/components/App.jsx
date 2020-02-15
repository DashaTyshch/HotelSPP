import React from "react";
import {makeStyles, ThemeProvider} from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';

import {Footer} from "./common/footer.jsx";
import {Header} from "./common/header.jsx";
import {colorTheme} from "./themes/colors.jsx";

const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
        flexDirection: 'column',
        minHeight: '100vh',
    },
    main: {
        marginTop: theme.spacing(8),
        marginBottom: theme.spacing(2),
    },
}));

function App(props) {

    return (
        <ThemeProvider theme={colorTheme}>
            <div className={useStyles().root}>
                <Header/>
                <Container component="main" className={useStyles().main} maxWidth="sm">
                    <h1>My React App!</h1>
                </Container>


                <Footer/>
            </div>
        </ThemeProvider>
    );
}

export default App;