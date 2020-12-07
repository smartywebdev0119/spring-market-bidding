<template>
  <div @click="navigate" class="list-item">
    <div class="card h-100 mb-6" style="">
      <div class="">
        <img
          class="card-img-top"
          :src="image"
          alt="Card image cap"
        />
        <div class="card-body row">
          <p class="card-title font-italic text-truncate col-12">
            {{ auction.title }}
          </p>
          <div class="col-12">
            <CurrentBid
              :fontSize="12"
              :showTitle="false"
              :startPrice="auction.start_price"
              :bids="auction.bids"
            />
          </div>
          <div class="col-12">
            <AuctionTimer
              :fontSize="12"
              :showTitle="false"
              :endDate="auction.end_date"
            />
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

  get image() {
    return this.auction.images.length > 0? `http://localhost:3000${this.auction.images[0].image_URL}` : this.auction.image_URL;
  }
  navigate() {
    console.log("AUCTION ITEM", this.auction)
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
  .card-body {
    padding: 10px;
  }
  .card-img-top {
    padding: 5%;
    width: 100%;
    height: 30vh;
    object-fit: cover;
  }
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
