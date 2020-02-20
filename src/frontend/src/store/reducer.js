import {LOGIN, LOGIN_OPEN, SET_USER} from "./actions";

const INITIAL_STATE = {
    user: null,
    loginOpen: false
};

export default (state=INITIAL_STATE, action) => {
    switch (action.type) {
        case SET_USER:
            return {
                ...state,
                user: action.payload.user
            };
        case LOGIN_OPEN:
            return {
              ...state,
              loginOpen: action.payload.loginOpen
            };
        case LOGIN:
            return {
                ...state
            };
        default:
            return state;
    }

};