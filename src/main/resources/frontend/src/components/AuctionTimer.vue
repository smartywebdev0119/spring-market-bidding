<template>
  <div class="auction-details">
    <h3 class="title">Time left:</h3>
    <span class="details-value detail-time-left">{{ timeLeft }}</span>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component
export default class AuctionTimer extends Vue {
  @Prop({type: Number, required: true})
  endDate

  get timeLeft() {
    const date = new Date(this.endDate);
    return date.toDateString();
  }

  created() {
  }

  destroyed() {
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
  }
</style>