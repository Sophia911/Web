<template>
  <div class="main">
    <div class="main-upload">
      <div class="top-nav"></div>
      <div class="upload">
        <el-form ref="form" label-width="80px">
          <el-form-item label="上传视频">
            <el-upload class="avatar-uploader" :drag="Plus" multiple :auto-upload="false"
              :on-change="(file, fileList) => ChangeVideo(file, fileList)" :limit="1">
              <el-icon class="el-icon-upload" :size="70" color="lightblue">
                <UploadFilled />
              </el-icon>
              <div v-if="Plus" class="el-upload__text">
                将文件拖到此处，或<em>点击上传</em>
              </div>
            </el-upload>
          </el-form-item>
          <el-form-item label="上传封面">
            <el-upload class="avatar-uploader-img" :drag="Plus" :auto-upload="false"
              :on-change="(file, fileList) => ChangeImg(file, fileList)" multiple :limit="1">
              <el-icon class="el-icon-upload" :size="70" color="lightblue">
                <UploadFilled />
              </el-icon>
              <div v-if="Plus" class="el-upload__text">
                将文件拖到此处，或<em>点击上传</em>
              </div>
            </el-upload>
          </el-form-item>

          <el-form-item label="标题">
            <e-col :span="8">
              <el-input v-model="$data.title" type="text" auto-complete="off" placeholder="请输入标题"></el-input>
            </e-col>
          </el-form-item>

          <el-form-item label="选择类型">
            <el-select size="small" filterable @change="customerChange" v-model="$data.type">
              <el-option v-for="(item, index) in $data.customerOptions" :key="index" :label="item.label"
                :value="item.value" style="width: 182px" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button @click="submitBtn" class="submit-button">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ElIcon } from "element-plus";
import { UploadFilled } from "@element-plus/icons-vue";
import axios from "axios";
import { forEach } from "lodash";
</script>

<script>
export default {
  data() {
    return {
      //视频部分
      videoForm: {
        videoId: "",
        videoUrl: "",
      },
      videoFlag: false,
      Plus: true,
      //上传视频时带的参数，这个地址就是后端保存磁盘的地址。可以更改。不建议放F盘，有的电脑可能没有F盘，只有C和D
      Path: {
        url: "/root/project/softwareEngineeringBack-end/target/src/main/resources/Video",
      },
      title: "",
      videoUploadPercent: 0,

      //下拉框
      customerOptions: [
        {
          value: "1",
          label: "悲剧",
        },
        {
          value: "2",
          label: "喜剧",
        },
      ],
      type: "",
      videoUrl: [],
      imgUrl: [],
    };
  },

  methods: {
    ChangeImg(file, fileList) {
      const testmsg = file.name.substring(file.name.lastIndexOf(".") + 1);
      // .jpg,.jpeg,.png,.pdf,.PDF
      const extension1 = testmsg === "jpg";
      const extension2 = testmsg === "jpeg";
      const extension3 = testmsg === "png"
      if (
        !extension1 &&
        !extension2 &&
        !extension3
      ) {
        this.$message({
          message: "上传文件只能是.jpg,.jpeg,.png格式!",
          type: "warning",
        });
      } else {
        this.$data.imgUrl = file;
      }
    },
    ChangeVideo(file, fileList) {
      const testmsg = file.name.substring(file.name.lastIndexOf(".") + 1);
      // .jpg,.jpeg,.png,.pdf,.PDF
      const extension1 = testmsg === "mp4";
      if (!extension1) {
        this.$message({
          message: "上传文件只能是.mp4格式!",
          type: "warning",
        });
      } else {
        this.$data.videoUrl = file;
      }
    },
    customerChange(id) {
      const thisLabel = this.$data.customerOptions.find(
        (item) => item.value === id
      ).label;
      console.log(`label----->`, thisLabel); //label-----> 国风
    },
    submitBtn() {
      if (
        this.$data.videoUrl.length !== 0 &&
        this.$data.imgUrl.length !== 0 &&
        this.$data.type !== "" &&
        this.$data.title !== ""
      ) {
        this.$data.customerOptions.forEach((element) => {
          if (element.value == this.$data.type) {
            this.$data.type = element.label;
          }
        });
        if (this.$user.userName != null) {
          axios({
            url: this.$url + "/video",
            method: "post",
            headers: {
              "Content-Type": "multipart/form-data",
            },
            data: {
              id: this.$user.userName,
              token: this.$user.token,
              videoFile: this.$data.videoUrl.raw,
              imgFile: this.$data.imgUrl.raw,
              name: this.$data.title,
              owner_id: this.$user.userName,
              type: this.$data.type,
            },
          }).then((response) => {
            if (response.data.data) {
              this.$message({
                message: "上传成功",
                type: "success",
              });
            } else {
              this.$message({
                message: "上传失败,原因：" + response.data.msg,
                type: "error",
              });
            }
          });
          this.$message({
            message: "上传中,请等待...",
            type: "success",
          });
        } else {
          this.$message({
            message: "请先登录！",
            type: "error",
          });
        }
      } else {
        this.$message({
          message: "文件信息不完整或格式不正确,请检查",
          type: "warning",
        });
      }
    },
  },
};
</script>

<style>
.main {
  margin: 0;
  padding: 0;
  background-color: whitesmoke;
}

.main-upload {
  margin: 0;
  padding: 0;
  background-image: url("../../imgs/backupload.jpg");
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: 100% 100%;
}

.main-upload .upload {
  margin: 0;
  padding: 0;
  margin-top: 10vh;
  /* margin-left: 70vh;
  height: 88vh; */
  display: flex;
  justify-content: center;
  align-items: center;
}

.upload .el-form-item__label {
  text-align: left;
  font-weight: bold;
}

.upload .submit-button {
  background-color: #409eff;
  color: white;
  width: 80px;
  text-align: center;
}

.top-nav {
  width: 100vw;
  height: 10vh;
}
</style>
