<template>
  <div class="message">
    <h2>评论</h2>
    <div class="input-msg">
      <textarea name="" id="msg" cols="50" rows="5" v-model.trim="content" @keyup.enter="sendMsg" placeholder="请输入评论内容"></textarea>
      <button class="send-msg" @click="sendMsg">发送留言</button>
    </div>
    <div class="history">
      <div class="msg-item" v-for="msg in msgList" :key="msg.id">
        <img class="header-img" :src="msg.img" alt="">
        <div>
          <h5>{{msg.author}}  <small>{{msg.time}}</small></h5>
          <div class="content">
            <p class="cont">{{msg.content}}</p>
            <p class="control">
              <img src="../imgs/iconPraise.png" @click="commentLike(msg)"/>
              <span class="fa fa-thumbs-up delete" >{{
                  msg.likes
                }}</span>
<!--              <button class="btn btn-edit"  @click="editMsg(msg)">编辑</button>-->
<!--              <button class="btn btn-del" @click="delMsg(msg.id)">删除</button>-->
            </p>
          </div>

        </div>
      </div>

    </div>
  </div>
</template>

<script>
// 限于纯前端测试时使用
import m1 from "../imgs/ava1.jpg"
import m2 from "../imgs/ava5.jpg"
import m3 from "../imgs/ava9.jpg"
export default {
  created(){
    // 当前实例创建时，模拟从接口获取数据
    this.msgList=[
      {id:3,author: "海松",time:"2022-12-12 9:45",img:m1,content: "看完了，有点震撼，太有感染力了，看到最后有点感动。面对民间美术的逐渐消失，其实那些民间艺术家们一直在行动，在改变。我们青年人也应该对了解民间艺术的美和他承载的意义，这样代代相传。其实，剪纸艺术就是一种匠心精神，只有精心雕琢去创造才会有那么多的好作品。正如郭沫若先生所说：美在民间永不朽。",likes:9},
      {id:2,author: "昆区传承人-江溪",time:"2022-12-13 9:00",img:m2,content:"希望越来越多的人重视中国非遗文化，发扬光大！加油！",likes:9},
      {id:1,author: "思而学",time:"2022-12-14 13:55",img:m3,content:"看到我们泱泱大国的有如此多的非物质文化遗产，我感到非常自豪！",likes:9},
    ];
  },
  data(){
    return{
      // 双向绑定的留言内容
      content: "",
      // 留言内容
      msgList: [],
      // 声明编辑的id
      editId:""
    }
  },
  methods: {
    sendMsg () {
      // console.log("用户准备发表留言",this.content)
      if(this.content===""){
        alert("留言内容为空")
        return
      }
      if(this.editId){
        // 拆分数据
        let [content]=this.content.split(" ")
        let index = this.msgList.findIndex(msg=>msg.id === this.editId)
        this.msgList[index].content=content
        // 清空文本域、清空editId
        this.content=""
        this.editId=""
      }else {
        // 创建留言对象
        let id=this.msgList.length>0?this.msgList[0].id+1:1
        let m={id,author:"豆子",time: Date.now(),img:m4,content:this.content}
        // 添加留言
        this.msgList.unshift(m)
        // 清空留言框
        this.content=""
      }
    },
    // 点赞
    commentLike(obj) {
      var _this = this;
      obj.likes++;
    },
    editMsg(msg) {
      // 给编辑的id赋值
      this.editId = msg.id
      // 给输入框绑定的变量赋值（展示编辑数据）
      this.content = msg.content
    },
    delMsg(id) {
      // 二次确认
      const result = confirm("确认删除？")
      if (!result) return
      // 删除
      this.msgList = this.msgList.filter(msg => msg.id !== id)
      // 防止点击编辑之后在点击删除按钮出现的bug
      this.editId=""
      this.content=""
    },
  },
  filters: {
    formatTime:value=>{
      const date=new Date(value)
      return `${date.getFullYear()}年${(date.getMonth()+1).toString().padStart(2,0)}月${date.getDate().toString().padStart(2,0)}日 ${date.getHours().toString().padStart(2,0)}时${date.getMinutes().toString().padStart(2,0)}分${date.getSeconds().toString().padStart(2,0)}秒`
    }
  }
}
</script >

<style scoped>
.message h2{
  margin-top: 9vh;
  margin-left: 50vh;
  font-size:22px;
  font-family: "Arial Black";
}
.input-msg{
  width: 80%;
  margin: 10px auto;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}
#msg{
  width: 70%;
  /*height: 180px;*/
  padding: 10px;
  outline: none;
  border: 1px solid rgba(219, 73, 73, 0.466);
  border-radius: 5px;
  resize: none;
  display: block;
  margin: 10px auto;
  font-size: 18px;
}
.send-msg{
  width: 150px;
  height: 40px;
  border: none;
  background-color: #ff9b68;
  color: wheat;
  border-radius: 20px;
  cursor: pointer;
  font-size: 18px;
}
.send-msg:hover{
  background-color: rgb(238, 129, 70);
  color: #a15237;
}
.history{
  width: 80%;
  padding: 20px 10px;
  margin: 10px auto;
  background-color: #fff;
}
.msg-item{
  display: flex;
  padding: 10px;
  border-bottom: 1px dashed #888;
  margin-left: 4vh;
}
.msg-item img{
  width: 50px;
  height: 50px;
  border-radius: 10px;
}

.msg-item div{
  margin-left: 10px;
  width: 100%;
  font-family: "Arial Black";
}
.msg-item div h5{
  font-size: 15px;

}
.msg-item div h5 small{
  font-size: 12px;
  color: #888;
  font-weight: 600;
  margin-left: 20px;
}
.msg-item div .content{
  display: flex;
}
.msg-item div p.cont{
  width: 80%;
  font-size: 16px;
  color: #5e5e5e;
  margin: 10px 0;
  word-wrap: break-word;
  word-wrap: break-word;
  white-space: pre-wrap;
  min-height: 50px;
  /* border: 1px solid red; */
}
.msg-item div p.control{
  width: 20%;
  display: flex;
  justify-content: flex-end;
  margin-right: 2vh;
}
.control img{
  width:20px;
  height: 20px;
  margin-right: 2vh;
}
.btn{
  width: 80px;
  height: 30px;
  border: 1px solid #888;
  border-radius: 15px;
  cursor: pointer;
}
.btn-edit{
  background-color: rgb(20, 187, 247);
}
.btn-edit:hover{
  background-color: rgb(17, 148, 196);
  color: #fff;
}
.btn-del{
  background-color: rgb(250, 24, 92);
}
.btn-del:hover{
  background-color: rgb(192, 21, 61);
  color: #fff;
}
</style>