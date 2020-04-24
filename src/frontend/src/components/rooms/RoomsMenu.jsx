import React, { useState } from 'react';
import connect from "react-redux/es/connect/connect";
import {userRole} from '../../constants/enums';
import { Row,  Col } from 'react-bootstrap';
import {FormControl, InputLabel, Select, MenuItem, Button} from '@material-ui/core';
import DateRangePicker from '@wojtekmaj/react-daterange-picker';
import makeStyles from "@material-ui/core/styles/makeStyles";

const useStyles = makeStyles(theme => ({
    btn: {
        backgroundColor: theme.palette.blue.backgroundColor,
        color: theme.palette.blue.color,
        '&:hover': {
            backgroundColor: theme.palette.lightBlue.backgroundColor,
            color: theme.palette.blue.color
        },
    },
}));

function RoomsMenu(props) {
    const style = useStyles();
    const [showDatePicker, setShowDatePicker] = useState(false);
    const [places, setPlaces] = useState(1);

    const handleSetPlaces = (value) => {
        setPlaces(value);
        props.filterByPlaces(value);
    };

    const handleDatesChanged = (value) => {
        props.setDates(value);
    };

    return (
        <>
            <Row className="justify-content-center">
                { props.user !== null && props.user.role == userRole.HEAD &&
                    <Button onClick={() => window.location.href = '#/newRoom'} className={style.btn}>Додати тип номеру</Button>
                }
                <Col xs={8} md={4} onClick={() => setShowDatePicker(true)} className='align-self-center text-center'>
                    <DateRangePicker
                        isOpen={showDatePicker}
                        minDate={new Date()}
                        value={props.dates}
                        onChange={(value) => handleDatesChanged(value)}
                        format={'dd/MM/y'}
                    />
                </Col>

                <Col xs={8} md={3} className='align-self-center'>
                    <FormControl fullWidth>
                        <InputLabel id="places-filter">Виберіть кількість гостей</InputLabel>
                        <Select
                            fullWidth
                            labelId="places-filter"
                            id="filter-places"
                            value={places}
                            onChange={(e) => handleSetPlaces(e.target.value)}
                        >
                            <MenuItem value={1}>1</MenuItem>
                            <MenuItem value={2}>2</MenuItem>
                            <MenuItem value={3}>3</MenuItem>
                            <MenuItem value={4}>4</MenuItem>
                        </Select>
                    </FormControl>
                </Col>
            </Row>
        </>
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

export default connect(mapStateToProps, mapDispatchToProps)(RoomsMenu);