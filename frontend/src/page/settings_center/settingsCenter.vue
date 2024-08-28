
<template>
  <div>
      <div class="top-nav"></div>
    <el-container class="center-style">
      <el-aside width="200px">
        <el-row class="tac">
          <el-col :span="3">
            <el-menu
                default-active="2"
                class="el-menu-vertical-demo"
            >
              <el-menu-item index="1">
                <el-icon><avatar /></el-icon>
                <span>个人中心</span>
              </el-menu-item>

              <el-sub-menu index="2">
                <template #title>
                  <el-icon><location /></el-icon>
                  <span>安全中心</span>
                </template>
                <el-menu-item index="1-1">未开放区域</el-menu-item>
                <el-menu-item index="1-2">未开放区域</el-menu-item>
                <el-menu-item-group title="第三方账号">
                  <el-menu-item index="1-3">管理</el-menu-item>
                </el-menu-item-group>
                <el-sub-menu index="1-4">
                  <template #title>未开放区域</template>
                  <el-menu-item index="1-4-1">未开放区域</el-menu-item>
                </el-sub-menu>
              </el-sub-menu>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
      <el-main class="temp">
        <user-info></user-info>
      </el-main>
    </el-container>
  </div>
</template>


<script lang="ts" >

import userInfo from "./UserInfo.vue";
export default {
  name:"settingsCenter",
  components:{
    userInfo
  },
  methods: {
    // userCenterClick: function (){
    //   this.$router.push({
    //     name: 'userInfo', params: {}
    //   })
    // }
  }
}
</script>

<style>
* {
  margin:0px;
  padding: 0px;
  font-family: "Arial Black";
}
.top-nav {
  width: 100vw;
  height: 10vh;
}
.tac {
  width: 100vw;
}
.center-style {
  height: 100vh;
}
</style>
