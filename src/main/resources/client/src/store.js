import {createStore} from "vuex";
import client from "./api/client";

const setOrRemoveLocalStorageItem = (key, value) => {
    if (value === null) {
        window.localStorage.removeItem(key);
        return;
    }

    window.localStorage.setItem(key, value);
}

export default createStore({
    state: {
        token: window.localStorage.getItem("token"),
        username: window.localStorage.getItem("username"),
        loading: false,
    },
    actions: {
        async login(store, username) {
            await store.commit("setLoading", true);

            const token = await client.login(username);

            await store.commit("setUsername", username);
            await store.commit("setToken", token);

            await store.commit("setLoading", false);
        },
        async validateToken(store) {
            await store.commit("setLoading", true);

            // Do not validate the token, if it's null already
            if (store.state.token === null) {
                await store.commit("setLoading", false);
                return;
            }

            const valid = await client.validateToken(store.state.token);
            if (!valid) {
                store.commit("setToken", null);
                store.commit("setUsername", null);
            }

            await store.commit("setLoading", false);
        }
    },
    mutations: {
        setLoading(state, loading) {
            state.loading = loading;
        },
        setUsername(state, username) {
            state.username = username;
            setOrRemoveLocalStorageItem("username", username);
        },
        setToken(state, token) {
            state.token = token;
            setOrRemoveLocalStorageItem("token", token);
        }
    }
});