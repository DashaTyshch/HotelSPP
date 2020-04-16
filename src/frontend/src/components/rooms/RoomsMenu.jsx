import React, { useState } from 'react';
import { Row,  Col } from 'react-bootstrap';
import { FormControl, InputLabel, Select, MenuItem} from '@material-ui/core';
import DateRangePicker from '@wojtekmaj/react-daterange-picker';

export default function RoomsMenu(props) {
    const [showDatePicker, setShowDatePicker] = useState(false);
    const [places, setPlaces] = useState(1);
    const [dates, setDates] = useState([new Date(), new Date()]);

    return (
        <>
            <Row className="align-items-center">
                <Col xs={12} md={4} onClick={() => setShowDatePicker(true)}>
                    <DateRangePicker
                        isOpen={showDatePicker}
                        minDate={new Date()}
                        value={dates}
                        onChange={(value) => setDates(value)}
                        format={'dd.MM.y'}
                    />
                </Col>
                <Col md={4}>
                    <FormControl fullWidth>
                        <InputLabel id="places-filter">Виберіть кількість гостей</InputLabel>
                        <Select
                            fullWidth
                            labelId="places-filter"
                            id="filter-places"
                            value={places}
                            onChange={(e) => setPlaces(e.target.value)}
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