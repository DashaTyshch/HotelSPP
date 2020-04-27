import React, {useEffect, useState} from "react";
import {connect} from "react-redux";
import { withRouter } from "react-router";

import styled from 'styled-components';
import DateFnsUtils from '@date-io/date-fns';
import { DatePicker, MuiPickersUtilsProvider } from '@material-ui/pickers';
import {Col, Row} from "react-bootstrap";
import {userRole} from "../../constants/enums";
import {Button, FormControl, InputLabel, MenuItem, Select} from "@material-ui/core";
import {getToken} from "../../store/actions";

const getStatus = (id) => {
    switch (id) {
        case 1: return "Чекає підтвердження";
        case 2: return "Підверджено";
        case 3: return "Скасовано";
    }
};

const Label = styled.div`
    font-size: 20px;
    font-weight: 500;
    letter-spacing: 0.05em;
    color: #31708E;
`;

const OrdersContainer = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
`;

const Order = styled.div`
    width: 80%;
    box-shadow: rgba(0, 0, 0, 0.4) 4px 4px 10px;
    height: 250px;
    display: flex;
    flex-direction: column;
    background: rgb(209, 232, 226);
    border-radius: 5px;
    margin: 15px;
`;

const Status = styled.div`
    float: right;
    padding: 5px;
    background: #31708E;
    color: white;
    border-radius: 10px;
    margin: 5px;
`;

function BookingsContainer(props) {
    const [selectedDate, handleDateChange] = useState(new Date());
    const [orders, setOrders] = useState(null);

    useEffect( () => {
        fetch(`/api/order/orders_by_date`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
                'Authorization': `Bearer ${getToken()}`
            },
            body: JSON.stringify(selectedDate)
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                setOrders(data);
            })
            .catch(err => {
                console.log("fetch error" + err);
            });
    }, []);

    const bla = (value) => {
        console.log(value);
        handleDateChange(value);
    };

    return (
        <>
            <Row className="justify-content-center">
                <Col xs={12} className='align-self-center text-center'>
                    <Label>Виберіть дату, щоб переглянути створенні бронювання</Label>
                </Col>
                <Col xs={12} className='align-self-center text-center'>
                    <MuiPickersUtilsProvider utils={DateFnsUtils}>
                        <DatePicker value={selectedDate} onChange={bla} color="secondary"
                                    autoOk={true} variant="inline"/>
                    </MuiPickersUtilsProvider>
                </Col>

                <Col xs={8} md={3} className='align-self-center'>
                </Col>

                <OrdersContainer>
                    { orders !== null &&
                        orders.length > 0
                        ? orders.map(order => {
                            return <Order>
                                    <div><Status>{getStatus(order.state_Id)}</Status></div>
                                </Order>
                            })
                        : <div>На сьогодні бронювань немає</div>
                    }
                </OrdersContainer>
            </Row>
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

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(BookingsContainer));