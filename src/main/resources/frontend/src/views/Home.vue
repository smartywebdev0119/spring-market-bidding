<template>
  <div class="home-page">
    <h1>This is the home page</h1>
    <Search />
  </div>
</template>

<script>
import { Component, Vue } from "vue-property-decorator";
import Search from "../components/home-page/Search";

@Component({
  components: {
    Search,
  },
})
export default class Home extends Vue {}
</script>

<style lang="scss" scoped></style>
