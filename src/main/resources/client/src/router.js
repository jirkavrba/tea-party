import {createRouter, createWebHashHistory} from "vue-router";
import Homepage from "./views/Homepage.vue";
import Login from "./views/Login.vue";
import Lobbies from "./views/Lobbies.vue";
import CreateLobby from "./views/CreateLobby.vue";

export default createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: "/",
            component: Homepage
        },
        {
            path: "/login",
            component: Login
        },
        {
            path: "/lobbies",
            component: Lobbies
        },
        {
            path: "/create-lobby",
            component: CreateLobby
        },
        {
            path: "/:catchAll(.*)",
            redirect: "/"
        }
    ]
});