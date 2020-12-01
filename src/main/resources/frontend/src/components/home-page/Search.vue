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
import { Vue, Component } from "vue-property-decorator";

@Component
export default class Search extends Vue {
  searchQuery = "";
  executeSearch() {
    this.$store.dispatch("fetchAllAuctionsWithQuery", this.searchQuery);
  }
}
</script>

<style lang="scss" scoped>
.search-wrapper {
  margin-top: 5vh;
  .form-group {
    display: grid;
    row-gap: 15px;

    #search-field,
    #search {
      justify-self: center;
      width: 75%;
    }
  }
}

@media (min-width: 768px) {
  .search-wrapper {
    .form-group {
      #search-field,
      #search {
        width: 60%;
      }
    }
  }
}

@media (min-width: 1100px) {
  .search-wrapper {
    .form-group {
      #search-field,
      #search {
        width: 50%;
      }
    }
  }
}
</style>
