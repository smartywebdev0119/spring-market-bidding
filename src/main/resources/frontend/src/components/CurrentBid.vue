<template>
  <div class="auction-details">
    <h3 v-if="showTitle" class="title" :style="standardFontSize">{{ title }}</h3>
    <div class="bid-info">
      <span class="current-bid" :style="standardFontSize">{{ currentPrice }}</span>
      <span v-if="hasBids" class="bids-counter" :style="bidCounterFontSize">
        {{ numberOfBids }}
      </span>
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
  @Prop({ type: Boolean, default: true })
  showTitle;
  @Prop({ type: Number, default: 18 })
  fontSize;

  get hasBids() {
    return this.bids.length;
  }

  get title() {
    return this.hasBids ? "Highest bid:" : "Starting price:";
  }

  get currentPrice() {
    const price = this.hasBids ? this.bids[0].bid_price : this.startPrice;
    return `${price} SEK`;
  }

  get numberOfBids() {
    const bidCount = this.bids.length;
    const output = `- ${bidCount} bid`;
    return bidCount > 1 ? output + "s" : output;
  }

  get standardFontSize() {
    return { fontSize: this.fontSize + "px" };
  }

  get bidCounterFontSize() {
    return { fontSize: this.fontSize * 0.8 + "px" };
  }
}
</script>

<style lang="scss" scoped>
.auction-details {
  display: flex;
  flex-direction: column;
  align-items: center;

  .title {
    font-style: italic;
  }

  .bid-info {
    display: flex;
    align-items: center;

    .current-bid {
      color: #288781;
      font-weight: bold;
    }

    .bids-counter {
      margin-left: 4px;
      font-style: italic;
    }
  }
}
</style>
