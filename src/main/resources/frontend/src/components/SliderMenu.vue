<template>
  <div class="slider-menu">
    <div class="menu-icon">
      <i class="material-icons" @click="toggleMenu">clear</i>
    </div>
    <div v-if="user" class="user">
      {{ user.username }}
    </div>
    <div>
      <div class="menu-item" v-for="(menuItem, i) in menuItems" :key="i">
        <div class="choice">
          <span @click="handleClick(menuItem)">
            {{ menuItem.title }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";
@Component
export default class SliderMenu extends Vue {
  defaultMenuItems = [
    { title: "Log In", route: "/login", userStatus: ["offline"] },
    { title: "Log Out", route: "/logout", userStatus: ["online"] },
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

  toggleMenu() {
    this.$emit("toggleSlider");
  }

  handleClick(menuItem) {
    this.toggleMenu();
    if (this.$route.path == menuItem.route) return;
    if (menuItem.route === "/logout") {
      return this.logoutUser();
    }
    this.$router.push(menuItem.route);
  }

  async logoutUser() {
    fetch("/auth/logout", {
      method: "GET",
    })
      .then((response) => {
        if (response.ok) {
          this.$store.commit("setloggedInUser", null);
          //return user to home
          if (this.$route.path === "/") return;
          this.$router.push("/");
        }
      })
      .catch((error) => {
        console.error(error);
      });
  }
}
</script>

<style lang="scss" scoped>
.slider-menu {
  z-index: 10;
  background: white;
  height: 100vh;
  width: 43vw;
  position: absolute;
  top: 0;
  right: 0;
  text-align: right;
  box-shadow: 10px 10px 10px 10px rgba(0, 0, 0, 0.336);

  .menu-item {
    padding-right: 18px;
    font-size: 1.2em;
  }

  .user {
    padding-right: 18px;
    font-size: 1.5em;
    font-weight: bold;
  }
  .menu-icon {
    height: 4em;
    background: white;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    padding-right: 0.8em;

    i {
      font-size: 2em;
      color: #288781;
      cursor: pointer;
    }
  }

  .choice {
    color: #288781;
    text-decoration: none;
    &:active {
      font-weight: bold;
    }
    span {
      cursor: pointer;
    }
  }
}

@media (min-width: 450px) {
  .slider-menu {
    transition: width 1s;
    width: 35vw;
  }
}

@media (min-width: 550px) {
  .slider-menu {
    transition: width 1s;
    width: 30vw;
  }
}

@media (min-width: 900px) {
  .slider-menu {
    transition: width 1s;
    width: 20vw;
  }
}

@media (min-width: 1400px) {
  .slider-menu {
    transition: width 1s;
    width: 12vw;
  }
}
</style>
