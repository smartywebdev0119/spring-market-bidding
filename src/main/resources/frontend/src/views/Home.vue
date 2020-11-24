<template>
  <div class="home-page bg-pic">
    <div class="bg-grey">HEADER D3mTr@din'Bo!z</div>
    <div class="background">
      <img src="../assets/img/auction1.jpg" alt="" />
      
    </div>
      <Search />
      <AuctionList />
  </div>
</template>

<script>
import { Component, Vue } from "vue-property-decorator";
import Search from "../components/home-page/Search";
import AuctionList from "../components/home-page/AuctionList";

@Component({
  components: {
    Search,
    AuctionList,
  },
})
export default class Home extends Vue {

  created(){
    this.$store.dispatch("fetchAllAuctionsWithQuery", "");
  }
}
</script>

<style lang="scss" scoped>
.background {
  max-width: 100vw;
  overflow: hidden;
  max-height: 30vh;
}
img {
  width: 100%;
  object-fit: cover;
}
</style>
