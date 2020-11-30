<template>
  <div class="container">
    <div class="text-center header-text mb-3 mt-3 italic">Create auction</div>
    <form @submit.prevent="createAuction" class="w-75 mx-auto">
      <label class="text-primary font-italic">Title</label>
      <input
        class="form-control mb-3 "
        v-model="auction.title"
        type="text"
        required
      />

      <label class="text-primary font-italic">Description</label>
      <input
        class="form-control mb-3"
        v-model="auction.description"
        type="text"
        required
      />

      <label class="text-primary font-italic">Auction end date</label>
      <datepicker
      class="mb-3"
        v-model="auction.end_date"
        bootstrap-styling
        :minimumView="'day'"
        :maximumView="'month'"
        placeholder="Select Date"
        :disabled-dates="this.disabledDates"
      ></datepicker>

      <label class="text-primary font-italic">Starting price</label>
      <input
        class="form-control mb-3"
        v-model="auction.start_price"
        type="text"
        pattern="[0-9]*"
        required
      />

      <label class="text-primary font-italic">Image Url</label>
      <input
        class="form-control mb-4"
        v-model="auction.image_URL"
        type="text"
        required
      />
      <div class="center-button">
        <button type="submit" class="btn btn-primary w-75 ">Create auction</button>
      </div>

    </form>
  </div>
</template>

<script>
import { Component, Vue } from "vue-property-decorator";
import Datepicker from "vuejs-datepicker";

@Component({
  components: {
    Datepicker,
  },
})
export default class CreateAuction extends Vue {

  date =  new Date();
  minDate = new Date(new Date().setDate(this.date.getDate()+1));

  disabledDates=  {
    to: new Date(new Date().setDate(this.date.getDate())),
    from: new Date(new Date().setDate(this.date.getDate()+30))
    }

  auction = {
    description: null,
    end_date: this.minDate,
    image_URL: null,
    start_price: null,
    title: null,
  };

  created() {}

  async createAuction() {

    let auctionToBeSaved = {
        end_date: this.auction.end_date.getTime(),
        start_price: Number.parseFloat(this.auction.start_price),
        title: this.auction.title,
        description: this.auction.description,
        image_URL: this.auction.image_URL,
        user: 1
    }

    let newAuction = await fetch('/api/v1/auctions', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(auctionToBeSaved)
    });
    newAuction = await newAuction.json();
    this.$router.push({ path: `auction/${newAuction.auction_id}` });
  }
}
</script>

<style lang="scss" scoped>
.header-text {
  font-size: 2em;
  color: #288781;
}
.center-button{
  display: flex;
  justify-content: center;
}
</style>
