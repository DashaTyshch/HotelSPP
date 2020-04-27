import React, {useState} from "react";
import {connect} from "react-redux";
import { withRouter } from "react-router";

import DateFnsUtils from '@date-io/date-fns';
import { DatePicker, MuiPickersUtilsProvider } from '@material-ui/pickers';
import {Col, Row} from "react-bootstrap";
import {userRole} from "../../constants/enums";
import {Button, FormControl, InputLabel, MenuItem, Select} from "@material-ui/core";
import DateRangePicker from "@wojtekmaj/react-daterange-picker";

function BookingsContainer(props) {
    const [selectedDate, handleDateChange] = useState(new Date());

    return (
        <>
            <Row className="justify-content-center">

                <Col xs={8} md={4} className='align-self-center text-center'>
                    <MuiPickersUtilsProvider utils={DateFnsUtils}>
                        <DatePicker value={selectedDate} onChange={handleDateChange} color="secondary"
                                    autoOk={true} variant="inline"/>
                    </MuiPickersUtilsProvider>
                </Col>

                <Col xs={8} md={3} className='align-self-center'>

                </Col>
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