<template>
  <div id="app">
    <NavBar @toggleSlider="toggleSlider" />
    <SliderMenu v-if="showSlider" @toggleSlider="toggleSlider" />
    <router-view />
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";

import NavBar from "../src/components/NavBar";
import SliderMenu from "../src/components/SliderMenu";

@Component({
  components: { NavBar, SliderMenu },
})
export default class App extends Vue {
  showSlider = false;

  get websocket() {
    return this.$store.state.websocket;
  }

  toggleSlider() {
    this.showSlider = !this.showSlider;
  }

  async created() {
    await this.$store.dispatch("whoami");
    this.$store.dispatch("connectToWebsocket");
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>
