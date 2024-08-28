<template>
  <div class="main">
    <div class="top-nav">
      <div class="logo"><img src="./icon/logo.png" alt="" class="logo-img" title="我们假设他是个 logo"></div>
      <div class="select-theme">
        当前主题
        <select name="主题" id="" @change="selectTheme" v-model="this.$data.theme">
          <option v-for="theme in this.ThemeList.theme" :key="theme" :value="theme.name">{{ theme.name }}</option>
        </select>
      </div>
      <div class="jump-operation">
        <router-link  v-for="link in jump_list" :key="link" :to="link.url" class="jump-link">{{link.name}}</router-link>
      </div>
      <div class="uer-inform">
        <div class="user-inform-center"
        :class="$data.head_border"
         @click="clickhead"
         @mouseover="headFocuse"
         @mouseleave="headBlur">
          <img :src="avatar" alt="" class="head-img"  title="用户头像">
          <div class="user-name">
            <p class="user-title-info">{{ titleInfo }}</p>
          </div>
        </div>
      </div>
      <div class="back-manage-link" v-show="user_power" @click="backSystem">
        <router-link to="" @click.native="jumpTo('http://localhost:80/')" class="link-type">后台管理</router-link></div>
      <el-dialog v-model="loginFormVisible">
        <LoginDialog @loginInfo="loginSwitch"></LoginDialog>
      </el-dialog>
    </div>
    <router-view></router-view>
  </div>
</template>
<script>
import axios from 'axios';
import { ref ,getCurrentInstance, reactive,onMounted} from 'vue';
import "../node_modules/swiper/swiper-bundle.min.css"
import unLogin from "./imgs/unLogin01.png";
import LoginDialog from './components/LoginDialog.vue';
export default {
  name: 'App',
  mounted() {
    // 判断登录状态
    let userInfo = JSON.parse(localStorage.getItem('userInfo'));
    if (null === userInfo) {
      this.titleInfo = '请先登录'
      this.isLogin = false
      return
    };
    this.$user.userName = userInfo.name
    this.$user.isLogin = userInfo.isLogin
    this.$user.token = userInfo.token
    if (userInfo.isLogin) {
      //this.titleInfo='个人中心'
      this.titleInfo = userInfo.name
      this.isLogin = true
      if (userInfo.manage) {
        this.user_power = true;
      }
    }
  },
  setup(){
    const cns = getCurrentInstance()
    let url = cns.appContext.config.globalProperties.$url
    let user = getCurrentInstance().appContext.config.globalProperties
    let loginFormVisible = ref(false)
    let isLogin = ref(false)
    let user_power = ref(false)
    let jump_list = []
    let ThemeList = reactive({
      theme: []
    })
    jump_list.push({url:"/home",name:"首页",is_focus:"link-blur"})
    jump_list.push({url:"/upload",name:"上传视频",is_focus:"link-blur"})
    jump_list.push({url:"/about",name:"关于",is_focus:"link-blur"})
    jump_list.push({url:"/article",name:"个人文章",is_focus:"link-blur"})
    jump_list.push({url:"/contentManagement",name:"内容管理",is_focus:"link-blur"})
    onMounted(async () => {
      axios.get(url + "MainInterface/getThemes", {}).then(response => {
        cns.appContext.config.globalProperties.$themeList=response.data.data
        cns.appContext.config.globalProperties.$themeList.forEach(element => {
          ThemeList.theme.push({name:element})
        });
      })
    })
    return {
      jump_list,
      loginFormVisible,
      isLogin,
      user,
      user_power,
      ThemeList
    }
  },
  components: {
    LoginDialog
  },
  data(){
      return{
        titleInfo: '请先登录',
        search:"",
        searchIcon:true,
        head_border:"head-blur",
        avatar:unLogin,
        theme:this.$theme
      }
    },
  methods : {
    selectTheme:function(obj){
      this.$theme = this.$data.theme
      console.log("change"+this.$theme)
      localStorage.setItem('theme', this.$theme)
    },
       //点击搜索触发函数
    clickSearch:function(){
      alert("搜索"+this.$data.search)
      this.$data.search = ""
    },
        //鼠标移动到搜索图标上触发函数
    searchFocuse:function(){
      this.$data.searchIcon=false
    },
        //鼠标离开搜索图标上触发函数
    searchBlur:function(){
      this.$data.searchIcon=true
    },
        //鼠标登录触发函数
    headFocuse:function(){
      this.$data.head_border="head-focuse"
    },
        //鼠标离开登录触发函数
    headBlur:function(){
      this.$data.head_border="head-blur"
    },
        //点击登录触发函数
    clickhead: function(){
      if (this.isLogin) {
        this.$router.push({
          name: 'settingsCenter', params: {}
        })
      } else {
        this.loginFormVisible = true
      }
    },
    loginSwitch: function(val){
      this.isLogin = val
      this.loginFormVisible = !val
      this.titleInfo = '个人中心'
      axios.post('http://localhost:8080/user/admin/login', {
        email: '',
        password: ''
      }).then(res => {
        localStorage.setItem('token', res.data.token)
        console.log(localStorage.getItem('token'))
        this.user_power = true
      })
      location.reload()
    },
    /* 跳转到子网地址 */
    jumpTo(url) {
      window.open(url,"_blank");
    },
}
}
</script>

<style scoped>
* {
  margin:0px;
	padding: 0px;
}
/* 主界面宽度设定为全屏 */
.main {
  width: 100vw;
  height: auto;
}
/* 导航栏基础配置 */
.top-nav {
  width: 100vw;
  height: 10vh;
  background-color: rgba(0, 0, 0, 0.4);
  position: fixed;
  z-index: 2;
  display: flex;
  justify-content: center;
  align-items: center;
}
/* logo基础配置 */
.logo {
  height: 100%;
  width: 10vw;
  display: flex;
  justify-content: center;
  align-items: center;
}
/* logo图片设置 */
.logo-img {
  width: 100%;
  height: 80%;
}
/* 跳转栏基础配置 */
.jump-operation {
  height: 100%;
  width: 45vw;
  display: flex;
  justify-content: center;
  align-items: center;

}

/* 跳转链接配置 */
.jump-link {
  font-size: 1.5em;
  margin-left: 5%;
  color: white;
  font-size:20px;
}
.jump-link:hover{
  background-color: rgba(180,160,146,0.5);
  color: rgb(93, 76, 54);
}
/* 后台跳转 */
.back-manage-link{
  margin-left: 4vh;
}

.link-type{
  color:white;
}
.link-type:hover{
  background-color: rgba(180,160,146,0.5);
  color: rgb(93, 76, 54);
}
/* 用户信息栏 */
.uer-inform {
  height: 100%;
  width: 10vw;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 4vh;
}
/* 搜索框基础设置 */
.search-box {
  height: 100%;
  width: 15vw;
  display: flex;
  justify-content: center;
  align-items: center;
}
.search-left {
  width: 15%;
  height: 40%;
  background-color: white;
  border-top-left-radius: 40%;
  border-bottom-left-radius: 40%;
}
.search-center {
  width: 70%;
  height: 40%;
  background-color: white;
}
.search-right {
  width: 15%;
  height: 40%;
  background-color: white;
  border-top-right-radius: 40%;
  border-bottom-right-radius: 40%;
}
/* 搜索框文本设置 */
.search-inform {
  height: 100%;
  width: 100%;
  outline: none;
  border:none;
  font-size: 0.9em;
  font-family: "Arial Black";
}
/* 搜索图标大小设置 */
.search-img {
  width: 85%;
  height: 90%;
}
/* 用户头像大小设置 */
.head-img {
  width: 8vh;
  height: 8vh;
  border-radius: 4vh;
  margin-right: 1vh;
}
/* 用户信息栏 */
.user-inform-center {
  height: 80%;
  width: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.user-title-info{
  width: 100%;
  height: 100%;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
/* 用户名字显示设置 */
.user-name {
  font-size: 1em;
  color: white;
}
/* 用户信息栏鼠标移出 */
.head-blur {
  border: none;
}
/* 用户信息栏鼠标移入 */
.head-focuse {
  border: solid white 1.5px;
  border-radius: 20%;
  width: 22vh;
}
/* 选择主题 */
.select-theme {
  color: whitesmoke;
}
</style>