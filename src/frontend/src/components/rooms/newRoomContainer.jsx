import React from "react";
import connect from "react-redux/es/connect/connect";
import {Button, TextField} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Grid from "@material-ui/core/Grid";

const useStyles = makeStyles(theme => ({
    form: {
        margin: '0 auto',
        width: '90%',
        '& label.Mui-focused': {
            color: theme.palette.turquoise.backgroundColor,
        },
        '& .MuiInput-underline:after': {
            borderBottomColor: theme.palette.turquoise.backgroundColor,
        },
    },
    infoBlock: {
        display: "flex",
        flexWrap: "wrap",
        justifyContent: "space-evenly",
        alignItems: "flex-start",
        width: "100%",
    },
    infoBlockCol: {
        display: "flex",
        flexDirection: "column",
    },
    button: {
        margin: theme.spacing(3),
        '&:hover': {
            backgroundColor: theme.palette.lightBlue.backgroundColor,
            color: theme.palette.blue.color
        },
    },
    confirmBtn: {
        backgroundColor: theme.palette.blue.backgroundColor,
        color: theme.palette.blue.color,
        // '&:hover': {
        //     backgroundColor: theme.palette.lightBlue.backgroundColor,
        //     color: theme.palette.blue.color
        // },
    },
}));

function NewRoomContainer(props) {

    return (
        <>
            <div>
                <form className={useStyles().form} noValidate>
                    <Grid container justify="center"
                          alignItems="center" direction="column">
                        <TextField size="normal" margin="normal" required
                                   id="roomType"
                                   label="Тип номеру"
                                   name="roomType"
                                   autoComplete="off"
                                   autoFocus
                            //value={phone}
                            //onChange={(e) => setPhone(e.target.value)}
                        />

                        <div className={useStyles().infoBlock}>
                            <div className={useStyles().infoBlockCol}>
                                <TextField size="normal" margin="normal"
                                           id="description"
                                           label="Опис"
                                           multiline
                                           rows="6"
                                    //defaultValue="Default Value"
                                           variant="outlined"
                                />
                                <TextField size="normal" margin="normal" required
                                           id="places"
                                           label="Кількість спальних місць"
                                           type="number"
                                           InputProps={{ inputProps: { min: 1} }}
                                           InputLabelProps={{
                                               shrink: true,
                                           }}
                                />
                            </div>

                            <TextField size="normal" margin="normal" required
                                       id="amount"
                                       label="Загальна кількість номерів"
                                       type="number"
                                       InputProps={{ inputProps: { min: 1} }}
                                       InputLabelProps={{
                                           shrink: true,
                                       }}
                            />
                        </div>

                        <div>
                            <Button
                                //type="submit"
                                //fullWidth
                                variant="contained"
                                color="secondary"
                                className={useStyles().button}
                            >
                                Скасувати
                            </Button>

                            <Button
                                type="submit"
                                //fullWidth
                                variant="contained"
                                className={`${useStyles().button} ${useStyles().confirmBtn}`}
                            >
                                Створити
                            </Button>
                        </div>
                    </Grid>
                </form>
            </div>
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

export default connect(mapStateToProps, mapDispatchToProps)(NewRoomContainer);