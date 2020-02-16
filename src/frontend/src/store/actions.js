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

export const userLoginFetch = (phone, pwd) => {
    return dispatch => {
        return fetch("/api/auth/signIn", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
            },
            body: JSON.stringify({login: phone, password: pwd})
        })
            .then(resp => resp.json())
            .then(data => {
                if (data.message) {
                    // Here you should have logic to handle invalid login credentials.
                    // This assumes your Rails API will return a JSON object with a key of
                    // 'message' if there is an error
                } else {
                    localStorage.setItem("token", data)
                    //dispatch(loginUser(data.user))
                }
            })
    }
}
