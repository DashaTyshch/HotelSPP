export const LOGIN_OPEN = 'LOGIN_OPEN';
export const LOGIN = 'LOGIN';

export function setLoginOpen(isOpen) {
    return {
        type: LOGIN_OPEN,
        payload: {
            loginOpen: isOpen
        },
    };
}
