import React, {useEffect, useState} from "react";
import { useParams } from "react-router-dom";

import styled from 'styled-components';
import Carousel from '@brainhubeu/react-carousel';

const Title = styled.h1`
  text-align: center;
  color: #31708E;
  letter-spacing: 0.05em;
`;

const Image = styled.img`
    width: 100%;
`;

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
                <>
                    <Title>{roomType.name}</Title>

                    {roomType.images.length > 0 &&
                        <Carousel autoPlay={7000} animationSpeed={2000} offset={15} stopAutoPlayOnHover
                                  slidesPerPage={roomType.images.length < 3? roomType.images.length : 3}
                                  arrows centered itemWidth={500}
                                  clickToChange
                                  breakpoints={{
                                      640: {
                                          slidesPerPage: 1,
                                          arrows: false,
                                          itemWidth: 300
                                      },
                                      900: {
                                          slidesPerPage: roomType.images.length < 2? 1 : 2,
                                          arrows: false,
                                          itemWidth: 400
                                      }
                                  }}>
                            {roomType.images.map((image) =>{
                                return <Image src={image.image} alt=""/>
                            })}
                        </Carousel>

                    }

                </>
            }
        </>
    );
}