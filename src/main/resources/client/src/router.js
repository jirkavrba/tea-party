import {createRouter, createWebHashHistory} from "vue-router";
import Homepage from "./views/Homepage.vue";

export default createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: "/",
            component: Homepage
        },
        {
            path: "/:catchAll(.*)",
            redirect: "/"
        }
    ]
});