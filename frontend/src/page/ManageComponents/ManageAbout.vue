<template>
    <div class="main">
        <div class="topic planCenterColumn">
            <div class="planCenterColumn">
                <div>主题</div>
                <input type="text" v-model="this.$data.theme">
                <div>标题</div>
                <input type="text" v-model="this.$data.title">
            </div>
            <div class="planCenterColumn">
                 <div>内容</div>
                 <textarea name="" id="" cols="100" rows="10" v-model="this.$data.content"></textarea>
            </div>
            <div class="planCenterRow">
                <div class="imgTip">背景图</div>
                <input type="file" accept="image/*" @change="changeTitleImg($event,null,'background')">
            </div>
        </div>
        <div class="content planCenterColumn">
            <div v-for="content in this.$data.contentList" :key="content" class="planCenterColumn">
                <div>内容编号{{content.number}}</div>
                <div class="planCenterRow">
                <div class="imgTip">图片一</div>
                <input type="file" accept="image/*" @change="changeTitleImg($event,content,'img1')">
                </div>
                <div class="planCenterRow">
                <div class="imgTip">图片二</div>
                <input type="file" accept="image/*" @change="changeTitleImg($event,content,'img2')">
                </div>
                <div class="planCenterRow">
                <div class="imgTip">图片三</div>
                <input type="file" accept="image/*" @change="changeTitleImg($event,content,'img3')">
                </div>
                <div class="planCenterColumn">
                <div>介绍</div>
                 <textarea name="" id="" cols="100" rows="10" v-model="content.content"></textarea>
                </div>
            </div>
        </div>
        <div class="addContent planCenterRow">
            <div class="planCenterRow addContent-button-contaner">
                <div class="addContent-button planCenterRow" 
                @click="addContent" 
                @mousemove="focusAdd('增加')"
                @mouseleave="blurAdd('增加')"
                :style="this.$data.addContentColor"
                >
                增加内容
                </div>
            </div>
            <div class="planCenterRow addContent-button-contaner">
                <div class="addContent-button planCenterRow"
                 @click="deleteContent" 
                 @mousemove="focusAdd('减少')"
                 @mouseleave="blurAdd('减少')"
                 :style="this.$data.deleteContentColor">减少内容
                </div>
            </div>
        </div>
    </div>
</template>

<script>
//主题、内容、背景图片          图片1~3 介绍
import axios from "axios";
import { formEmits } from "element-plus";
export default ({
    name:"MamanageAbout",
    data(){
        return{
            theme:"",
            title:"",
            topicImg:"",
            contentList:[
            {img1:"",img2:"",img3:"",content:"",number:1},
            {img1:"",img2:"",img3:"",content:"",number:2},
            ],
            content:"",
            addContentColor:"background-color: whitesmoke;",
            deleteContentColor:"background-color: whitesmoke;",
        }
    },
    props:{
        op: { type: Object, default: {name:"",func:""} }
    },
    emits:['askForShowConfirmTable'],
    setup(props,ctx){
        return{
            ctx
        }
    },
    watch:{
        op() {
           switch(this.op.func){
            case "confirm":
                let uploadContentList = []
                let numbers = []
                let uploadImnFiles = []
                const that = this
                for (const element of this.$data.contentList) {
                    uploadContentList.push(element.content)
                    numbers.push(element.number)
                    uploadImnFiles.push(element.img1[0])
                    uploadImnFiles.push(element.img2[0])
                    uploadImnFiles.push(element.img3[0])
                }
                axios({
                url: this.$url + "/ContentManagement/About",
                method: "post",
                headers: {
                  "Content-Type": "multipart/form-data",
                },
                data: {
                    "id":this.$user.userName,
                    "token":this.$user.token,
                    "theme":this.$data.theme,
                    "title":this.$data.title,
                    "content":this.$data.content,
                    "backImg":this.$data.topicImg[0],
                    "numbers":numbers,
                    "contentList":uploadContentList,
                    "imgFiles":uploadImnFiles
                },
                }).then((response) => {
                  console.log(response.data);
                  if(response.data.msg!="ok"){
                    this.$message({
                        message: '提交失败',
                        type: 'error'
                    })
                  }else{
                    this.$message({
                        message: '提交成功',
                        type: 'success'
                    })
                    that.clear()
                  }
               });
               this.$message({
                      message: '提交中,请等待...',
                      type: 'success'
                })
                break
            case "1":
                if(this.check())
                this.ctx.emit('askForShowConfirmTable',"提交关于界面")
                break
            case "0":
                this.clear()
                break
           }
        },
    },
    methods : {
        changeTitleImg:function(event,obj,chose){
            console.log(event.target.files)
            switch(chose){
                    case "img1":
                    obj.img1 = event.target.files
                    break
                    case "img2":
                    obj.img2 = event.target.files
                    break
                    case "img3":
                    obj.img3 = event.target.files
                    break
                    case "background":
                        this.$data.topicImg = event.target.files
                    break
            }
        },
        clear:function(){
            this.$data.theme = ""
            this.$data.title = ""
            this.$data.content = ""
            this.$data.topicImg = ""
            this.$data.contentList = [
            {img1:"",img2:"",img3:"",content:"",number:1},
            {img1:"",img2:"",img3:"",content:"",number:2},
            ]
        },
        check:function(){
            if(this.$user.userName==null){
                alert("登录已过期")
                return false
            }
            if(this.$data.theme==""){
                alert("请填写主题")
                return false
            }
            if(this.$data.title==""){
                alert("请填写标题")
                return false
            }
            if(this.$data.content==""){
                alert("请填写内容")
                return false
            }
            if(this.$data.topicImg==""){
                alert("请填选择背景图片")
                return false
            }
            for(const element of this.$data.contentList){
                let info = ""
                if(element.img1==""){
                    info = "图片一"
                }else if(element.img2=="") {
                    info = "图片二"
                }else if(element.img3=="") {
                    info = "图片三"
                }else if(element.content=="") {
                    info = "介绍"
                }
                if(info!=""){
                    alert("请填写内容编号为"+element.number+"的"+info)
                    return false
                }
            }
            return true
        },
        addContent:function(){
            this.$data.contentList.push({img1:"",img2:"",img3:"",content:"",number:this.$data.contentList.length+1})
        },
        deleteContent:function(){
            if(this.$data.contentList.length>2){
            this.$data.contentList = this.$data.contentList.slice(0,this.$data.contentList.length-1)
            }
            else{
                alert("内容至少为两项")
            }
        },
        focusAdd:function(name){
        if(name=='增加'){
            this.$data.addContentColor = "background-color: rgb(204, 204, 204);"
        }else{
            this.$data.deleteContentColor = "background-color: rgb(204, 204, 204);"
        }
       },
       blurAdd:function(name){
        if(name=='增加'){
            this.$data.addContentColor = "background-color: whitesmoke;"
        }else{
            this.$data.deleteContentColor = "background-color: whitesmoke;" 
        }
       }
    }
})
</script>

<style scoped>
*{
  color:rgb(93, 76, 54);
}
.main {
    width: 100%;
    height: 100%;
    background-color: whitesmoke;
    overflow-y: scroll;
}
.topic {
    width: 30vw;
    height: 40vh;
    margin-left: 25vw;
    margin-top: 18vh;
}
.content {
    width: 30vw;
    margin-left: 25vw;
    margin-top: 15vh;
}
.planCenterColumn {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  padding-top: 3vh;
}
.planCenterRow {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}
.imgTip {
    margin-left: 9vw;
    width: 10vw;
}
.addContent {
    width: 30vw;
    margin-left: 25vw;
    margin-top: 5vh;
    height: 10vh;
    margin-bottom: 5vh;
    background-color: rgb(171, 171, 171);
}
.addContent-button {
    width: 8vw;
    height: 8vh;
    background-color: whitesmoke;
}
.addContent-button-contaner {
    width: 50%;
    height: 100%;
}
</style>