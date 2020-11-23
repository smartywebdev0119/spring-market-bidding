<template>
  <div class="search-wrapper">
    <form @submit.prevent="executeSearch">
      <input id="search-field" type="text" v-model="searchQuery" />
      <button id="search">search</button>
    </form>
  </div>
</template>

<script>
import { Vue, Component, Watch } from "vue-property-decorator";

@Component
export default class Search extends Vue {
  searchQuery = "";
  executeSearch() {
    this.$store.dispatch("fetchAllAuctionsWithQuery", this.searchQuery);
  }

  //TODO Remove this watch later on.
  @Watch("$store.state.auctions")
  onAuctionsChange(newVal) {
    console.log(newVal);
  }
}
</script>

<style lang="scss" scoped></style>
