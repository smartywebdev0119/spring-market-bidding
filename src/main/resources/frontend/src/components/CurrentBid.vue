<template>
  <div class="auction-details">
    <h3 class="title">Highest bid:</h3>
    <span class="details-value detail-bid">{{ currentBid }} SEK</span>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component
export default class CurrentBid extends Vue {
  @Prop({type: Number, required: true})
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