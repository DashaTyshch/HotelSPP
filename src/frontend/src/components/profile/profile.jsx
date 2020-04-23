import React from "react";
import {withRouter} from "react-router";
import { connect } from 'react-redux';
import styled from 'styled-components';

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

function Profile(props) {

    return (
        <>{ props.user !== null &&
                <ProfileContainer>

                    <Greeting>{`Вітаємо ${props.user.name} ${props.user.surname}!`}</Greeting>
                    <Label>Поточні бронювання</Label>
                    <Label>Історія бронювань</Label>

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