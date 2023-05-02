import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
//全局饿了么组件
import elementIcon from "./plugins/icons"
//全局axios
import axios from 'axios'
axios.defaults.maxContentLength = 20 * 1024 * 1024;
axios.defaults.maxBodyLength = 20 * 1024 * 1024;
//全局vuex
import store from './store'
//路由
import router from './router/index'
const app =createApp(App)
app.config.globalProperties.$axios =axios
app.use(elementIcon).use(store).use(router).mount('#app')
