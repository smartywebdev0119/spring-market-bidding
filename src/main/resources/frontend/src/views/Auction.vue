<template>
  <div v-if="auction" class="detailed-auction container">
    <div class="auction-header row">
      <button @click="goBack()" class="back-arrow col-2">
        <svg height="25px" viewBox="0 0 16 16" class="bi bi-arrow-left" fill="#288781" xmlns="http://www.w3.org/2000/svg">
          <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
        </svg>
      </button>
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
          <CurrentBid :startPrice="auction.start_price" :currentBid="currentBid" />
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

  // TODO: get current bid.
  get currentBid() {
    return 2500;
  }

  goBack() {
    this.$router.go(-1);
  }

  async created() {
    const id = this.$route.params.id;
    await this.$store.dispatch("fetchAuction", id);
  }
}
</script>

<style lang="scss" scoped>
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
    .back-arrow {
      border: none;
      background: none;
      &:focus {
        outline: none;
        svg {
          border: 1px solid #288781;
        }
      }
    }
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
      .description {
        margin: 5px 0;
      }
    }

  }
</style>