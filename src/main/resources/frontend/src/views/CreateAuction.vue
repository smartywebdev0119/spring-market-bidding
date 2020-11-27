<template>
  <div class="container">
    <div class="text-center header-text">Create Auction</div>
    <form @submit.prevent="createNewAuction">
      <p>Title</p>
      <input
        class="form-control"
        v-model="auction.title"
        type="text"
        required
      />

      <p>Description</p>
      <input
        class="form-control"
        v-model="auction.description"
        type="text"
        required
      />

      <p>Auction end date</p>
      <datepicker
        v-model="auction.end_date"
        bootstrap-styling
        :minimumView="'day'"
        :maximumView="'month'"
        placeholder="Select Date"
        :disabled-dates="this.disabledDates"
      ></datepicker>
      <p>Starting price</p>

      <input
        class="form-control"
        v-model="auction.start_price"
        type="text"
        pattern="[0-9]*"
        required
      />

      <p>Image Url</p>
      <input
        class="form-control"
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

    console.log(auctionToBeSaved, "AUCTION");
    console.log(JSON.stringify(auctionToBeSaved));

    let newAuction = await fetch('/api/v1/auctions', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(auctionToBeSaved)
    });
    newAuction = await newAuction.json();
 
      console.log(newAuction, "newAuction");
  }
}
</script>

<style lang="scss" scoped>
.header-text {
  font-size: 2em;
  color: #288781;
}
</style>
