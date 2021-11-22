import {createRouter, createWebHashHistory} from "vue-router";
import Homepage from "./views/Homepage.vue";
import Login from "./views/Login.vue";

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
            path: "/:catchAll(.*)",
            redirect: "/"
        }
    ]
});