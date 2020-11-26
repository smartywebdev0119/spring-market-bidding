<template>
  <div class="container">
    <div class="text-center header-text">Create Auction</div>
    <form @submit.prevent="createNewAuction">
      <!-- <div :key="option.title" v-for="option in options">
            <p>{{option.title}}</p>
            <input id="option.title"  v-model="auction[option.bind]" type="option.bind" required class="form.control"/>
        </div> -->

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
    title: null,
    description: null,
    start_price: null,
    end_date: new Date(),
    image_URL: null,
    timestamp: null,
  };

    date = new Date();
    priorDate

    disabledDates=  {
    to: this.date, // Disable all dates up to specific date
    from: new Date()
    }
  //   options = [
  //       {title: "Title", bind: 'title', type: "text"},
  //       {title: "Starting price", bind: 'start_price', type: "number"},
  //       {title: "Description", bind: 'description', type: "text"},
  //       {title: "Auction end date", bind: 'end_date', type: "number"},
  //       {title: "Image Url", bind: 'image_URL', type: "text"},
  //   ]

  created() {}

  //   checkForm(){
  //        if(Number.parseInt(this.auction.start_price)){
  //           this.auction.start_price = Number.parseInt(this.auction.start_price)
  //         }
  //   }

  async createNewAuction() {
    this.auction.start_price = Number.parseInt(this.auction.start_price);
    this.auction.end_date;

    this.auction.timestamp = new Date();
    console.log(this.auction, "AUCTION");

    let newAuction = await fetch("/api/v1/auctions", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(this.auction),
    });
    newAuction = await newAuction.json();
    if (newAuction) {
      console.log(newAuction, "newAuction");
    }
  }
}
</script>

<style lang="scss" scoped>
.header-text {
  font-size: 2em;
  color: #288781;
}
</style>
