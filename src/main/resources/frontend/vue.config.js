module.exports = {
  devServer: {
    proxy: {
      "^/auth/login": {
        target: "http://localhost:3000",
        ws: true,
        changeOrigin: true,
        pathRewrite: {"^/auth/login": "/login" },
      },
      "^/api/": {
        target: "http://localhost:3000",
        ws: true,
        changeOrigin: true,
      },
      "^/auth/": {
        target: "http://localhost:3000",
        ws: true,
        changeOrigin: true,
      },
    },
  },
};
