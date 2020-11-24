<template>
  <div class="slider-menu">
    <div class="menu-icon">
      <i class="material-icons">menu</i>
    </div>
    <div>
      John Doe
    </div>
    <span v-if="true">
      Log out
    </span>
    <span v-else>
      Log in
    </span>
    <span>
      Create Auction
    </span>
    <span>
      Register
    </span>
    <span>
      Home
    </span>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";
@Component
export default class SliderMenu extends Vue {}
</script>

<style lang="scss" scoped>
.slider-menu {
  z-index: 10;
  background: red;
  height: 100vh;
  width: 50vw;
  position: absolute;
  top: 0;
  right: 0;
  text-align: right;

  div {
      padding-right: 0.8em;
      
  }
  .menu-icon {
    height: 4em;
    background: yellow;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    padding-right: 0.8em;

    i {
      font-size: 2.1em;
      color: #288781;
      cursor: pointer;
    }
  }
}
</style>
