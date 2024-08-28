<template>
    <div class="main planCenterRow" planCenterRow>
        <div class="upload-article">
      <el-form label-position="left" label-width="80px">
        <el-form-item label="标题：">
          <el-input type="text" v-model="this.$data.title"></el-input>
        </el-form-item>
        <el-form-item label="内容：">
          <el-input type="textarea" cols="100" rows="6" v-model="this.$data.content"></el-input>
        </el-form-item>
      </el-form>
    </div>
    </div>
</template>

<script>
import axios from "axios";
export default ({
    name:"ManagePersonPage",
    setup(props,ctx){
        return{
            ctx
        }
    },
    props:{
        op: { type: Object, default: {name:"",func:""} }
    },
    emits:['askForShowConfirmTable'],
    watch:{
        op() {
           switch(this.op.func){
            case "confirm":
            axios.post(this.$url + "/PersonalArticles/addPersonalArticles",
            {
                "UserPowerCheck":{
                        "id":this.$user.userName,
                        "token":this.$user.token,
                },
                "personalArticle":{
                    "title":this.$data.title,
                    "content":this.$data.content,
                    "author":this.$user.userName,
                }
            }
            ).then(response => {
                if(response.data.msg=="ok"){
                    this.$message({
                        message: '提交成功',
                        type: 'success'
                    })
                    this.$data.content=""
                    this.$data.title=""
                }else{
                    this.$message({
                        message: '提交失败',
                        type: 'error'
                    })
                }
            })
            this.$message({
                      message: '提交中,请等待...',
                      type: 'success'
                })
                break
            case "0":
                this.$data.content=""
                this.$data.title=""
                break
            case "1":
                if(this.$data.title==""){
                    this.$message({
                        message: '请填写标题',
                        type: 'error'
                    })
                }else if(this.$data.content==""){
                    this.$message({
                        message: '请填写内容',
                        type: 'error'
                    })
                }else{
                    this.ctx.emit('askForShowConfirmTable',"提交个人文章")
                }
            break
           }
        },
    },
    data(){
        return{
            title:"",
            content:"",
        }
    },
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
}
.planCenterRow {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}
</style>