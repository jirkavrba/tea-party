import {createStore} from "vuex";
import client from "./api/client";

const setOrRemoveLocalStorageItem = (key, value) => {
    if (value === null) {
        window.localStorage.removeItem(key);
        return;
    }

    window.localStorage.setItem(key, value);
}

const whileLoading = async (store, callback) => {
    await store.commit("setLoading", true)
    await callback()
    await store.commit("setLoading", false)
}

export default createStore({
    state: {
        token: window.localStorage.getItem("token"),
        username: window.localStorage.getItem("username"),
        loading: false,
    },
    actions: {
        async login(store, username) {
            await whileLoading(store, async () => {
                const token = await client.login(username);
                await store.commit("setUsername", username);
                await store.commit("setToken", token);
            })
        },
        async validateToken(store) {
            await whileLoading(store, async () => {
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
            });
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