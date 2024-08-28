import {createRouter, createWebHashHistory, createWebHistory} from "vue-router"

// 1. 定义路由组件， 注意，这里一定要使用 文件的全名（包含文件后缀名）
import App from "../App.vue";
import home from "../home.vue";
import videoWindow from "../videoWindow.vue";
import settingsCenter from "../page/settings_center/settingsCenter.vue"
import userInfo from "../page/settings_center/UserInfo.vue";
import upload from "../page/content_center/upload.vue";
import about from "../page/About.vue";
import contentManagement from "../page/ContentManagement.vue";
import article from "../page/article.vue";
// 2. 定义路由配置
const routes = [
  { 
    path: "/",
    redirect: '/home'
  },
  { path: "/App", name:"App",component: App },
  { path: "/home", name:"home",component: home },
  { path: "/videoWindow",name:"videoWindow", component: videoWindow },
  { path: "/settingsCenter",name:"settingsCenter", component: settingsCenter },
  { path: "/userInfo",name:"userInfo", component: userInfo },
  { path: "/upload",name:"upload", component: upload },
  { path: "/about",name:"about", component: about },
  { path: "/contentManagement",name:"contentManagement", component: contentManagement },
  { path: "/article",name:"article", component: article },
];

// 3. 创建路由实例
const router = createRouter({
  // 4. 采用hash 模式
  history: createWebHashHistory(),
  // 采用 history 模式
  // history: createWebHistory(),
  routes, // short for `routes: routes`
});

export default router

