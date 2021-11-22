import {createStore} from "vuex";

export default createStore({
    state: {
        token: window.localStorage.getItem("token"),
        username: window.localStorage.getItem("username"),
    }
});