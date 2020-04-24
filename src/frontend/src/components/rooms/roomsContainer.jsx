import React, { useEffect, useState } from "react";
import RoomCards from "./roomCards.jsx";
import RoomsMenu from './RoomsMenu.jsx';
import connect from "react-redux/es/connect/connect";

function RoomsContainer(props) {
    const [rooms, setRooms] = useState(null);
    const [filteredRooms, setFilteredRooms] = useState(null);
    const [dates, setDates] = useState([new Date(), new Date().setDate(new Date().getDate() + 1)]);

    useEffect( () => {
        if(dates != undefined){
            localStorage.setItem('dates', JSON.stringify(dates));
            fetch(`/api/room_type/all_free_on_dates`, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                    Accept: 'application/json',
                },
                body: JSON.stringify({start: new Date(dates[0]), end: new Date(dates[1])})
            })
                .then(response => response.json())
                .then(data => {
                    setRooms(data);
                    setFilteredRooms(data);
                })
                .catch(err => {
                    console.log("fetch error" + err);
                });
        }
    }, [dates]);

    const filterByPlaces = (places) => {
        setFilteredRooms(rooms.filter(room => room.places >= places));
    };

    return (
        <>
            <RoomsMenu filterByPlaces={filterByPlaces} dates={dates} setDates={setDates}/>
            {filteredRooms !== null &&
                <RoomCards rooms={filteredRooms} dates={dates}/>
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