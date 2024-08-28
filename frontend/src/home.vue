<template>
  <div class="main" :style=Bg.url[0]>
    <div class="top-background Appicon">
      <swiper
          :modules="modules"
          :slides-per-view="1"
          :space-between="30"
          :loop="false"
          :navigation="true"
          :pagination="{ clickable: true }"
          :speed="2000"
          :autoplay="{
                    delay: 2000,
                    disableOnInteraction: true,
                    }"
          @swiper="onSwiper"
          @slideChange="onSlideChange"
      >
        <swiper-slide v-for="index in CarouselChartImg.inofrom" :key="index">
          <div class="imgbox">
            <img :src="index.url" class="image">
          </div>
        </swiper-slide>
      </swiper>
    </div>
    <div class="type-area"  :style=Bg.url[1]>
      <div class="video-boxs">
        <div class="video-control">
          <div :class="$data.renew"
               @click="renewVideo"
               @mouseover="renewFocuse"
               @mouseleave="renewBlur">
            <p class="renew-word">换一换</p>
            <img src="./icon/icon-test.png" alt="" class="renew-img">
          </div>
        </div>
        <div v-for="layer in VideoInterface.inofrom" :key="layer" class="video-box-layer">
          <div v-for="video in  layer" :key="video" class="video-box-per"
               @mouseover="show_video_marsk(video)"
               @mouseleave="hide_video_marsk(video)"
               @click="click_video_marsk(video)">
            <div v-show="video.focus=='true'" class="video-marsk">
              <img src="./icon/live.png" alt="" class="video-live">
            </div>
            <img :src="video.imgUrl" alt="" class="video-img">
            <p class="video-name">{{ video.videoName }}</p>
            <p class="upLoad-name">{{ video.upLoadNmae }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
import { ref, getCurrentInstance, reactive, onMounted } from 'vue';
import {useRouter} from 'vue-router'
import { themeBus } from './eventBus.js';
import { watch } from 'vue';
import {Swiper, SwiperSlide} from '../node_modules/swiper/vue/swiper-vue.js';
import "../node_modules/swiper/swiper-bundle.min.css"
import SwiperCore, {Navigation, Pagination, Scrollbar, A11y, Autoplay} from 'swiper';

SwiperCore.use([Navigation, Pagination, Scrollbar, A11y, Autoplay]);
export default {
  name: 'home',
  /* 页面初始化，加载轮播图以及视频封面等信息 */
  setup() {
    const cns = getCurrentInstance()
    let url = cns.appContext.config.globalProperties.$url
    let Bg = reactive({
      url: []
    })
    let CarouselChartImg = reactive({
      inofrom: []
    })

    let VideoInterface = reactive({
      inofrom: [[]]
    })
    onMounted(async () => {
      axios.get(url + "/VideoInterface", {}).then(response => {
        let i = 0
        response.data.data.forEach(element => {
          if (i % 4 == 0) {
            VideoInterface.inofrom.push([])
          }
          VideoInterface.inofrom[parseInt(i / 4)].push({
            imgUrl: element.pic_id,
            videoUrl: element.url,
            videoName: element.name,
            upLoadNmae: element.owner_id,
            focus: "false"
          })
          i++
        });
      })
      if((cns.appContext.config.globalProperties.$theme=localStorage.getItem('theme'))==null){
        cns.appContext.config.globalProperties.$theme="国画颜料制作"
      }
      console.log(cns.appContext.config.globalProperties.$theme)
      axios.post(url + "MainInterface/getBaseURL", {
          Theme:cns.appContext.config.globalProperties.$theme
        }).then(response => {
          CarouselChartImg.inofrom.push({url:response.data.data.carouselImg_1})
          CarouselChartImg.inofrom.push({url:response.data.data.carouselImg_2})
          CarouselChartImg.inofrom.push({url:response.data.data.carouselImg_3})
          CarouselChartImg.inofrom.push({url:response.data.data.carouselImg_4})
          CarouselChartImg.inofrom.push({url:response.data.data.carouselImg_5})
          Bg.url.push('background-image: url("'+response.data.data.backgroundImg+'")')
          Bg.url.push('background-image: url("'+response.data.data.videoRecommendImg+'")')
          console.log(CarouselChartImg)
        })
    })
    watch(themeBus, (newTheme) => {
      loadThemeData(newTheme);
    });
    const loadThemeData = (theme) => {

      axios.post(url + "MainInterface/getBaseURL", {
        Theme: theme
      }).then(response => {
        CarouselChartImg.inofrom = []; // 清空当前的图片数据
        Bg.url = []; // 清空当前背景数据

        CarouselChartImg.inofrom.push({ url: response.data.data.carouselImg_1 });
        CarouselChartImg.inofrom.push({ url: response.data.data.carouselImg_2 });
        CarouselChartImg.inofrom.push({ url: response.data.data.carouselImg_3 });
        CarouselChartImg.inofrom.push({ url: response.data.data.carouselImg_4 });
        CarouselChartImg.inofrom.push({ url: response.data.data.carouselImg_5 });
        Bg.url.push('background-image: url("' + response.data.data.backgroundImg + '")');
        Bg.url.push('background-image: url("' + response.data.data.videoRecommendImg + '")');
        console.log(CarouselChartImg);
      });
    };

    return {
      VideoInterface,
      CarouselChartImg,
      Bg,
      modules: [Navigation, Pagination, Scrollbar, A11y]
    };
    const onSwiper = (swiper) => {
    };
    const onSlideChange = () => {
    };
    return {
      VideoInterface,
      CarouselChartImg,
      onSwiper,
      onSlideChange,
      modules: [Navigation, Pagination, Scrollbar, A11y],
      Bg
    };
  },
// 监听 themeBus 的变化


  // watch: {
  //   currentTheme(newTheme) {
  //     // 主题变化时，调用 axios 刷新轮播图
  //     console.log("watch")
  //     this.updateCarousel(newTheme)
  //     async () => { 

  //     axios.post(url + "MainInterface/getBaseURL", {
  //         Theme:newTheme
  //       }).then(response => {
  //         CarouselChartImg.inofrom.push({url:response.data.data.carouselImg_1})
  //         CarouselChartImg.inofrom.push({url:response.data.data.carouselImg_2})
  //         CarouselChartImg.inofrom.push({url:response.data.data.carouselImg_3})
  //         CarouselChartImg.inofrom.push({url:response.data.data.carouselImg_4})
  //         CarouselChartImg.inofrom.push({url:response.data.data.carouselImg_5})
  //         Bg.url.push('background-image: url("'+response.data.data.backgroundImg+'")')
  //         Bg.url.push('background-image: url("'+response.data.data.videoRecommendImg+'")')
  //         console.log(CarouselChartImg)
  //       })}
  //   }
    
  // },
  data() {
    return {
      renew: "renew renew-blur",
      url: this.$url
    }
  },
  components: {//轮播图控件
    Swiper,
    SwiperSlide,
  },
  methods: {
    //鼠标移至视频上出现阴影
    show_video_marsk: function (video) {
      video.focus = 'true'
    },
    //鼠标离开视频上隐藏阴影
    hide_video_marsk: function (video) {
      video.focus = 'false'
    },
    //点击视频播放跳转页面
    click_video_marsk: function (video) {
      console.log(video)
      this.$router.push({
        name: 'videoWindow', params: {
          videoUrl: video.videoUrl,
          videoName: video.videoName,
          upLoadName: video.upLoadName,
          imageUrl: video.imgUrl
        }
      })
    },
    //重新加载视频资源
    renewVideo: function () {
      axios.get(this.url + "/VideoInterface", {}).then(response => {
        let i = 0
        this.VideoInterface.inofrom = []
        response.data.data.forEach(element => {
          if (i % 4 == 0) {
            this.VideoInterface.inofrom.push([])
          }
          this.VideoInterface.inofrom[parseInt(i / 4)].push({
            imgUrl: element.pic_id,
            videoUrl: element.url,
            videoName: element.name,
            upLoadNmae: element.owner_id,
            focus: "false"
          })
          i++
        });
      })
    },
    //鼠标移至刷新栏出现边框
    renewFocuse: function () {
      this.renew = "renew renew-focuse"
    },
    //鼠标离开刷新栏隐藏边框
    renewBlur: function () {
      this.renew = "renew renew-blur"
    }
  }
}
</script>

<style scoped>
* {
  margin: 0px;
  padding: 0px;
}

.main {
  width: 100vw;
  height: auto;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-size: cover;
}

/* 版心大小、颜色设置 */
.type-area {
  width: 80vw;
  height: 100vh;
  margin-left: 10vw;
  background-color: whitesmoke;
  background-repeat: no-repeat;
  background-size: cover;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

/* 轮播图大小、图片颜色设置 */
.swiper-slide {
  height: 60vh;
  line-height: 60vh;
  text-align: center;
  background-color: white;;
}

/* 轮播图背景设置 */
.swiper::v-deep .swiper-pagination .swiper-pagination-bullet-active {
  background: rgb(255, 255, 255);
}

/* 视频栏上方的控件栏样式 */
.video-control {
  width: 100%;
  height: 8vh;
  background-color: rgb(250, 163, 59);
  display: flex;
  flex-direction: row-reverse;
  align-items: center;
  margin-bottom: 2vh;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

/* 更新视频栏大小、宽度设置*/
.renew {
  width: 18vh;
  height: 6vh;
  margin-right: 5%;
  display: flex;
}

/* 更新视频栏边框（响应鼠标变化）*/
.renew-focuse {
  border: solid 2px white;
  border-radius: 10%;
}

.renew-blur {
  border: none;
}

/* 更新视频按钮图片 */
.renew-img {
  margin-top: 1vh;
  width: 4vh;
  height: 4vh;
}

/* 更新视频按钮字体 */
.renew-word {
  width: 9vh;
  height: 6vh;
  margin-left: 2vh;
  line-height: 6vh;
  font-size: 3vh;
  color: rgb(137, 95, 72);
}

/* 视频栏大小设置 */
.video-boxs {
  margin-top: 2vh;
  width: 100%;
}

/* 单个视频大小设置 */
.video-box-per {
  width: 15vw;
  height: 25vh;
  margin-right: 3vw;
  background-color: white;
  border-radius: 1vw;
}

/* 每层视频的大小设置 */
.video-box-layer {
  display: flex;
  margin-left: 5.4vw;
  margin-bottom: 2vh;
  width: 100%;
}

/* 单个视频图片设置 */
.video-img {
  width: 15vw;
  height: 20vh;
  border-radius: 1vw;
}

/* 单个视频名字设置 */
.video-name {
  color: slategray;
  font-size: 0.5em;
}

/* 单个视频作者名字设置 */
.upLoad-name {
  color: black;
  font-size: 0.5em;
}

/*视频上出现阴影设置*/
.video-marsk {
  width: 15vw;
  height: 20vh;
  border-radius: 1vw;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1;
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
}

/*轮播图图片大小设置*/
.image {
  width: 100%;
  height: 100%;
}

/*视频播放图标图片大小设置*/
.video-live {
  width: 15vh;
  height: 15vh;
}
</style>