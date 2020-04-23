import React from "react";
import {withRouter} from "react-router";
import { connect } from 'react-redux';
import styled from 'styled-components';
import BookingCard from "./bookingCard.jsx";

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

    return (
        <>{ props.user !== null &&
                <ProfileContainer>
                    <Greeting>{`Вітаємо ${props.user.name} ${props.user.surname}!`}</Greeting>

                    <Label>Поточні бронювання</Label>
                    <BookingsContainer>
                        {[1,2,3,4].map(el => {
                            return <BookingCard/>
                            })
                        }
                    </BookingsContainer>
                    <Label>Історія бронювань</Label>
                    <BookingsContainer>
                        {[1,2].map(el => {
                            return <BookingCard/>
                        })
                        }
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