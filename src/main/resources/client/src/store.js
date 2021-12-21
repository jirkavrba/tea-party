import Vue from 'vue'
import Vuex from 'vuex'
import api from "@/api";

Vue.use(Vuex)

const whileLoading = async (store, callback) => {
  await store.commit("setLoading", true);
  const result = await callback();
  await store.commit("setLoading", false);

  return result;
}

export default new Vuex.Store({
  state: {
    loading: false,
    token: window.localStorage.getItem("token"),
    lobbies: []
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
      window.localStorage.setItem("token", token);
    },
    setLoading(state, loading) {
      state.loading = loading;
    },
    setLobbies(state, lobbies) {
      state.lobbies = lobbies;
    }
  },
  actions: {
    validateToken: async (store) => {
      await whileLoading(store, async () => {
        const valid = await api.validateToken(store.state.token);

        if (valid) {
          return;
        }

        await store.commit("setToken", null);
      })
    },
    createAccount: async (store, username) => {
      await whileLoading(store, async () => {
        const user = await api.createAccount(username);
        await store.commit("setToken", user.token);
      });
    },
    loadLobbies: async (store) => {
      await whileLoading(store, async () => {
        const lobbies = await api.loadLobbies(store.state.token);
        await store.commit("setLobbies", lobbies)
      });
    },
    createLobby: async (store, mode) => {
      return await whileLoading(store, async () =>  await api.createLobby(store.state.token, mode));
    }
  },
  modules: {
  }
})
