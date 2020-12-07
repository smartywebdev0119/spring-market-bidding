<template>
  <div class="image-carousel">
    <div class="image-wrapper">
      <img :src="imageSelected" />
      <span v-if="this.images.length > 1" class="material-icons arrow left text-primary" @click="swipeLeft"
        >keyboard_arrow_left</span
      >
      <span v-if="this.images.length > 1" class="material-icons arrow right text-primary" @click="swipeRight"
        >keyboard_arrow_right</span
      >
    </div>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component
export default class ImageCarousel extends Vue {

  @Prop({
    required: true,
  })
  images

  imageIndex = 0;

  get imageSelected() {
    return this.images[this.imageIndex].image_URL? "http://Localhost:3000"+this.images[this.imageIndex].image_URL: this.images[this.imageIndex];
  }

  created(){
    console.log(this.images, " IMAGES")
  }

  swipeRight() {
    if (this.images.length - 1 === this.imageIndex) {
      this.imageIndex = 0;
    } else {
      this.imageIndex += 1;
    }
  }

  swipeLeft() {
    if (this.imageIndex === 0) {
      this.imageIndex = this.images.length - 1;
    } else {
      this.imageIndex -= 1;
    }
  }
}
</script>

<style lang="scss" scoped>
.image-carousel {
  height: 300px;

  .image-wrapper {
    position: relative;
    transition: 1000ms;
    // max-width: 200px;
    height: 300px;

    img {
      width: auto;
      height: 100%;
    }

    .arrow {
      cursor: pointer;
      font-size: 2em;
      position: absolute;
      top: 40%;
      user-select: none;
    }

    .arrow.right {
      right: 2%;
    }

    .arrow.left {
      left: 2%;
    }
  }
}
</style>
