module.exports = {
  devServer: {
    proxy: {
      "^/api/": {
        target: "http://localhost:3000",
        ws: true,
        changeOrigin: true,
      },
      "/auth/login": {
        target: "http://localhost:3000",
        ws: true,
        changeOrigin: true,
        pathRewrite: {"/auth/login": "/login"}
      },
      "^/auth/": {
        target: "http://localhost:3000",
        ws: true,
        changeOrigin: true,
      },
    },
  },
};
