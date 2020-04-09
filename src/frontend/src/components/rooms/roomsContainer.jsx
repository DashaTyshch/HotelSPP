import React, { useEffect, useState } from "react";
import RoomCards from "./roomCard.jsx";
import connect from "react-redux/es/connect/connect";
import {getToken} from "../../store/actions";

function RoomsContainer(props) {
    const [rooms, setRooms] = useState(null);

    useEffect( () => {
        fetch("/api/room_type/all", {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                setRooms(data);
            })
            .catch(err => {
                console.log("fetch error" + err);
            });

    }, []);

    return (
        <>
            {rooms !== null &&
                <RoomCards rooms={rooms}/>
            }
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