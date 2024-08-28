import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { truncate } from 'fs'

const { resolve } = require('path')

export default defineConfig({
  build:{
    chunkSizeWarningLimit:1500,
  },
   // 起个别名，在引用资源时，可以用‘@/资源路径’直接访问
  resolve: {
    alias: {
      "@": resolve(__dirname, "src"),
    },
  },
  plugins: [vue()],
  // 配置前端服务地址和端口
  server: {
    host: '0.0.0.0',
    port: 443,
    // 是否开启 https
    https: false,
  },
  // 设置反向代理，跨域
  proxy: {
    '/api1': {
      target: 'https://www.ichlearning.cn/',
      changeOrigin: true,
      https: true,
      rewrite: path => path.replace(/^\/api1/, '')
    }
  }
})

