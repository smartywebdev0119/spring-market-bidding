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
          <p class="card-title font-italic">{{ auction.title }}</p>
          <div class="row">
            <div class="current-bid-container col-12">
              <CurrentBid :startPrice="startPrice" :bids="currentBids" />
            </div>
            <div class="col-12">
              <AuctionTimer :endDate="endDate" />
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

  get link() {
    return `/auction/${this.auction.end_date}`;
  }

  get currentBids() {
    return this.bids;
  }

  get startPrice() {
    return this.auction.start_price;
  }

  get endDate() {
    return this.auction.end_date;
  }

  async created() {
    this.bids = await this.getBidByAuctionId(this.auction.auction_id);
  }

  navigate() {
    this.$store.commit("setAuction", this.auction);
    this.$router.push({ path: `auction/${this.auction.auction_id}` });
  }

  async getBidByAuctionId(id) {
    let bids = await fetch(`/api/v1/bids/auction/${id}`);
    bids = await bids.json();
    return bids;
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
