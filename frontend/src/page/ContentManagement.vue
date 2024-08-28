<template>
    <div class="body">
        <div class="Function-Menu">
            <div class="Function-title">功能菜单</div>
            <div v-for="inform in this.MenuItem.inform" :key="inform" class="Function-item" @click="choseItem(inform)"
                @mouseover="focusItem(inform)" @mouseleave="blurItem(inform)" :style=inform.color>
                {{ inform.name }}
            </div>
        </div>
        <div class="Function-Operation-Container">
            <div class="Operation">
                <ManageTheme v-if="this.MenuItem.Mamanage[0]" :op="this.$data.themeOp"
                    @askForShowConfirmTable="childAskForShowConfirmTable"></ManageTheme>
                <ManageVideo v-if="this.MenuItem.Mamanage[1]" :op="this.$data.themeOp"
                    @askForShowConfirmTable="childAskForShowConfirmTable"></ManageVideo>
                <ManagePersonPage v-if="this.MenuItem.Mamanage[2]" :op="this.$data.themeOp"
                    @askForShowConfirmTable="childAskForShowConfirmTable"></ManagePersonPage>
                <MamanageAbout v-if="this.MenuItem.Mamanage[3]" :op="this.$data.themeOp"
                    @askForShowConfirmTable="childAskForShowConfirmTable"></MamanageAbout>
            </div>
        </div>
        <div class="confirm-table" v-show="$data.showConfirmTable">
            <div class="op-befor-submission">
                <div class="submission-title befor-submission-item">当前提交项目为：{{ this.$data.SubmitItem }}</div>
                <div class="befor-submission-item">
                    <input type="text" v-model=this.$data.VerificationCode class="input-verification-code"
                        placeholder="输入验证码">
                    <h5 class="get-verification-code" v-if="!this.$data.getVerificationCode"
                        @click="userGetVerificationCode" @mousemove="focusUserGetVerificationCode"
                        @mouseleave="blurUserGetVerificationCode" :style="this.$data.getVerificationCodeColor">点击以获取验证码</h5>
                    <h5 class="get-verification-code" v-if="this.$data.getVerificationCode">已发送{{ this.$data.validTime
                    }}s内有效
                    </h5>
                </div>
            </div>
            <div class="op-button">
                <div class="submission" @click="confirmSubmission" @mousemove="focusSubmission('确认')"
                    @mouseleave="blurSubmission('确认')" :style=this.$data.confirmSubmitColor>确认提交</div>
                <div class="submission" @click="cancelSubmission" @mousemove="focusSubmission('取消')"
                    @mouseleave="blurSubmission('取消')" :style=this.$data.cancelSubmitColor>取消提交</div>
            </div>
        </div>
        <div class="Function-Options-Container" v-if="this.MenuItem.Options[this.$data.currentItem]">
            <div class="show-hiden" @click="showHidenFOC">
                <div :class=$data.showFunctionOptionsClass>
                </div>
            </div>
            <div class="Function-Options" v-show="$data.showFunctionOptions">
                <div class="Function-title">
                    {{ this.MenuItem.Options[this.$data.currentItem].title }}
                </div>
                <div v-for="op in this.MenuItem.Options[this.$data.currentItem].op" :key="op" class="Function-item"
                    @click="choseFunction(op)" @mouseover="focusFunction(op)" @mouseleave="blurFunction(op)"
                    :style=op.color>
                    {{ op.name }}
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import { onMounted, reactive, ref, getCurrentInstance } from 'vue';
import MamanageAbout from "./ManageComponents/ManageAbout.vue";
import ManageTheme from "./ManageComponents/ManageTheme.vue";
import ManageVideo from "./ManageComponents/ManageVideo.vue";
import ManagePersonPage from "./ManageComponents/ManagePersonPage.vue";
import { isNumber } from '@vueuse/shared';
export default {
    components: {
        MamanageAbout,
        ManageTheme,
        ManageVideo,
        ManagePersonPage
    },
    setup() {
        const cns = getCurrentInstance()
        let url = cns.appContext.config.globalProperties.$url
        let MenuItem = reactive({
            inform: [],
            Options: [],
            Mamanage: [false, false, false, false]
        })
        onMounted(async () => {
            axios.post(url + "/ContentManagement/GetRole", {
                "UserPowerCheck": {
                    "id": cns.appContext.config.globalProperties.$user.userName,
                    "token": cns.appContext.config.globalProperties.$user.token,
                }
            }).then(response => {
                if (response.data.data == null) {
                    alert("用户未分配角色！")
                }
                else {
                    let opColor = "background-color: rgb(236, 207, 170);"
                    let Options_list1 = ["修改操作",
                        { name: "重置主题", color: opColor, chose: false, function: "0" },
                        { name: "提交", color: opColor, chose: false, function: "1" }]
                    let Options_list2 = ["审核操作",
                        { name: "审核通过", color: opColor, chose: false, function: "0" },
                        { name: "视频驳回", color: opColor, chose: false, function: "1" }]
                    let Options_list3 = ["修改操作",
                        { name: "重置文章", color: opColor, chose: false, function: "0" },
                        { name: "提交", color: opColor, chose: false, function: "1" }]
                    let Options_list4 = ["修改操作",
                        { name: "重置页面", color: opColor, chose: false, function: "0" },
                        { name: "提交", color: opColor, chose: false, function: "1" }]
                    for (const role of response.data.data) {
                        switch (role) {
                            case "visitor":
                                MenuItem.inform.push({ name: "个人文章", color: opColor, chose: false, content: 2 })
                                MenuItem.Options.push({ item: "个人文章", title: Options_list3[0], op: Options_list3.slice(1) })
                                break;
                            case "editor":
                                MenuItem.inform.push({ name: "视频审核", color: opColor, chose: false, content: 1 })
                                MenuItem.Options.push({ item: "视频审核", title: Options_list2[0], op: Options_list2.slice(1) })
                                MenuItem.inform.push({ name: "主题风格", color: opColor, chose: false, content: 0 })
                                MenuItem.Options.push({ item: "主题风格", title: Options_list1[0], op: Options_list1.slice(1) })
                                // MenuItem.inform.push({name:"关于界面",color:opColor,chose:false,content:3})
                                // MenuItem.Options.push({item:"关于界面",title:Options_list4[0],op:Options_list4.slice(1)})
                                break;
                        }

                    }
                    if (MenuItem.inform.length > 0) {
                        MenuItem.inform[0].chose = true
                        MenuItem.inform[0].color = "background-color: rgb(225, 160, 125);"//“视频审核的颜色
                        MenuItem.Mamanage[MenuItem.inform[0].content] = true
                    }

                }
            })
        })
        return {
            MenuItem
        }
    },
    data() {
        return {
            showFunctionOptions: true,
            showConfirmTable: false,
            showFunctionOptionsClass: "show-hide-img show-img",
            currentItem: 0,
            SubmitItem: "this.MenuItem.inform[0].name",
            VerificationCode: "",
            confirmSubmitColor: "background-color: whitesmoke;",
            cancelSubmitColor: "background-color: whitesmoke;",
            themeOp: { name: "", func: "" },
            getVerificationCode: false,
            validTime: "",
            getVerificationCodeColor: "color: black;",
            isSubmitting: false, // 防重复提交标志位
        }
    },
    methods: {
        showHidenFOC: function () {
            this.$data.showFunctionOptions = !this.$data.showFunctionOptions;
            this.$data.showFunctionOptionsClass = this.$data.showFunctionOptions ? "show-hide-img show-img" : "show-hide-img hide-img";
        },
        choseItem: function (Item) {
            if (this.$data.showConfirmTable) {
                alert("请先取消或确认当前提交")
                return
            }
            for (const element of this.MenuItem.inform) {
                if (element.chose) {
                    element.chose = false;
                    element.color = "background-color: rgb(236, 207, 170);"
                    break
                }
            }
            Item.chose = true
            Item.color = "background-color: rgb(225, 160, 125);"
            this.MenuItem.Mamanage = [false, false, false, false]
            let count = 0
            for (const element of this.MenuItem.Options) {
                if (element.item == Item.name) {
                    this.$data.currentItem = count
                    this.MenuItem.Mamanage[Item.content] = true
                    break
                }
                count++
            }

        },
        focusItem: function (op) {
            if (!op.chose) {
                op.color = "background-color: rgb(225, 160, 125);"//菜单选项被选中的颜色
            }
        },
        blurItem: function (op) {
            if (!op.chose) {
                op.color = "background-color: rgb(236, 207, 170);"
            }
        },
        choseFunction: function (op) {
            for (const element of this.MenuItem.Options[this.$data.currentItem].op) {
                if (element.chose) {
                    element.chose = false
                    element.color = "background-color: rgb(236, 207, 170);"
                    break
                }
            }
            op.chose = true
            this.$data.themeOp = { name: "", func: op.function }
        },
        childAskForShowConfirmTable: function (tip) {
            this.$data.SubmitItem = tip
            this.$data.showConfirmTable = true
        },
        focusFunction: function (op) {
            op.color = "background-color: rgb(225, 160, 125);"
        },
        blurFunction: function (op) {
            op.color = "background-color: rgb(236, 207, 170);"
        },
        // confirmSubmission: function () {
        //     if (this.$data.VerificationCode == "") {
        //         alert("请输入验证码！")
        //     } else {
        //         if (!isNaN(Number(this.$data.VerificationCode))) {
        //             this.$data.themeOp = { name: "", func: "confirm" }
        //             const that = this
        //             axios.post(this.$url + "/ContentManagement/checkVerificationCode", {
        //                 "UserPowerCheck": {
        //                     "id": this.$user.userName,
        //                     "token": this.$user.token,
        //                 },
        //                 "UserVerificationCode": {
        //                     "mail": this.$user.userName,
        //                     "Code": this.$data.VerificationCode
        //                 }
        //             }).then(response => {
        //                 if (response.data.data) {
        //                     that.$data.VerificationCode = ""
        //                     that.$data.showConfirmTable = false
        //                     clearInterval(that.timer)
        //                     that.$data.getVerificationCode = false
        //                     that.$data.getVerificationCodeColor = "color: black;"
        //                     that.$data.themeOp = { name: "", func: "confirm" }
        //                 }
        //                 else {
        //                     this.$message({
        //                         message: '验证码错误！',
        //                         type: 'error'
        //                     })
        //                 }
        //             })
        //         } else {
        //             this.$message({
        //                 message: '验证码错误！',
        //                 type: 'error'
        //             })
        //         }
        //     }
        // },
        confirmSubmission() {
        if (this.isSubmitting) {
            return; // 如果正在提交，直接返回
        }
        this.isSubmitting = true; // 设置标志位为true，表示开始提交

        if (this.VerificationCode === "") {
            alert("请输入验证码！");
            this.isSubmitting = false; // 重置标志位
            return;
        }

        if (!isNaN(Number(this.VerificationCode))) {
            axios.post(this.$url + "/ContentManagement/checkVerificationCode", {
                "UserPowerCheck": {
                    "id": this.$user.userName,
                    "token": this.$user.token,
                },
                "UserVerificationCode": {
                    "mail": this.$user.userName,
                    "Code": this.VerificationCode
                }
            }).then(response => {
                if (response.data.data) {
                    // 验证成功，处理提交
                    this.VerificationCode = "";
                    this.showConfirmTable = false;
                    clearInterval(this.timer);
                    this.getVerificationCode = false;
                    this.getVerificationCodeColor = "color: black;";
                    this.themeOp = { name: "", func: "confirm" };
                } else {
                    this.$message({
                        message: '验证码错误！',
                        type: 'error'
                    });
                }
                this.isSubmitting = false; // 重置标志位
            }).catch(error => {
                this.$message({
                    message: '请求失败，原因：' + error.message,
                    type: 'error'
                });
                this.isSubmitting = false; // 重置标志位
            });
        } else {
            this.$message({
                message: '验证码错误！',
                type: 'error'
            });
            this.isSubmitting = false; // 重置标志位
        }
    },
        cancelSubmission: function () {
            this.$data.pwd = ""
            this.$data.showConfirmTable = false
        },
        focusSubmission: function (name) {
            if (name == '确认') {
                this.$data.confirmSubmitColor = "background-color: rgb(204, 204, 204);"
            } else {
                this.$data.cancelSubmitColor = "background-color: rgb(204, 204, 204);"
            }
        },
        blurSubmission: function (name) {
            if (name == '确认') {
                this.$data.confirmSubmitColor = "background-color: whitesmoke;"
            } else {
                this.$data.cancelSubmitColor = "background-color: whitesmoke;"
            }
        },
        userGetVerificationCode: function () {
            const that = this
            this.$data.getVerificationCode = true
            axios.post(this.$url + "/ContentManagement/getVerificationCode", {
                UserPowerCheck: {
                    "id": this.$user.userName,
                    "token": this.$user.token,
                },
                "mail": this.$user.userName
            }
            ).catch(function (error) {
                this.$message({
                    message: '请求失败,权限不足',
                    type: 'error'
                })
                that.$data.getVerificationCode = false
            }).then(response => {
                if (response.data.data) {
                    that.$data.validTime = 60
                    that.timer = setInterval(() => {
                        that.$data.validTime -= 1
                        if (that.$data.validTime == 0) {
                            clearInterval(that.timer)
                            that.$data.getVerificationCode = false
                            that.$data.getVerificationCodeColor = "color: black;"
                        }
                    }, 1000)
                } else {
                    this.$message({
                        message: '获取验证码失败，原因：' + response.data.msg,
                        type: 'error'
                    })
                }
            })
        },
        focusUserGetVerificationCode: function () {
            this.$data.getVerificationCodeColor = "color: yellow;"
        },
        blurUserGetVerificationCode: function () {
            this.$data.getVerificationCodeColor = "color: black;"
        }
    },
    beforeDestroy() {
        clearInterval(this.timer)
    },

}

</script>

<style>
* {
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
}

.body {
    width: 100vw;
    height: 90vh;
    margin-top: 10vh;
    background-color: rgb(171, 171, 171);
}

.Function-Menu {
    width: 15vw;
    height: 90vh;
    background-color: white;
    position: fixed;
    display: flex;
    flex-direction: column;
    align-items: center;

}

.Function-title {
    color: white;
    width: 100%;
    height: 9vh;
    background-color: rgb(187, 137, 113);
    /*margin-top: 2vh;*/
    display: flex;
    align-items: center;
    justify-content: center;
    /*border: solid 2px black;*/
}

.Function-Options {
    background-color: #fff;
    width: 15vw;
    height: 40vh;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.Function-item {
    width: 80%;
    height: 6vh;
    margin-top: 2vh;
    display: flex;
    align-items: center;
    justify-content: center;
    color: rgb(93, 76, 54);
    /*border: solid 1px rgb(0,0,0);*/
    border-radius: 24px 0 0 24px;
    /*margin-left: 7vh;*/
}

.Function-Operation-Container {
    background-color: rgb(217, 185, 155);
    margin-left: 15vw;
    height: 90vh;
    width: 85vw;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    z-index: 100;
}

.Operation {
    background-color: whitesmoke;
    height: 85vh;
    width: 80vw;
}

.Function-Options-Container {
    background-color: rgb(204, 204, 204);
    height: 40vh;
    position: fixed;
    top: 30vh;
    right: 0;
    display: flex;
    flex-direction: row;
    align-items: center;
}

.show-hiden {
    background-color: rgba(125, 125, 125, 0.6);
    height: 40vh;
    display: flex;
    flex-direction: row;
    align-items: center;
}

.show-hide-img {
    width: 2vw;
    height: 2vw;
    background-repeat: no-repeat;
    background-size: cover;
}

.show-img {
    background-image: url("../icon/youzhankai.png");
}

.hide-img {
    background-image: url("../icon/zuozhankai.png");
}


.confirm-table {
    position: fixed;
    background: rgb(115, 115, 115);
    ;
    width: 30vw;
    height: 30vh;
    left: 40vw;
    top: 35vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    z-index: 100;
}

.submission {
    background-color: aqua;
    width: 6vw;
    height: 6vh;
    display: flex;
    align-items: center;
    margin-left: 2vw;
    padding-left: 1.5vw;
}

.op-befor-submission {
    background-color: rgb(115, 115, 115);
    ;
    width: 80%;
    height: 15.5vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.op-button {
    height: 8vh;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-left: -1vw;
}

.befor-submission-item {
    background-color: rgb(115, 115, 115);
    height: 5vh;
    width: 100%;
    margin-top: 1vh;
    display: flex;
    align-items: center;
    justify-content: center;
}

.get-verification-code {
    margin-left: 0.5vw;
    height: 3vh;
    width: 10vw;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 5px;
}

.input-verification-code {
    width: 5vw;
    height: 3vh;
}
</style>