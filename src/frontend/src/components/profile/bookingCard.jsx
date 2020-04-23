import React from "react";
import styled from 'styled-components';

const Card = styled.div`
    width: 350px;
    background: #D1E8E2;
    box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.4);
    border-radius: 5px;
    height: 250px;
    margin: 15px;
    display: flex;
    flex-direction: column;
`;

const Label = styled.div`
    float: right;
    padding: 5px;
    background: #31708E;
    color: white;
    border-radius: 10px;
    margin: 5px;
`;

const InfoBlock = styled.div`

`;

export default function BookingCard(props) {

    return (
        <Card>
            <div><Label>Чекає підтвердження</Label></div>
            <div>
                <div>12.02.2020 - 15.02.2020</div>
                <div>Студія</div>
                <div>-</div>
            </div>
            <button/>
            <button/>
        </Card>
    )
}