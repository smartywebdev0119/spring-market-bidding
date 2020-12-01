<template>
  <div class="shadowWarriorKillerStabber">
    <div class="bid-modal ">
      <div class="menu-icon">
        <i class="material-icons text-primary" @click="closeModal">clear</i>
      </div>
      <div>
        <h1 class="modal-title text-primary mb-3">Place bid</h1>
      </div>

      <CurrentBid
        class="mb-2"
        :fontSize="25"
        :startPrice="auction.start_price"
        :bids="bids"
      />

      <AuctionTimer class="mb-4" :fontSize="25" :endDate="auction.end_date" />
      <p class="text-danger font-weight-bold" v-if="error">{{ this.error }}</p>
      <div>
        <div class="text-primary modal-bid">Your bid</div>
        <form @submit.prevent="createBid" class="col">
          <input
            :class="error ? 'is-invalid' : ''"
            required
            type="number"
            pattern="[0-9]*"
            placeholder="place bid ..."
            class="modal-input form-control mx-auto"
            v-model="bidPrice"
          />
          <div>
            <button type="submit" class="btn btn-primary modal-btn mt-2 mb-5">
              Bid
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";
import AuctionTimer from "../components/AuctionTimer";
import CurrentBid from "../components/CurrentBid";
@Component({
  components: {
    AuctionTimer,
    CurrentBid,
  },
})
export default class PlaceBidModal extends Vue {
  @Prop({
    required: true,
  })
  auction;
  @Prop({
    required: true,
  })
  bids;

  isModalOpen = false;

  closeModal() {
    this.$emit("closeModal");
  }
  error = null;
  bidPrice = null;

  async createBid() {
    let response = await fetch(
      `/api/v1/auctions/${this.auction.auction_id}/bids`,
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ bidPrice: this.bidPrice }),
      }
    );

    if (response.ok) this.closeModal();
    if (!response.ok) {
      response = await response.json();
      this.error = response.message;
    }
  }
}
</script>

<style lang="scss" scoped>
.shadowWarriorKillerStabber {
  background-color: #2887815d;
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  right: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
.bid-modal {
  background-color: rgb(255, 255, 255);
  width: 85%;
  text-align: center;

  .modal-title {
    padding-top: 2em;
  }

  .material-icons {
    float: right;
    font-size: 50px;
    font-weight: bold;
    cursor: pointer;
    margin-right: 0.2em;
    margin-top: 0.2em;
  }
  .modal-btn {
    width: 11em;
  }
  .modal-input {
    justify-self: center;
    width: 11em;
  }
}
</style>
