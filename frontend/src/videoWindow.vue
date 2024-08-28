<template>
  <div class="main-video">
    <div class="video">
      <div class="video-show">
        <!--    poster：封面-->
        <vue3VideoPlay v-bind="options" :poster="options.imgUrl" />
      </div>
      <div class="comment-show">
        <Comment />
      </div>
    </div>
  </div>
</template>

<script>
import { useRoute } from "vue-router";
import { reactive } from "vue";
import Comment from "./components/Comment.vue";
export default {
  name: "videoWindow",
  components: {
    Comment,
  },
  setup() {
    let videoUrl = useRoute().params;
    // 视频播放器设置
    console.log(videoUrl);
    const options = reactive({
      width: "800px", //播放器高度
      height: "450px", //播放器高度
      color: "#409eff", //主题色
      title: "", //视频名称
      src: videoUrl.videoUrl, //视频源
      muted: false, //静音
      webFullScreen: false,
      imgUrl: videoUrl.imageUrl,
      speedRate: ["0.75", "1.0", "1.25", "1.5", "2.0"], //播放倍速
      autoPlay: false, //自动播放
      loop: false, //循环播放
      mirror: false, //镜像画面
      ligthOff: false, //关灯模式
      volume: 0.3, //默认音量大小
      control: true, //是否显示控制
      controlBtns: [
        "audioTrack",
        "quality",
        "speedRate",
        "volume",
        "setting",
        "pip",
        "pageFullScreen",
        "fullScreen",
      ], //显示所有按钮,
    });

    return {
      videoUrl,
      options,
    };
  },
  data() {
    return {};
  },
};
</script>
<style scoped>
.main-video {
  margin: 0;
  padding: 0;
}
.video {
  background-image: url("./imgs/plantCente.png");
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-size: cover;
}
.video-show {
  width: 100vw;
  height: 90vh;
  margin-top: 10vh;

  display: flex;
  justify-content: center;
  align-items: center;
}

.video-play {
  width: 80vw;
  height: 90vh;
}
</style>
