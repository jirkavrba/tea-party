import {createRouter} from "vue-router";
import Homepage from "./views/Homepage";

export default createRouter({
    routes: [
        {
            path: "/",
            component: Homepage
        }
    ]
});