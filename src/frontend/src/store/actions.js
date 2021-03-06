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

export function fetchUserInfo() {
    return dispatch => {
        if (getToken()) {
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
    }
}

export const logOut = () => {
    return () => {
        setUser(null);
        removeToken();
        location.reload();
    }
};

export const getToken = () => {
    return localStorage.getItem('token');
};
const removeToken = () => {
    localStorage.removeItem("token");
};
