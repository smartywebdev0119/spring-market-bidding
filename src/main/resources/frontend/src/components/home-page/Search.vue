<template>
  <div class="search-wrapper">
    <form @submit.prevent="executeSearch">
      <div class="form-group">
        <input
          class="form-control"
          id="search-field"
          type="text"
          v-model="searchQuery"
          placeholder="Type in your search..."
        />
        <button id="search" class="btn btn-primary">Search</button>
      </div>
    </form>
  </div>
</template>

<script>
import { Vue, Component, } from "vue-property-decorator";

@Component
export default class Search extends Vue {
  searchQuery = "";
  executeSearch() {
    this.$store.dispatch("fetchAllAuctionsWithQuery", this.searchQuery);
  }

  //TODO Remove this watch later on.
  // @Watch("$store.state.auctions")
  // onAuctionsChange(newVal) {
  //   console.log(newVal);
  // }
}
</script>

<style lang="scss" scoped>
.search-wrapper {
  .form-group {
    display: grid;
    row-gap: 5px;

    #search-field,
    #search {
      justify-self: center;
      width: 80%;
    }
  }
}
</style>
