<template>
  <div class="main">
    <el-card v-for="page in pages.page" :key="page">
      <div>
        <h2 class="main-text">{{ page.title }}</h2>
        <div class="main-text">
          {{ page.content }}
        </div>
      </div>
      <i class="article-icon">{{ page.author }}</i>
      <i class="el-icon-date article-icon">{{ page.time }}</i>
    </el-card>
  </div>
</template>

<script>
import {onMounted, reactive, ref, getCurrentInstance} from 'vue';
import axios from "axios";
export default {
  name: "upload",
  setup() {
      const cns = getCurrentInstance();
      let url = cns.appContext.config.globalProperties.$url;
      let pages = reactive({
      page: []
    })
      onMounted(async () => {
      axios.post(url + "/PersonalArticles/getPersonalArticles",{}).then(response => {
          pages.page = response.data.data
      }
      )
    }
    )
      return {pages};
    },
  data() {
    return {
      showDialog: false,
    };
  },
  methods: {
  },
  created() {},
};
</script>

<style scoped>
.main {
  margin: 0;
  padding: 0;
  margin-top: 100px;
  margin-left: 50px;
  margin-right: 50px;
}
.article {
  margin: 0;
  padding: 0;
  margin-top: 500px;
}
.el-card {
  margin-top: 20px;
}

.article-icon {
  color: #909399;
  font-size: 13px;
  margin-right: 10px;
  text-decoration: none;
}

</style>
