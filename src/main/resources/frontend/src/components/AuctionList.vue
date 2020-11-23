<template>
  <div class="class-based-tutorial container">
    <div class="sticky-top bg-white ">
    <h5 class="pt-2">Auctions</h5>
    <div class="line"></div>

    </div>
    <div class="row ">
    <AuctionListItem class="col-6 mt-3" v-for="(auction ) in this.$store.state.auctions" :key="auction.auction_id" :auction="auction"/>

    </div>
  </div>
  

  
</template>

<script>
import { Vue, Component} from "vue-property-decorator";
import AuctionListItem from "../components/AuctionListItem"

@Component({
  components: {
    AuctionListItem
  },
})
export default class ClassBasedTutorial extends Vue {
  
  auctions = [];

  created() {
   this.$store.dispatch('getAuctions')
   //this.auctions = this.$store.state.auctions;

    console.log(this.auctions + "<- Auctions")
  }

}
</script>

<style lang="scss" scoped>

.line{
  border: 0.5px solid rgba(0, 0, 0, 0.5);
}


</style>