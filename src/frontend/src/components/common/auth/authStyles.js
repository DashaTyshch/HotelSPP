import makeStyles from "@material-ui/core/styles/makeStyles";

export const useStyles = makeStyles(theme => ({
    paper: {
        margin: theme.spacing(0, 0, 3, 0),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    form: {
        width: '80%',
        '& label.Mui-focused': {
            color: theme.palette.turquoise.backgroundColor,
        },
        '& .MuiInput-underline:after': {
            borderBottomColor: theme.palette.turquoise.backgroundColor,
        },
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
        backgroundColor: theme.palette.lightBlue.backgroundColor,
        color: theme.palette.lightBlue.color,
        '&:hover': {
            backgroundColor: theme.palette.blue.backgroundColor,
            color: theme.palette.blue.color
        },
    },
    signUp: {
        textAlign: "center",
        '& span:hover': {
            color: theme.palette.beige.backgroundColor,
            textDecoration: "underline",
            cursor: "pointer"
        },
    }
}));