import {createStore} from "vuex";
import client from "./api/client";

export default createStore({
    state: {
        token: window.localStorage.getItem("token"),
        username: window.localStorage.getItem("username"),
        loading: false,
    },
    actions: {
        async login(store, username) {
            await store.commit("setLoading", true);

            const {token} = client.login(username);

            await store.commit("setUsername", username);
            await store.commit("setToken", token);

            await store.commit("setLoading", false);
        }
    },
    mutations: {
        setLoading(state, loading) {
            state.loading = loading;
        },
        setUsername(state, username) {
            state.username = username;
            window.localStorage.setItem("username", username);
        },
        setToken(state, token) {
            state.token = token;
            window.localStorage.setItem("token", token);
        }
    }
});