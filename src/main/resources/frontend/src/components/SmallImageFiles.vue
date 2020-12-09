<template>
  <div class=" mr-1 mb-3">
    <img
      :class="{ marked: index == 0 }"
      :src="smallImageObj.url"
      alt=""
      class="small-image"
      @click="setMarked"
    />
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component({
  components: {},
})
export default class SmallImageFiles extends Vue {
  @Prop({
    required: true,
  })
  smallImageObj;

  @Prop({
    required: true,
  })
  index;

  get markedIndex() {
    console.log(this.$store.state.markedIndex, " MARKED INDEX");
    return this.$store.state.markedIndex;
  }

  setMarked() {
    console.log("small ", this.smallImageObj);
    this.$emit("newMarkedImage", {
      imgObj: this.smallImageObj,
      index: this.index,
    });
  }
}
</script>

<style lang="scss" scoped>
.small-image {
  width: 10vw;
  height: 12vh;
  object-fit: cover;
}

.marked {
  border: teal solid 4px;
}
</style>
