<template>
  <div class="search-wrapper">
    <form >
      <input id="search-field" type="text">
      <button id="search">search</button>
    </form>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";

@Component
export default class Search extends Vue {

}
</script>

<style lang="scss" scoped></style>