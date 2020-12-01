<template>
  <div class="home-page bg-pic">
    <div class="background">
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
  height: 15vh;
  overflow: hidden;
  background-image: url('../assets/img/auction1.jpg');
  background-position: center;
  background-size: cover;
}

</style>
