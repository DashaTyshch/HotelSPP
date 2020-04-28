import React, {useEffect, useState} from "react";
import {connect} from "react-redux";
import { withRouter } from "react-router";

import styled from 'styled-components';
import DateFnsUtils from '@date-io/date-fns';
import { DatePicker, MuiPickersUtilsProvider } from '@material-ui/pickers';
import {Col, Row} from "react-bootstrap";
import {userRole} from "../../constants/enums";
import {Button} from "@material-ui/core";
import {getToken} from "../../store/actions";
import makeStyles from "@material-ui/core/styles/makeStyles";

const getStatus = (id) => {
    switch (id) {
        case 1: return "Чекає підтвердження";
        case 2: return "Підверджено";
        case 3: return "Скасовано";
    }
};

const useStyles = makeStyles(theme => ({
    button: {
        margin: theme.spacing(3),
        '&:hover': {
            backgroundColor: theme.palette.lightBlue.backgroundColor,
            color: theme.palette.blue.color
        },
    },
    confirmBtn: {
        backgroundColor: theme.palette.blue.backgroundColor,
        color: theme.palette.blue.color,
    },
}));

const Dark = styled.span`
    color: #2C3531;
    font-size: 17px;
`;

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
    background: #d1e8e21f;
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

const OrderNumber = styled.div`
    font-weight: 500;
    font-size: 20px;
    color: #2C3531;
    text-align: center;
`;
const Info = styled.span`
    color: #31708E;
    font-size: 18px;
    font-weight: 500;
`;
const InfoRoom = styled(Info)`

`;

function BookingsContainer(props) {
    const style = useStyles();
    const [selectedDate, setSelectedDate] = useState(new Date());
    const [orders, setOrders] = useState(null);

    const getOrders = () => {
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
    };

    useEffect( () => {
        getOrders();
    }, [selectedDate]);

    const handleDateChanged = (value) => {
        setSelectedDate(value);
    };

    return (
        <>
            <Row className="justify-content-center">
                <Col xs={12} className='align-self-center text-center'>
                    <Label>Виберіть дату, щоб переглянути створенні бронювання</Label>
                </Col>
                <Col xs={12} className='align-self-center text-center'>
                    <MuiPickersUtilsProvider utils={DateFnsUtils}>
                        <DatePicker value={selectedDate} onChange={handleDateChanged} color="secondary"
                                    autoOk={true} variant="inline"/>
                    </MuiPickersUtilsProvider>
                </Col>

                <OrdersContainer>
                    { orders !== null &&
                        orders.length > 0
                        ? orders.map(order => {
                            return <Order>
                                    <div><Status>{getStatus(order.state_Id)}</Status></div>
                                    <OrderNumber>Номер бронювання {order.id}</OrderNumber>
                                    {order.bookings.map(booking => {
                                        return <Row>
                                            <Col xs={12} md={6} className='align-self-center text-center'>
                                                <Dark>З</Dark> <Info>{booking.start_date}</Info> <Dark>по</Dark> <Info>{booking.end_date}</Info>
                                            </Col>
                                            <Col xs={12} md={6} className='align-self-center text-center'>
                                                <Dark>Номер:</Dark> <InfoRoom>{booking.room_type}</InfoRoom>
                                            </Col>
                                            <Col xs={12} md={6} className='align-self-center text-center'>
                                                <Dark>Ціна за добу:</Dark> <InfoRoom>{booking.price} UAH</InfoRoom>
                                            </Col>
                                            <Col xs={12} md={6} className='align-self-center text-center'>
                                                <Dark>Загальна ціна:</Dark> <InfoRoom>{booking.period_price} UAH</InfoRoom>
                                            </Col>
                                            <Col xs={12} className='align-self-center text-center'>
                                                <Dark>Коментар:</Dark> <InfoRoom>{booking.comment}</InfoRoom>
                                            </Col>
                                        </Row>
                                    })}
                                <div className="align-self-center">
                                    <Button
                                        variant="contained"
                                        color="secondary"
                                        className={style.button}>
                                        Скасувати
                                    </Button>

                                    <Button
                                        type="submit"
                                        variant="contained"
                                        className={`${style.button} ${style.confirmBtn}`}>
                                        Підтвердити
                                    </Button>
                                </div>
                                </Order>
                            })
                        : <OrderNumber>Цього дня не було нових бронювань</OrderNumber>
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