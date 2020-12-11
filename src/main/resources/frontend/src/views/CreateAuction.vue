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
        class="form-control mb-4"
        v-model="auction.start_price"
        type="number"
        pattern="[0-9]*"
        required
      />
      <p v-if="fileError" class="text-danger font-weight-bold">
        {{ fileError }}
      </p>

      <ImageUploader @chosen-img="setImages" class="mb-2" />

      <div class=" container row">
        <SmallImageFiles
          v-for="(imageObj, i) in smallImages"
          @newMarkedImage="setPreviewImage"
          :key="(imageObj.url, i)"
          :index="i"
          :smallImageObj="imageObj"
          @click="setMarked()"
        />
      </div>
      <p class="mb-">{{ this.auction.images.length }} files has been choosen</p>

      <div class="center-button">
        <button type="submit" class="btn btn-primary w-75" :disabled="!user">
          Create auction
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import { Component, Vue } from "vue-property-decorator";
import Datepicker from "vuejs-datepicker";
import ImageUploader from "../components/ImageUploader";
import SmallImageFiles from "../components/SmallImageFiles";

@Component({
  components: {
    Datepicker,
    ImageUploader,
    SmallImageFiles,
  },
})
export default class CreateAuction extends Vue {
  date = new Date();
  minDate = new Date(new Date().setDate(this.date.getDate() + 1));

  disabledDates = {
    to: new Date(new Date().setDate(this.date.getDate())),
    from: new Date(new Date().setDate(this.date.getDate() + 30)),
  };

  auction = {
    description: null,
    end_date: this.minDate,
    images: [],
    start_price: null,
    title: null,
  };

  fileError = "";
  imageFiles = null;

  get user() {
    return this.$store.state.loggedInUser;
  }

  get markedIndex() {
    return this.$store.state.markedIndex;
  }

  get smallImages() {
    return this.auction.images;
  }

  setImages(imgs) {
    this.auction.images = imgs.images;
    this.imageFiles = imgs.imageFiles;
  }

  setPreviewImage(obj) {
    
    this.auction.images.splice(obj.index, 1);
    this.auction.images.unshift(obj.imgObj);
  }

  async createAuction() {
    if (this.auction.images.length == 0) {
      this.fileError = "Choose atleast one image";
      return;
    }

    let auctionToBeSaved = {
      end_date: this.auction.end_date.getTime(),
      start_price: Number.parseFloat(this.auction.start_price),
      title: this.auction.title,
      description: this.auction.description,
      images: this.auction.images.map((x) => x.filename),
      user: this.user.user_id,
    };

    await fetch("/api/v1/upload-images", {
      method: "POST",
      body: this.imageFiles,
    });

    let newAuction = await fetch("/api/v1/auctions", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(auctionToBeSaved),
    });
    newAuction = await newAuction.json();

    this.imageFiles = null;
    this.images = [];
    this.$router.push({ path: `auction/${newAuction.auction_id}` });
  }
}
</script>

<style lang="scss" scoped>
.header-text {
  font-size: 2em;
  color: #288781;
}
.center-button {
  display: flex;
  justify-content: center;
}

.marked {
  border: teal 2px solid;
}
</style>
