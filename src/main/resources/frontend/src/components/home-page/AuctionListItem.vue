<template>
  <div @click="navigate" class="list-item">
    <div class="card h-100 mb-6" style="">
      <div class="">
        <img
          class="card-img-top"
          :src="auction.image_URL"
          alt="Card image cap"
        />
        <div class="card-body">
          <p class="card-title font-italic text-truncate">{{ auction.title }}</p>
          <div class="row">
            <div class="current-bid-container col-12">
              <CurrentBid :startPrice="auction.start_price" :bids="bids" />
            </div>
            <div class="col-12">
              <AuctionTimer :endDate="auction.end_date" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";
import AuctionTimer from "../AuctionTimer";
import CurrentBid from "../CurrentBid";
import { fetchBidsByAuctionId } from "../../core/utilities";

@Component({
  components: {
    AuctionTimer,
    CurrentBid,
  },
})
export default class AuctionListItem extends Vue {
  @Prop({
    required: true,
  })
  auction;

  bids = [];

  async created() {
    this.bids = await fetchBidsByAuctionId(this.auction.auction_id);
  }

  navigate() {
    this.$store.commit("setAuction", this.auction);
    this.$router.push({ path: `auction/${this.auction.auction_id}` });
  }
}
</script>

<style lang="scss" scoped>
.list-item {
  cursor: pointer;
}
.card-title {
  font-size: 0.75em;
}
.card {
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}
.card-title {
  width: 100%;
  white-space: nowrap;
  overflow: hidden;
  overflow: ellipsis;
}
.price-font {
  font-weight: bold;
  font-size: 1.8em;
  color: #288781;
}

.current-bid-container {
  margin-bottom: 12px;
}
</style>
