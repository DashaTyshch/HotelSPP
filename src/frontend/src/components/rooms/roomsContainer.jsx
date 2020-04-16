import React, { useEffect, useState } from "react";
import { Container } from 'react-bootstrap';
import RoomCards from "./roomCard.jsx";
import RoomsMenu from './RoomsMenu.jsx';
import connect from "react-redux/es/connect/connect";

function RoomsContainer(props) {
    const [rooms, setRooms] = useState(null);
    const [filteredRooms, setFilteredRooms] = useState(null);

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
                setFilteredRooms(data);
            })
            .catch(err => {
                console.log("fetch error" + err);
            });

    }, []);

    return (
        <>
            <RoomsMenu />
            {filteredRooms !== null &&
                <RoomCards rooms={filteredRooms}/>
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