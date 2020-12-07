<template>
  <div class="image-carousel">
    <div class="image-wrapper">
      <img :src="imageSelected" />
      <span class="material-icons arrow left text-primary" @click="swipeLeft"
        >keyboard_arrow_left</span
      >
      <span class="material-icons arrow right text-primary" @click="swipeRight"
        >keyboard_arrow_right</span
      >
    </div>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";

@Component
export default class ImageCarousel extends Vue {
  imageIndex = 0;
  images = [
    "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.OemSsSn0VuJIXoXzw4j6XgHaKW%26pid%3DApi&f=1",
    "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.gaychristian101.com%2Fimages%2Fjesusgentle.jpg&f=1&nofb=1",
    "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.g56qmqkC2VUIC5rP7HgbDAAAAA%26pid%3DApi&f=1",
    "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fcgcatholic.org.au%2Fwp-content%2Fuploads%2F2015%2F09%2Fjesus_painting_by_heinrich_hofmann-212x300.jpg&f=1&nofb=1",
  ];

  get imageSelected() {
    return this.images[this.imageIndex];
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
    max-width: 200px;

    img {
      width: 100%;
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
