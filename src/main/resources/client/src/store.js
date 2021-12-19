import {createStore} from "vuex";
import api from "./api";

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
    },
    actions: {
        async validateToken(store) {
            if (store.state.token == null) {
                return null;
            }

            const valid = await api.validateToken(store.state.token);

            if (!valid) {
                await store.commit("setToken", null);
                return null;
            }

            return store.state.token;

        }
    }
});