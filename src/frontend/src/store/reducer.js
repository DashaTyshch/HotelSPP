import {LOGIN, LOGIN_OPEN} from "./actions";

const INITIAL_STATE = {
    loginOpen: false
};

export default (state=INITIAL_STATE, action) => {
    switch (action.type) {
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