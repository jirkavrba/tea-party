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
    await store.commit("setLoading", true);
    const response = await callback();
    await store.commit("setLoading", false);

    return response;
}

export default createStore({
    state: {
        token: window.localStorage.getItem("token"),
        username: window.localStorage.getItem("username"),
        loading: false,
        lobbies: [],
        lobby: null
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
                    return;
                }

                const valid = await client.validateToken(store.state.token);
                if (!valid) {
                    store.commit("setToken", null);
                    store.commit("setUsername", null);
                }
            });
        },
        async loadLobbies(store) {
            await whileLoading(store, async () => {
                const lobbies = await client.loadLobbies(store.state.token);
                await store.commit("setLobbies", lobbies);
            });
        },
        async createLobby(store, mode) {
            return await whileLoading(store, async () => {
                return await client.createLobby(store.state.token, mode);
            });
        },
        async loadLobby(store, id) {
            await whileLoading(store, async () => {
                const lobby = await client.loadLobby(store.state.token, id);
                await store.commit("setLobby", lobby);
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
        },
        setLobbies(state, lobbies) {
            state.lobbies = lobbies;
        },
        setLobby(state, lobby) {
            state.lobby = lobby;
        }
    }
});