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

      <div>
        <div class="text-primary modal-bid">Your bid</div>
        <form action="">
          <input
            required
            type="number"
            pattern="[0-9]*"
            placeholder="place bid ..."
            class="modal-input"
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
}
</style>
