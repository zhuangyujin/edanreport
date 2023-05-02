const{ defineConfig } = require('@vue/cli-service')
const AutoImport = require('unplugin-auto-import/webpack')
const Components = require('unplugin-vue-Components/webpack')
const{ ElementPlusResolver } = require('unplugin-vue-Components/resolvers')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  configureWebpack:{
    plugins:[
      AutoImport({
        resolvers: [ElementPlusResolver()]
      }),
      Components({
        resolvers:[ElementPlusResolver()]
      })
    ]
  },
  // //解决跨域问题
  devServer:{
    host: 'localhost',
    port: 8080,
    proxy:{
      '/api':{
        target:'http://localhost:8090',
        changeOrigin: true,
        pathRewrite:{
          '^/api':'' //重写api，把api变成空字符，因为我们真正请求的路径是没有api的
        }
      }
    }
  }
})