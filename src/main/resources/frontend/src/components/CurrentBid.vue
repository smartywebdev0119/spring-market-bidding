<template>
  <div class="auction-details">
    <h3 class="title">{{ title }}</h3>
    <span class="current-bid">{{ currentPrice }}</span>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component
export default class CurrentBid extends Vue {
  @Prop({type: Number, required: true })
  startPrice
  @Prop({type: Number, default: 0})
  currentBid

  get title() {
    return this.currentBid > this.startPrice ? 'Highest bid:' : 'Starting price:'
  }

  get currentPrice() {
    const price = this.currentBid > this.startPrice ? this.currentBid : this.startPrice
    return `${price} SEK`
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
    .current-bid {
      font-size: 18px;
      font-weight: bold;
    }
  }
</style>