import React from 'react';
import { connect } from 'react-redux';
import { withRouter } from "react-router";

import { Nav, Navbar } from 'react-bootstrap';
import {setLoginOpen, logOut} from "../../store/actions";

const Header = props => {

    return (
        <Navbar bg="dark" variant="dark" expand="md" fixed="top">
            <Navbar.Brand href="/">HotelSPP</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="mr-auto">
                    {props.user != null &&
                        <Nav.Link href="#/profile">Особистий кабінет</Nav.Link>
                    }
                </Nav>
                {props.user == null ?
                    <Nav.Link style={{ color: '#D1E8E2' }} onClick={() => props.onSetLoginOpen(true)}>Ввійти</Nav.Link>
                    : <Nav.Link style={{ color: '#D1E8E2' }} onClick={props.logOut}>Вийти</Nav.Link>
                }
            </Navbar.Collapse>
        </Navbar>
    );
};

const mapStateToProps = (state) => {
    return {
        user: state.user
    };
};
const mapDispatchToProps = (dispatch) => {
    return {
        onSetLoginOpen: (isOpen) => dispatch(setLoginOpen(isOpen)),
        logOut: () => dispatch(logOut()),
    };
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Header));