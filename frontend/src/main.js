import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'
import './assets/main.css'
import router from './router/router'
import vue3videoPlay from "vue3-video-play"; // 引入组件
import "vue3-video-play/dist/style.css"; // 引入css
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css';

const app = createApp(App)
app.config.globalProperties.$axios=axios;
// app.config.globalProperties.$url="https://www.ichlearning.cn:8080/"
app.config.globalProperties.$url="http://localhost:8080/"
app.config.globalProperties.$user={userName:"user",key:"key",isLogin:false}
app.config.globalProperties.$theme="西河大鼓"
app.config.globalProperties.$themeList=[]
app.use(router)
app.use(vue3videoPlay);
app.use(router).use(ElementPlus)
app.mount('#app')
