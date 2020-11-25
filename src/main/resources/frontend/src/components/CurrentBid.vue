<template>
  <div class="auction-details">
    <h3 class="title">{{ title }}</h3>
    <div class="bid-info">
      <span class="current-bid">{{ currentPrice }}</span>
      <span v-if="hasBids" class="bids-counter">{{ numberOfBids }}</span>
    </div>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component
export default class CurrentBid extends Vue {
  @Prop({ type: Number, required: true })
  startPrice;
  @Prop({ type: Array, default: [] })
  bids;

  get hasBids() {
    return this.bids.length;
  }

  get title() {
    return this.hasBids ? "Highest bid:" : "Starting price:";
  }

  get currentPrice() {
    const price =
      this.hasBids ? this.bids[0].bid_price : this.startPrice;
    return `${price} SEK`;
  }

  get numberOfBids() {
    const bidCount = this.bids.length;
    const output = `- ${bidCount} bid`;
    return bidCount > 1 ? output + "s" : output;
  }
}
</script>

<style lang="scss" scoped>
.auction-details {
  display: flex;
  flex-direction: column;
  align-items: center;

  .title {
    color: #288781;
    font-style: italic;
    font-size: 18px;
  }

  .bid-info {
    display: flex;
    align-items: center;

    .current-bid {
      font-size: 18px;
      font-weight: bold;
    }

    .bids-counter {
      margin-left: 4px;
      font-style: italic;
    }
  }
}
</style>
