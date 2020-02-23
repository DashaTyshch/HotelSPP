import React from "react";
import RoomCard from "./roomCard.jsx";
import connect from "react-redux/es/connect/connect";

function RoomsContainer(props) {

    return (
        <>
            <RoomCard/>
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

export default connect(mapStateToProps, mapDispatchToProps)(RoomsContainer);