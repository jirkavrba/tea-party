import {createApp} from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import store from "./store"
import router from "./router"
import {loadFonts} from './plugins/webfontloader'

loadFonts()

createApp(App)
    .use(vuetify)
    .use(store)
    .use(router)
    .mount('#app')
