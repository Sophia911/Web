<script setup>

</script>
<template>

  <div class="formbody">
    <div class="formtitle"><span>用户信息</span></div>
    <ul class="forminfo">
      <li><label>头像</label><img :src="avatar"/></li>
      <li><label>邮箱</label><input v-model="id" type="text" readonly="readonly" class="info"/></li>
      <li><label>名字</label><input v-model="name" type="text" readonly="readonly" class="info"/></li>
      <!--      <li><label>背景图</label><input v-model="introduction" type="text" readonly="readonly" class="info"/></li>-->
      <li><label>等级</label><input v-model="background" type="text" readonly="readonly" class="info"/></li>
    </ul>
    <el-button type="danger" @click="logout">退出登录</el-button>
  </div>
</template>

<script>
import axios from "axios";
import {getCurrentInstance} from "vue";
import unLogin from "../../imgs/unLogin01.png";
export default {
  name: "UserInfo",
  data(){
    return{
      avatar:unLogin,
      id:"",
      name:"",
      introduction:"level1",
      background:"",

    }
  },
  methods:{
    logout: function(){
    console.log("out")
      window.localStorage.clear();
      location.reload();
    },
  },
  onload(){

  },
  mounted(){
    const cns=getCurrentInstance();
    let url=cns.appContext.config.globalProperties.$url;
    axios.post(this.$url+"/GetUserInfo",{
      id:cns.appContext.config.globalProperties.$user.userName
    }).then(res=>{
      if (res.status == 200){
        console.log(res,"响应信息")
        this.avatar=res.data.avatar;
        this.id = res.data.id;         //改变data中的result值（默认为''）
        this.name=res.data.name;
        //this.introduction=res.data.introduction;
        this.background=res.data.background;
      }

    }).catch(err => {
      console.log(err)
    })
  },
  setup(){

  },

}
</script>

<style scoped>
ul{display:block;margin:0;padding:0;list-style:none;}
li{display:block;margin:0;padding:0;list-style: none;}
img{border:0;}
.formbody{padding:10px 18px;}
.formtitle{color:#00a1d6;font-family: "Arial Black";border-bottom:solid 1px #d0dee5; line-height:45px; position:relative; height:35px; margin-bottom:28px;}

.forminfo{padding-left:23px;}
.forminfo img{width: 100px}
.forminfo li{margin-bottom:13px; clear:both;}
.forminfo li label{font-family: "Arial Black";color:#00a1d6;width:86px;line-height:34px; display:block; float:left;}
.forminfo li i{color:#7f7f7f; padding-left:20px; font-style:normal;}
.forminfo li cite{display:block; padding-top:10px;}
.info{width:345px; height:32px; line-height:32px; border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df; background:url(../../imgs/inputbg.gif) repeat-x; text-indent:10px;}
.textinput{border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df; background:url(../../imgs/inputbg.gif) repeat-x; padding:10px; width:504px; height:135px; line-height:20px; overflow:hidden;}
.btn{width:137px;height:35px; background:url(../../imgs/btnbg.png) no-repeat; font-size:14px;font-weight:bold;color:#fff; cursor:pointer;}


</style>