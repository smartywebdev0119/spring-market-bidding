<template>
  <div class="slider-menu">
    <div class="menu-icon">
      <i class="material-icons" @click="toggleMenu">clear</i>
    </div>
    <div class="user">
      {{ user }}
    </div>
    <div>
      <div class="menu-item" v-for="(menuItem, i) in menuItems" :key="i">
        {{ menuItem.title }}
      </div>
    </div>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";
@Component
export default class SliderMenu extends Vue {
  defaultMenuItems = [
    { title: "Log In", route: "/log-in", userStatus: ["offline"] },
    { title: "Log Out", route: "/log-out", userStatus: ["online"] },
    {
      title: "Create Auction",
      route: "/create-auction",
      userStatus: ["online"],
    },
    { title: "Register", route: "/register", userStatus: ["offline"] },
    { title: "Home", route: "/", userStatus: ["offline", "online"] },
  ];

  get menuItems() {
    return this.isUserLoggedIn
      ? this.defaultMenuItems.filter((item) =>
          item.userStatus.includes("online")
        )
      : this.defaultMenuItems.filter((item) =>
          item.userStatus.includes("offline")
        );
  }

  get isUserLoggedIn() {
    return this.$store.state.loggedInUser ? true : false;
  }

  get user() {
    return this.$store.state.loggedInUser;
  }

  // Fix what to show depending on logged in or not.

  toggleMenu() {
    this.$emit("toggleSlider");
  }
}
</script>

<style lang="scss" scoped>
.slider-menu {
  z-index: 10;
  background: rgb(235, 228, 228);
  height: 100vh;
  width: 50vw;
  position: absolute;
  top: 0;
  right: 0;
  text-align: right;

  .menu-item {
    padding-right: 18px;
    font-size: 1.2em;
    cursor: pointer;
  }

  .user {
    padding-right: 18px;
    font-size: 1.5em;
    font-weight: bold;
  }
  .menu-icon {
    height: 4em;
    background: rgb(235, 228, 228);
    display: flex;
    align-items: center;
    justify-content: flex-end;
    padding-right: 0.8em;

    i {
      font-size: 2em;
      color: #288781;
    }
  }
}
</style>
