import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/Login.vue";
import Lobbies from "@/views/Lobbies.vue";
import store from "./store";
import CreateLobby from "@/views/CreateLobby";

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
    },
    {
        name: "CreateLobby",
        path: "/lobbies/create",
        component: CreateLobby
    }
]

const router = new VueRouter({
    routes,
})

router.beforeEach(async (to, from, next) => {
    // TODO: Add token validation cache later on
    await store.dispatch("validateToken");

    if (to.name !== "Login" && (store.state.token === null || store.state.token === "null")) {
        next({name: "Login"});
    }
    else {
        next();
    }
})

export default router
