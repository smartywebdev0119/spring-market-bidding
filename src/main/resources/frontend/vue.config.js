module.exports = {
  devServer: {
    proxy: {
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
      // "^/login": {
      //   target: "http://localhost:3000",
      //   ws: true,
      //   changeOrigin: true,
      // },
    },
  },
};
