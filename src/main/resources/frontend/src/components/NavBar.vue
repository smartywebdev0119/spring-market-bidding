<template>
  <div class="navbar">
    <h1 class="title">D3mTr@din'Bo!z</h1>
    <i class="material-icons" @click="toggleMenu">menu</i>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";
@Component
export default class NavBar extends Vue {
  toggleMenu() {
    this.$emit("toggleSlider");
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  display: grid;
  align-items: center;
  grid-template-columns: 1fr 3fr 1fr;
  column-gap: 5px;
  background:white;
  height: 4em;

  .title {
    grid-column: 2/3;
    justify-self: center;
    margin: 0;
    font-size: 1.6em;
  }

  i {
    font-size: 2em;
    color: #288781;
    cursor: pointer;
    justify-self: flex-end;
  }
}
</style>
