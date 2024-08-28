<template>
  <div class="main">
    <div class="top-nav"></div>
    <div class="main-upload">
      <div class="form-container">
        <el-form ref="form" label-width="80px">
          <el-form-item label="上传视频" class="custom-label-left">
            <el-upload class="avatar-uploader" :drag="Plus" :auto-upload="false" :before-upload="beforeVideoUpload"
              :on-change="(file, fileList) => ChangeVideo(file, fileList)" :limit="1">
              <el-icon class="el-icon-upload" :size="70" color="lightblue">
                <UploadFilled />
              </el-icon>
              <div v-if="Plus" class="el-upload__text">
                将文件拖到此处，或<em>点击上传</em>
              </div>
            </el-upload>
          </el-form-item>

          <el-form-item label="上传封面" class="custom-label-left">
            <el-upload class="avatar-uploader-img" :drag="Plus" :auto-upload="false" :before-upload="beforeImageUpload"
              :on-change="(file, fileList) => ChangeImg(file, fileList)" :limit="1">
              <el-icon class="el-icon-upload" :size="70" color="lightblue">
                <UploadFilled />
              </el-icon>
              <div v-if="Plus" class="el-upload__text">
                将文件拖到此处，或<em>点击上传</em>
              </div>
            </el-upload>
          </el-form-item>

          <el-form-item label="标题" class="custom-label-left">
            <el-col :span="24">
              <el-input v-model="$data.title" type="text" auto-complete="off" placeholder="请输入标题"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item label="选择类型" class="custom-label-left">
            <el-select size="small" filterable @change="customerChange" v-model="$data.type">
              <el-option v-for="(item, index) in $data.customerOptions" :key="index" :label="item.label"
                :value="item.value" />
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
</script>

<script>
export default {
  data() {
    return {
      videoForm: {
        videoId: "",
        videoUrl: "",
      },
      videoFlag: false,
      Plus: true,
      Path: {
        url: "/root/project/softwareEngineeringBack-end/target/src/main/resources/Video",
      },
      title: "",
      videoUploadPercent: 0,
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
    // ChangeImg(file, fileList) {
    //   const testmsg = file.name.substring(file.name.lastIndexOf(".") + 1).toLowerCase();
    //   const validExtensions = ["jpg", "jpeg", "png", "pdf", "doc", "docx"];
    //   if (!validExtensions.includes(testmsg)) {
    //     this.$message({
    //       message: "上传文件只能是.jpg,.jpeg,.png,.pdf,.doc,.docx格式!",
    //       type: "warning",
    //     });
    //   } else {
    //     this.imgUrl = file;
    //   }
    // },
    // ChangeVideo(file, fileList) {
    //   const testmsg = file.name.substring(file.name.lastIndexOf(".") + 1).toLowerCase();
    //   if (testmsg !== "mp4") {
    //     this.$message({
    //       message: "上传文件只能是.mp4格式!",
    //       type: "warning",
    //     });
    //   } else {
    //     this.videoUrl = file;
    //   }
    // },
    // 用于检查图片格式并阻止不符合要求的文件上传
    beforeImageUpload(file) {
      const testmsg = file.name.substring(file.name.lastIndexOf(".") + 1).toLowerCase();
      const validExtensions = ["jpg", "jpeg", "png"];
      console.log("调用")
      if (!validExtensions.includes(testmsg)) {
        this.$message({
          message: "上传文件只能是.jpg,.jpeg,.png格式!",
          type: "warning",
        });
        return false;  // 阻止文件上传
      }
      return true;  // 允许上传
    },

    // 用于检查视频格式并阻止不符合要求的文件上传
    beforeVideoUpload(file) {
      const testmsg = file.name.substring(file.name.lastIndexOf(".") + 1).toLowerCase();
      if (testmsg !== "mp4") {
        this.$message({
          message: "上传文件只能是.mp4格式!",
          type: "warning",
        });
        return false;  // 阻止文件上传
      }
      return true;  // 允许上传
    },

    // 在文件通过格式验证后保存图片信息
    ChangeImg(file, fileList) {
      this.imgUrl = file;
    },

    // 在文件通过格式验证后保存视频信息
    ChangeVideo(file, fileList) {
      this.videoUrl = file;
    },
    customerChange(id) {
      const thisLabel = this.customerOptions.find(
        (item) => item.value === id
      ).label;
      console.log(`label----->`, thisLabel);
    },
    submitBtn() {
      console.log(this.videoUrl)
      console.log(this.imgUrl)
      console.log(this.videoUrl.raw)
      console.log(this.imgUrl.raw)
      if (
        this.videoUrl.length !== 0 &&
        this.imgUrl.length !== 0 &&
        this.type !== "" &&
        this.title !== ""
      ) {
        this.customerOptions.forEach((element) => {
          if (element.value == this.type) {
            this.type = element.label;
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
              videoFile: this.videoUrl.raw,
              imgFile: this.imgUrl.raw,
              name: this.title,
              owner_id: this.$user.userName,
              type: this.type,
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
          message: "文件信息不完整,请检查",
          type: "warning",
        });
      }
    },
    //     submitBtn() {
    //   // 检查视频文件、封面文件、类型和标题是否完整
    //   if (!this.videoUrl && !this.imgUrl && !this.type && !this.title) {
    //     this.$message({
    //       message: "文件信息不完整,请检查",
    //       type: "warning",
    //     });
    //     return; // 如果有字段未填写，直接返回，阻止提交
    //   }

    //   // 如果类型是通过 label 映射的，确保使用正确的 label 值
    //   const selectedType = this.customerOptions.find(
    //     (element) => element.value === this.type
    //   );
    //   if (selectedType) {
    //     this.type = selectedType.label;
    //   }

    //   // 检查用户是否登录
    //   if (this.$user && this.$user.userName) {
    //     this.$message({
    //       message: "上传中,请等待...",
    //       type: "info",
    //     });


    //     // 发送请求
    //     axios({
    //       url: this.$url + "/video",
    //       method: "post",
    //       headers: {
    //         "Content-Type": "multipart/form-data",
    //       },
    //       data: {
    //         id: this.$user.userName,
    //         token: this.$user.token,
    //         videoFile: this.videoUrl.raw,
    //         imgFile: this.imgUrl.raw,
    //         name: this.title,
    //         owner_id: this.$user.userName,
    //         type: this.type,
    //       },
    //     }).then((response) => {
    //       if (response.data.data) {
    //         this.$message({
    //           message: "上传成功",
    //           type: "success",
    //         });
    //       } else {
    //         this.$message({
    //           message: "上传失败,原因：" + response.data.msg,
    //           type: "error",
    //         });
    //       }
    //     }).catch((error) => {
    //       this.$message({
    //         message: "上传过程中发生错误：" + error.message,
    //         type: "error",
    //       });
    //     });
    //   } else {
    //     this.$message({
    //       message: "请先登录！",
    //       type: "error",
    //     });
    //   }
    // }

  },
};
</script>

<style>
.main {
  margin: 0;
  padding: 0;
  background-color: whitesmoke;
  min-height: 100vh;
  /* display: flex;
  justify-content: center;
  align-items: center; */
}

.main .top-nav {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 10vh;
  z-index: 1000;

}

.main-upload {
  margin: 0;
  padding-top: 80px;
  position: relative;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.main-upload::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url("../../imgs/backupload.jpg");
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 0.5;
  z-index: -1;
}

.form-container {
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
  padding: 20px;
  max-width: 600px;
  width: 100%;
}

.custom-label-left .el-form-item__label {
  text-align: left;
  font-weight: bold;
}

.form-container .submit-button {
  background-color: #409eff;
  color: white;
  width: 80px;
  text-align: center;
}
</style>
