<template>
  <div class="auction-details">
    <h3 class="title">{{ currentBid > startPrice ? 'Highest bid:' : 'Starting price:'}}</h3>
    <span class="details-value detail-bid">{{ currentBid > startPrice ? currentBid : startPrice }} SEK</span>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component
export default class CurrentBid extends Vue {
  @Prop({type: Number, required: true })
  startPrice
  @Prop({type: Number})
  currentBid
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
  }
</style>