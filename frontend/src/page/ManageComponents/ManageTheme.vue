<template>
    <div class="main planCenterColumn">
        <div class="theme planCenterColumn">
            <div>主题</div>
            <input type="text" v-model="Theme">
        </div>
        <div class="carousel-chart planCenterColumn">
            <div>选择轮播图</div>
            <div class="planCenterRow">
            <div v-for="Chart in this.$data.carouselChart" :key="Chart" class="planCenterRow chose-chart">
                <div>{{Chart.name}}</div>
                <input type="file" accept="image/*" @change="changeChart($event,Chart)"  class="input-file">
            </div>
            </div>
        </div>
        <div class="backgrounds planCenterColumn">
            <div v-for="background in this.$data.backgrounds" :key="background" class="planCenterRow chose-file">
            <div>{{background.name}}</div>
            <input type="file" accept="image/*" @change="changeChart($event,background)" class="input-file">
        </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
export default ({
    name:"ManageTheme",
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
                let carouselChartFiles = []
                let backgroundsFiles = []
                for(const element of this.$data.carouselChart){
                    carouselChartFiles.push(element.file[0])
                }
                for(const element of this.$data.backgrounds){
                    backgroundsFiles.push(element.file[0])
                }
                const that = this
                axios({
                url: this.$url + "/ContentManagement/Theme",
                method: "post",
                headers: {
                  "Content-Type": "multipart/form-data",
                },
                data: {
                    "id":this.$user.userName,
                    "token":this.$user.token,
                    "theme":this.$data.Theme,
                    "carouselChartFiles":carouselChartFiles,
                    "backgroundsFiles":backgroundsFiles,
                },
                }).then((response) => {
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
                  this.ctx.emit('askForShowConfirmTable',"提交主题")
                break
            case "0":
                this.clear()
                break
           }
        },
    },
    data() {
        return{
            Theme:"",
            carouselChart:[
                            {name:"图一",file:""},
                            {name:"图二",file:""},
                            {name:"图三",file:""},
                            {name:"图四",file:""},
                            {name:"图五",file:""}
                        ],
            backgrounds:[
                            {name:"选择主页背景",file:""},
                            {name:"推荐界面背景",file:""},
                            {name:"视频播放背景",file:""}
                        ]
        }
    },
    methods : {
        changeChart:function(event,Chart){
            Chart.file = event.target.files
        },
        clear:function(){
            this.$data.Theme = ""
            this.$data.carouselChart = [
                            {name:"图一",file:""},
                            {name:"图二",file:""},
                            {name:"图三",file:""},
                            {name:"图四",file:""},
                            {name:"图五",file:""}
                            ]
            this.$data.backgrounds =[
                            {name:"选择主页背景",file:""},
                            {name:"推荐界面背景",file:""},
                            {name:"视频播放背景",file:""}
                             ]
        },
        check:function(){
            if(this.$user.userName==null){
                alert("登录已过期")
                return false
            }
            if(this.$data.Theme==""){
                alert("请填写主题")
                return false
            }
            for(const element of this.$data.carouselChart){
                if(element.file==""){
                    alert("未选择"+element.name)
                    return false
                }
            }
            for(const element of this.$data.backgrounds){
                if(element.file==""){
                    alert("未选择"+element.name)
                    return false
                }
            }
            return true
        }
    }
})
</script>

<style scoped>
*{
  color: rgb(87, 71, 50);
}
.main {
    width: 100%;
    height: 100%;
    background-color: whitesmoke;
}
.planCenterColumn {
    display: flex;
    flex-direction: column;
    align-items: center;
}
.planCenterRow {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}
.input-file {
    width: 10vw;
}
.chose-file {
    width: 25vw;
    height: 8vh;
}
.chose-chart {
    width: 15vw;
    height: 8vh;
}
.theme {
    height: 20vh;
    margin-top: 5vh;
    margin-left: 3vw;
}
.carousel-chart {
    margin-left: 3vw;
}
.backgrounds {
    margin-left: 3vw;
}
</style>