<template>

  <body>
    <div class="img-box" :style="back">
      <img :src="back_ground" alt="" style="width: 80vw; height: 36vw" class="image" />
      <div class="title">
        <h1>{{ title }}</h1>
      </div>
      <div class="content">
        {{ content }}
      </div>
    </div>

    <div class="show">
      <div class="show-board" v-for="index in show_board" :key="index">
        <div class="images">
          <img :src="index.image1" style="width: 24vw; height: 16vw" class="image" />
          <img :src="index.image2" style="width: 24vw; height: 16vw" class="image" />
          <img :src="index.image3" style="width: 24vw; height: 16vw" class="image" />
        </div>
        <div class="introduce">{{ index.introduce }}</div>
      </div>
    </div>
  </body>
</template>
<script>
import axios from "axios";
import { reactive, getCurrentInstance } from "vue";
import { themeBus } from '../eventBus.js';
import { watch } from 'vue';

export default {
  data () {
    return {
      // theme_name: "西河大鼓",
      theme_name: "",
      back_ground: "",
      title: "",
      content: "",
      show_board: null,
      back: {
        // 背景图片地址
        backgroundImage: "url(" + this.$data.back_ground + ")",
      },
    };
  },
  // 生命周期函数：在渲染成HTML或者模版编译进路由前调用created
  created () {
    this.setup();
  },
  methods: {
    setup () {
      const cns = getCurrentInstance()
      let url = cns.appContext.config.globalProperties.$url
      if ((cns.appContext.config.globalProperties.$theme = localStorage.getItem('theme')) == null) {
        cns.appContext.config.globalProperties.$theme = "国画颜料制作"
      }
      axios({
        url: url + "/GetAboutInfo",
        method: "post",
        data: {
          theme_name: cns.appContext.config.globalProperties.$theme,
        },
      }).then((response) => {
        console.log(response.data);
        this.$data.back_ground = response.data.background;
        this.$data.title = response.data.title;
        this.$data.content = response.data.description;
        this.$data.show_board = response.data.show;
      });
      watch(themeBus, (newTheme) => {
        loadThemeData(newTheme);
      });
      const loadThemeData = (theme) => {
        console.log(theme)

        axios({
          url: url + "/GetAboutInfo",
          method: "post",
          data: {
            theme_name: theme,
          },
        }).then((response) => {
          console.log(response.data);
          this.$data.back_ground = response.data.background;
          this.$data.title = response.data.title;
          this.$data.content = response.data.description;
          this.$data.show_board = response.data.show;
        });
      }
      return {};
    },
    dianji () {
      console.log(this.$data.back_ground);
      console.log(this.$data.title);
      console.log(this.$data.show_board);
    },
  },
};
</script>

<style scoped>
* {
  margin: 0px;
  padding: 0px;
}

body {
  margin: 0px;
  padding: 0px;
  display: flex;
  margin-top: 10vh;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-image: url("../imgs/back.jpg");
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: 100% 100%;
}

.img-box {
  /* width: 160vh;
  height: 70vh; */
  margin: 0px;
  padding: 0px;
  width: 80vw;
  height: 36vw;
  background-size: cover;
  background-repeat: no-repeat;
  position: relative;
}

.img-box .title {
  color: aliceblue;
  margin-left: 6vw;
  margin-top: -28vw;
  position: absolute;
}

.img-box .content {
  margin-left: 6vw;
  margin-top: -20vw;
  width: 30vw;
  color: aliceblue;
  position: absolute;
  font-size: large;
}

.show {
  margin-top: 4vw;
  padding: 0px;
  width: 80vw;
  height: auto;
}

.show .show-board {
  width: auto;
  height: auto;
  margin-top: 4vw;
  border: 2px solid #faf9de;
  border-radius: 10px;
  /* background: url("../imgs/board-back.jpg"); */
  background: url("../imgs/board-back.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}

.show .show-board .images {
  margin: 1vw;
  border: 2px solid #faf9de;
  border-radius: 10px;
}

.show .show-board .images img {
  margin-left: 1vw;
  /* border:2px solid #FaF9de;
  border-radius: 10px; */
}

.show .show-board .introduce {
  margin: 1vw;
  margin-left: 2vw;
  /* border:2px solid #FaF9de;
  border-radius: 10px; */
}

.image {
  width: 100%;
  height: 100%;
}

/* .main {
  width: 90vw;
  height: 40vw;
  background-image: url("../imgs/back.png");
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-size: cover;
} */
</style>
