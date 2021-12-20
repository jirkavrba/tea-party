import Vue from 'vue'
import Vuex from 'vuex'
import api from "@/api";

Vue.use(Vuex)

const whileLoading = async (store, callback) => {
  await store.commit("setLoading", true);
  await callback();
  await store.commit("setLoading", false);
}

export default new Vuex.Store({
  state: {
    loading: false,
    token: window.localStorage.getItem("token")
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
      window.localStorage.setItem("token", token);
    },
    setLoading(state, loading) {
      state.loading = loading;
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

        console.log(user);

        await store.commit("setToken", user.token);
      });
    }
  },
  modules: {
  }
})
