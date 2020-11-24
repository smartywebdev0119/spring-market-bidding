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

  toggleSlider() {
    this.showSlider = !this.showSlider;
  }
}
</script>

<style></style>
