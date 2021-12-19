import {createStore} from "vuex";

export default createStore({
    state: {
        loading: false,
        token: window.localStorage.getItem("token")
    },
    mutations: {
        setLoading(state, loading) {
            state.loading = loading;
        },
        setToken(state, token) {
            window.localStorage.setItem("token", token);
            state.token = token;
        }
    }
});