import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Auction from "../views/Auction.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/auction/:id",
    name: "Auction",
    component: Auction,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
