<template>
  <div v-if="!processing" class="detailed-auction container">
    <div class="auction-header row">
      <div class="back-arrow col-2">
        <button @click="goBack">
          <svg
            height="25px"
            viewBox="0 0 16 16"
            class="bi bi-arrow-left"
            fill="#288781"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fill-rule="evenodd"
              d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"
            />
          </svg>
        </button>
      </div>
      <h2 class="auction-title col-10">
        {{ auction.title }}
      </h2>
      <div class="divider"></div>
    </div>

    <div class="content">
      <div class="row image">
        <ImageCarousel />
        <!-- <img :src="auction.image_URL" :alt="imageAlt" /> -->
      </div>

      <div class="row">
        <div
          class="col-6 col-sm-5 col-md-4 col-lg-3 offset-sm-1 offset-md-2 offset-lg-3"
        >
          <CurrentBid :startPrice="auction.start_price" :bids="auction.bids" />
        </div>

        <div class="col-6 col-sm-5 col-md-4 col-lg-3">
          <AuctionTimer :endDate="auction.end_date" />
        </div>
      </div>
      <div class="bid row">
        <button
          type="button"
          class="btn btn-primary bid-btn col-4 offset-4"
          v-if="isOwner"
          @click="toggleModal"
        >
          Place bid
        </button>
      </div>

      <div class="row auction-description">
        <h4 class="title col-12">Description</h4>
        <div class="divider"></div>
        <p class="description col-12">"{{ auction.description }}"</p>
      </div>
      <div class="row auction-description">
        <h4 class="title col-12">Contact information</h4>
        <div class="divider" />
        <div class="description col-12">
          <p>
            <i class="material-icons align-middle" aria-hidden="true">mail</i>
            {{ auction.auctionOwner.email }}
          </p>
          <p>{{ auction.auctionOwner.username }}</p>
        </div>
      </div>
    </div>
    <PlaceBidModal
      @closeModal="toggleModal"
      v-if="showModal"
      :bids="auction.bids"
      :auction="auction"
    />
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";
import CurrentBid from "../components/CurrentBid";
import AuctionTimer from "../components/AuctionTimer";
import PlaceBidModal from "../components/PlaceBidModal";
import ImageCarousel from "../components/auction-page/ImageCarousel";

@Component({
  components: {
    CurrentBid,
    AuctionTimer,
    PlaceBidModal,
    ImageCarousel,
  },
})
export default class Auction extends Vue {
  processing = true;
  showModal = false;

  get auction() {
    return this.$store.state.auction;
  }

  get imageAlt() {
    return `Image of ${this.auction.title}.`;
  }

  get loggedInUser() {
    return this.$store.state.loggedInUser;
  }

  get isOwner() {
    if (!this.loggedInUser) return true;
    return this.loggedInUser.user_id === this.auction.auctionOwner.user_id
      ? false
      : true;
  }

  goBack() {
    this.$router.go(-1);
  }

  toggleModal() {
    if (!this.loggedInUser) {
      this.$router.push("/login");
    }
    this.showModal = !this.showModal;
  }

  async created() {
    const id = this.$route.params.id;
    if (this.auction?.auction_id != id) {
      await this.$store.dispatch("fetchAuction", id);
    }
    this.processing = false;
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
    display: flex;
    justify-content: flex-start;
    button {
      border: none;
      background: none;
      &:focus {
        outline: none;
        svg {
          border: 1px solid #288781;
        }
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

.bid-btn {
  border-radius: 12px;
}
</style>
