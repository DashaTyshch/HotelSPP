import React from "react";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Carousel from 'react-material-ui-carousel';
import Paper from "@material-ui/core/Paper";
import GridList from "@material-ui/core/GridList";
import GridListTile from "@material-ui/core/GridListTile";

const useStyles = makeStyles({
    root: {
        maxWidth: 345,
        boxShadow: "0px 2px 1px -1px rgba(0,0,0,0.2), 0px 1px 1px 0px rgba(0,0,0,0.14)," +
            " 0px 1px 3px 0px rgba(0,0,0,0.12)",
        borderRadius: "4px",
        backgroundColor: "#fff",
    },
    actionButton: {
        width: "100%",
        display: "block",
        textAlign: "inherit",
        color: "inherit",
        border: 0,
        cursor: "pointer",
        margin: "0",
        outline: 0,
        padding: 0,
        position: "relative",
        alignItems: "center",
        userSelect: "none",
        borderRadius: 0,
        verticalAlign: "middle",
        mozAppearance: "none",
        justifyContent: "center",
        textDecoration: "none",
        backgroundColor: "transparent",
        webkitAppearance: "none",
        webkitTapHighlightColor: "transparent",
    },
    img: {
        objectFit: "cover",
        width: "100%",
        display: "block",
        backgroundSize: "cover",
        backgroundRepeat: "no-repeat",
        backgroundPosition: "center",
        height: "300px",

    },
    gridList : {
        height: "auto",
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'center',
        alignItems: "center",
    },
    gridListTile: {
        margin: "30px",
    }
});

export default function RoomCard(props) {
    const classes = useStyles();
    const elements = [1,2, 3, 4, 5, 6];
    return (
        <>
            <div className={classes.gridList}>
                {elements.map((value, index) => {
                    return <div key={index} className={classes.gridListTile}>
                        <div  className={classes.root}>
                            <button className={classes.actionButton}>
                                <Carousel interval={8000}>
                                    <Paper>
                                        <img src={"../img/img26.jpg"} alt={"room"} className={classes.img}/>
                                    </Paper>
                                    <Paper>
                                        <img src={"../img/img27.jpg"} alt={"room"} className={classes.img}/>
                                    </Paper>
                                    <Paper>
                                        <img src={"../img/img25.jpg"} alt={"room"} className={classes.img}/>
                                    </Paper>
                                </Carousel>
                                <div style={{padding: "16px", textAlign: "center"}}>
                                    <h2>
                                        Люкс
                                    </h2>
                                    <h3>
                                        Спальних місць: 4
                                    </h3>
                                    <h3>
                                        UAH 900
                                    </h3>
                                    <h4>
                                        К-ть вільних: 3
                                    </h4>
                                </div>
                            </button>
                        </div>
                    </div>
                })}
            </div>

        </>
    );
}