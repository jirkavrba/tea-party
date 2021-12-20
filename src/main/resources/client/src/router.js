import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/Login.vue";
import Lobbies from "@/views/Lobbies.vue";
import store from "./store";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: "Lobbies",
        redirect: "/lobbies",
    },
    {
        name: "Login",
        path: "/login",
        component: Login
    },
    {
        name: "Lobbies",
        path: "/lobbies",
        component: Lobbies
    }
]

const router = new VueRouter({
    routes,
})

router.beforeEach(async (to, from, next) => {
    await store.dispatch("validateToken");

    if (to.name !== "Login" && (store.state.token === null || store.state.token === "null")) {
        next({name: "Login"});
    }
    else {
        next();
    }
})

export default router
