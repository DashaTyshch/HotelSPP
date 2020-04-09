import React, {useEffect, useState} from "react";
import { useParams } from "react-router-dom";

export default function RoomTypeContainer(props) {
    let { id } = useParams();
    const [roomType, setRoomType] = useState(null);

    useEffect( () => {
        fetch(`/api/room_type/get?name=${id}`, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                setRoomType(data);
            })
            .catch(err => {
                console.log("fetch error" + err);
            });
    }, []);

    return (
        <>
            {roomType !== null &&
                <></>
            }
        </>
    );
}