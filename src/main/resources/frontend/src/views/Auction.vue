<template>
  <div v-if="auction" class="detailed-auction container">
    <div class="auction-header row">
      <div class="back-arrow col-2">
        Ba
      </div>
      <h2 class="auction-title col-10">
        {{ auction.title }}
      </h2>
      <div class="divider"></div>
    </div>

    <div class="content">
      <div class="row image">
        <img :src="auction.image_URL" :alt="imageAlt">
      </div>

      <div class="row">
        <div class="col-6">
          <CurrentBid :currentBid="currentBid" />
        </div>

        <div class="col-6">
          <AuctionTimer :endDate="auction.end_date" />
        </div>
      </div>

      <div class="row auction-description">
        <h4 class="title col-12">Description</h4>
        <div class="divider"></div>
        <p class="description col-12">"{{ auction.description }}"</p>
      </div>
    </div>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";
import CurrentBid from "../components/CurrentBid.vue";
import AuctionTimer from "../components/AuctionTimer.vue";

@Component({
  components: {
    CurrentBid,
    AuctionTimer
  }
})
export default class Auction extends Vue {
  get auction() {
    return this.$store.state.auction;
  }

  get imageAlt() {
    return `Image of ${this.auction.title}.`;
  }

  get currentBid() {
    return this.auction.start_price;
  }

  async created() {
    const id = this.$route.params.id;
    await this.$store.dispatch("fetchAuction", id);
  }
}
</script>

<style lang="scss" scoped>
  h1, h2, h3, h4, h5, h6 {
    margin: 0;
  }

  .divider {
    width: calc(100% - 30px);
    margin: 0 15px;
    border-bottom: 1px solid black;
  }

  .row {
    margin-bottom: 20px;
  }

  .auction-header {
    align-items: center;
    height: 40px;
    margin-bottom: 20px;
    font-size: 12px;
    .auction-title {
      font-size: 18px;
      font-style: italic;
      text-align: end;
      text-overflow: ellipsis;
      white-space: nowrap;
      overflow: hidden;
    }
  }

  .content {
    .title {
      color: #288781;
      font-style: italic;
      font-size: 18px;
    }

    .image {
      display: flex;
      justify-content: center;
      img {
        height: 200px;
      }
    }

    .auction-description {
      
    }

  }
</style>