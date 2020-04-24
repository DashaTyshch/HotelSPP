import React, {useEffect, useState} from "react";
import {withRouter} from "react-router";
import { connect } from 'react-redux';
import styled from 'styled-components';
import BookingCard from "./bookingCard.jsx";
import {getToken} from "../../store/actions";

const ProfileContainer = styled.div`
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    align-content: center;
    text-align: center;
`;

const Greeting = styled.div`
    font-size: 24px;
    font-weight: 500;
    letter-spacing: 0.05em;
    color: #31708E;
`;

const Label = styled.div`
    font-size: 20px;
    font-weight: 500;
    letter-spacing: 0.05em;
    color: #31708E;
`;

const BookingsContainer = styled.div`
    display: flex;
    flex-wrap: wrap;
    align-content: center;
    justify-content: center;
`;

function Profile(props) {
    const [orders, setOrders] = useState([]);

    useEffect( () => {
        fetch(`/api/order/by_user`, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
                'Authorization': `Bearer ${getToken()}`
            },
        })
            .then(response => response.json())
            .then(data => {
                setOrders(data);
            })

            .catch(err => {
                console.log("fetch error" + err);
            });
    }, []);

    return (
        <>{ props.user !== null &&
                <ProfileContainer>
                    <Greeting>{`Вітаємо ${props.user.name} ${props.user.surname}!`}</Greeting>

                    <Label>Поточні бронювання</Label>
                    <BookingsContainer>
                        {orders.length > 0
                            ? orders.map(order => {
                                return <BookingCard order={order}/>
                            })
                            : <div>Бронювань немає</div>
                        }
                    </BookingsContainer>
                    <Label>Історія бронювань</Label>
                    <BookingsContainer>

                    </BookingsContainer>

                </ProfileContainer>
        }</>
    )
}

const mapStateToProps = (state) => {
    return {
        user: state.user
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
    };
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Profile));