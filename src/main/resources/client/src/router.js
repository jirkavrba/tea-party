import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/Login.vue";
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
    }
]

const router = new VueRouter({
    routes,
})

router.beforeEach((to, from, next) => {
    if (to.name !== "Login" && (store.state.token === null || store.state.token === "null")) {
        next({name: "Login"});
    }
    else {
        next();
    }
})

export default router
