import {createRouter, createWebHashHistory} from "vue-router";
import Homepage from "./components/Homepage.vue";

export default createRouter({
    routes: [
        {
            name: "home",
            path: "/",
            component: Homepage
        }
    ],
    history: createWebHashHistory(),
})