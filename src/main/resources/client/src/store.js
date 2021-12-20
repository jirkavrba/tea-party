import Vue from 'vue'
import Vuex from 'vuex'

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
        // Try fetching /api/authentication/check
        const valid = false;

        if (valid) {
          return;
        }

        await store.commit("setToken", null);
      })
    },
    createAccount: async (store, username) => {
      await whileLoading(store, async () => {
        await console.log(username);
      });
      await store.commit("setLoading", true);
    }
  },
  modules: {
  }
})
