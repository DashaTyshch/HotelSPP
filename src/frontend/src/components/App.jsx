import React from "react";
import {makeStyles, ThemeProvider} from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';

import {Footer} from "./common/footer.jsx";
import {Header} from "./common/header.jsx";
import {colorTheme} from "./themes/colors.jsx";
import LoginModal from "./common/loginModal.jsx";

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
    const [open, setOpen] = React.useState(false);

    return (
        <ThemeProvider theme={colorTheme}>
            <div className={useStyles().root}>
                <Header openLogin={() => setOpen(true)}/>
                <Container component="main" className={useStyles().main} maxWidth="sm">
                    <h1>My React App!</h1>
                </Container>

                <Footer/>
            </div>

        <LoginModal open={open} close={() => setOpen(false)}/>
        </ThemeProvider>
    );
}

export default App;