export const SET_USER = 'SET_USER';
export const LOGIN_OPEN = 'LOGIN_OPEN';
export const LOGIN = 'LOGIN';

export function setUser(user) {
    return {
        type: SET_USER,
        payload: {
            user: user
        },
    };
}

export function setLoginOpen(isOpen) {
    return {
        type: LOGIN_OPEN,
        payload: {
            loginOpen: isOpen
        },
    };
}

export const fetchUserInfo = () => {
    return dispatch => {
        return fetch("/api/user/info", {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
                'Authorization': `Bearer ${getToken()}`
            },
        })
            .then(response => response.json())
            .then(data => {
                dispatch(setUser(data));
            })

            .catch(err => {
                console.log("fetch error" + err);
            });
    }
};

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
            .then(response => response.text())
            .then(data => {
                localStorage.setItem("token", data);
                dispatch(fetchUserInfo());
                //location.reload();
            })

            .catch(err => {
                console.log("fetch error" + err);
            });
    }
};

const getToken = () => {
    return localStorage.getItem('token');
};
