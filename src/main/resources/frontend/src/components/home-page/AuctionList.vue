<template>
  <div class="class-based-tutorial container">
    <div class=" bg-white">
      <h5 v-if="!searchWord">Auctions</h5>
      <h5 v-else class="word">"{{ searchWord }}"</h5>

      <div class="line" />
      <h5 v-if="auctions && !auctions.length" class="mt-3 word text-center">
        No auctions found.
      </h5>
    </div>

    <div class="row">
      <AuctionListItem
        class="col-6 col-sm-6 col-md-4 col-lg-3 mt-3"
        v-for="auction in auctions"
        :key="auction.auction_id"
        :auction="auction"
      />
    </div>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";
import AuctionListItem from "../home-page/AuctionListItem";

@Component({
  components: {
    AuctionListItem,
  },
})
export default class AuctionList extends Vue {
  auctioner = [];

  get searchWord() {
    return this.$store.state.searchWord;
  }

  get auctions() {
    return this.$store.state.auctions;
  }

  created() {}
}
</script>

<style lang="scss" scoped>
.line {
  border: 0.5px solid rgba(0, 0, 0, 0.5);
}
.word{
  overflow-wrap: break-word;
}
</style>
