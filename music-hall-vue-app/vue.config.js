module.exports = {
  publicPath: "/",
  devServer: {
    host: "0.0.0.0",
    open: true,
    disableHostCheck: true,
    proxy: {
      //配置代理，解决跨域请求后台数据的问题
      "/api": {
        target: "http://localhost:8090",
        ws: true, //是否跨域
        changeOrigin: true,
        pathRewrite: {
          "^/api": "/",
        },
      },
    },
  },
};
