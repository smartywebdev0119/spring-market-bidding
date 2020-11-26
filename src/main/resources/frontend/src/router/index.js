import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Auction from "../views/Auction.vue";
import CreateAuction from "../views/CreateAuction.vue";

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
  {
    path: "/create-auction",
    name: "CreateAuction",
    component: CreateAuction,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
