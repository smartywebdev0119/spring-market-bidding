<template>
  <div class="container">
    <div class="text-center header-text">Create Auction</div>
    <form @submit.prevent="createNewAuction" class="w-75 mx-auto">
      <label>Title</label>
      <input
        class="form-control mb-3"
        v-model="auction.title"
        type="text"
        required
      />

      <label>Description</label>
      <input
        class="form-control mb-3"
        v-model="auction.description"
        type="text"
        required
      />

      <label>Auction end date</label>
      <datepicker
      class="mb-3"
        v-model="auction.end_date"
        bootstrap-styling
        :minimumView="'day'"
        :maximumView="'month'"
        placeholder="Select Date"
        :disabled-dates="this.disabledDates"
      ></datepicker>

      <label>Starting price</label>
      <input
        class="form-control mb-3"
        v-model="auction.start_price"
        type="text"
        pattern="[0-9]*"
        required
      />

      <label>Image Url</label>
      <input
        class="form-control mb-3"
        v-model="auction.image_URL"
        type="text"
        required
      />

      <button type="submit" class="btn btn-primary">Create auction</button>
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
  auction = {
    description: null,
    end_date: this.minDate,
    image_URL: null,
    start_price: null,
    timestamp: null,
    title: null,
  };

    date =  new Date();
    minDate = new Date(new Date().setDate(this.date.getDate()+1));
    disabledDates=  {
    to: new Date(new Date().setDate(this.date.getDate())),
    from: new Date(new Date().setDate(this.date.getDate()+30))
    }
 
  created() {}

  async createNewAuction() {

    this.auction.start_price = Number.parseFloat(this.auction.start_price);

    let auctionToBeSaved = {
        timestamp: new Date().getTime(),
        end_date: this.auction.end_date.getTime(),
        start_price: this.auction.start_price,
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
    console.log("NewAuction", newAuction)
    newAuction = await newAuction.json();
  }
}
</script>

<style lang="scss" scoped>
.header-text {
  font-size: 2em;
  color: #288781;
}
</style>
