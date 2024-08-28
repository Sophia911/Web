<template>
    <div class="field">
      <el-col :span="20" :offset="2">
        <el-tabs type="border-card">
          <el-tab-pane label="登录">
            <el-form label-position="right" label-width="80px" class="login-form">
              <el-form-item label="邮箱："
                            :rules="{
                                required: true,
                                message: '请输入正确邮箱',
                                trigger: 'blur',
                              }">
                <el-input v-model="email" placeholder="请输入邮箱" @blur="checkEmailFormat(email)"/>
              </el-form-item>
              <el-form-item label="密码："
                            :rules="{
                              required: true,
                              message: '请输入密码',
                              trigger: 'blur',
                            }">
                <el-input type="password" v-model="password" placeholder="请输入密码"/>
              </el-form-item>
              <el-form-item>
                <input type="checkbox" id="login-agreement" name="agreement" v-model="isAgree">
                <label for="login-agreement">同意<a>用户协议</a>与<a>隐私条款</a></label>
              </el-form-item>
              <el-row justify="center">
                <el-button @click="loginCheck" type="primary" :disabled="!(emailOK && password && isAgree)" >
                  登录
                </el-button>
              </el-row>
            </el-form>
          </el-tab-pane>
          
          <el-tab-pane label="注册">
            <el-form label-position="right" label-width="100px" class="login-form">
              <el-form-item label="注册邮箱：">
                <el-input v-model="rEmail" placeholder="example@mail.com" @change="checkEmailFormat(rEmail)"/>
              </el-form-item>
              <el-form-item label="密码：">
                <el-input type="password" v-model="rPassword" />
              </el-form-item>
              <el-form-item label="确认密码：">
                <el-input type="password" v-model="confirmPassword" @blur="confirmFunc" />
              </el-form-item>
              <el-form-item>
                <input type="checkbox" id="register-agree" name="agreement" v-model="rAgree">
                <label for="register-agree">同意<a>用户协议</a>与<a>隐私条款</a></label>
              </el-form-item>
              <el-row justify="center">
                <el-button :disabled="!(emailOK && rPassword && confirmPassword && rAgree)" type="primary" @click="register">
                  注册
                </el-button>
              </el-row>
            </el-form>
          </el-tab-pane>
        </el-tabs>
      </el-col>
  
    </div>
  </template>
  
  <script>
  
  import { reactive, toRefs, ref , getCurrentInstance} from "vue";
  import { ElMessage, ElMessageBox } from "element-plus";
  import axios from "axios";
  export default {
    setup(props, context) {
      const cns = getCurrentInstance()
      const url = cns.appContext.config.globalProperties.$url
      const transportLoginInfoToParent = () => {
        context.emit('loginInfo', true)
      }
      const loginForm = reactive({
        email: '',
        emailOK: false,
        password: '',
        isAgree: 0,
      });
      const registerForm = reactive({
        rEmail: "",
        rPassword: "",
        confirmPassword: "",
        identifyCode: "",
        rAgree: 0,
      });
      const tipToRegister = () => {
        ElMessage({
          message: '邮箱未注册',
          grouping: true,
          type: 'error',
          duration: 1000,
          center: true
        })
      };
      const tipRegistered = () => {
        ElMessage({
          message: '邮箱已注册',
          grouping: true,
          type: 'warning',
          duration: 1000,
          center: true
        })
      };
      // 登录
      function login() {
        axios.post(url + "/user/check", {
          email: loginForm.email,
          password: loginForm.password
        }).then(response => {
          const OK = response.data.code == "200" ? true:false;
          if (OK) {
            cns.appContext.config.globalProperties.$user.userName = loginForm.email
            cns.appContext.config.globalProperties.$user.token = response.data.data
            console.log("token")
            console.log(cns.appContext.config.globalProperties.$user.token)
            cns.appContext.config.globalProperties.$user.isLogin = true
            ElMessage({
              'message': '登录成功',
              'type': 'success'
            })
            let userInfo = {
              isLogin: true,
              manage: true,
              token:response.data.data,
              name: loginForm.email
            }
            localStorage.setItem('userInfo', JSON.stringify(userInfo))
            transportLoginInfoToParent()
          } else {
            ElMessage({
              message: '密码错误！',
              type: 'error',
              grouping: true
            })
          }
        })
      }
      function loginCheck() {
        console.log("登录中...");
        axios.post(url + "/user/search", {
          email: loginForm.email
        }).then(response => {
          const json = response.data
          if (json.data) {
            login();
          } else {// 未注册
            tipToRegister()
          }
        })
      }
      // 注册
      function register() {
        console.log("注册...");
        axios.post(url + "/user/search", {
            email: registerForm.rEmail,
        }).then(response => {
          const pwd = response.data.data
          if (pwd) {// 已注册的邮箱
            tipRegistered()
            return false
          } else {
            axios.post(url + '/user/insert', {
              email: registerForm.rEmail,
              password: registerForm.confirmPassword
            })
            registerSuccess()
          }
        });
      }
      // 注册成功提示
      function registerSuccess() {
        ElMessageBox.alert('注册成功！', '', {
          confirmButtonText: '好的',
        })
      }
      // 获取验证码
      function getIdentifyCode() {
        console.log("获取验证码");
        registerForm.identifyCode = 'nkcs'
      }
      function checkEmailFormat(value) {
        if (value.match(/^\w+@\w+\.\w+$/i)) {
          loginForm.emailOK = true
          return true
        }
        loginForm.emailOK = false
        ElMessage({
          message: '邮箱格式有误',
          grouping: true,
          type: 'error',
          duration: 1000,
          center: true
        })
        return false
      }
      const confirmFunc = () => {
        if (registerForm.confirmPassword !== registerForm.rPassword)
          ElMessage.error("密码与确认密码不一致.").duration=100;
      };
      return {
        ...toRefs(loginForm),
        ...toRefs(registerForm),
        login,
        loginCheck,
        register,
        getIdentifyCode,
        confirmFunc,
        checkEmailFormat,
        transportLoginInfoToParent
      };
    },
  };
  </script>
  
  
  <style scoped>

  
  el-form-item {
    display: flex;
    justify-items: auto;
  }
  </style>
  