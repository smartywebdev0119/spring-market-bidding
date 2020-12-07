<template>
     
        <div  class=" mr-1 mb-3"  >
          <img :class="{marked: isMarked}" :src="smallImage" alt="" class="small-image" @click="setMarked"/>
        </div>
      
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";


@Component({
  components: {
    
  },
})
export default class SmallImageFiles extends Vue {
 @Prop({
    required: true,
  })
  smallImage;

  isMarked= false;

  setMarked(){
      console.log(this.isMarked)
      this.isMarked = !this.isMarked
  }
}


</script>

<style lang="scss" scoped>
.small-image {
  width: 10vw;
  height: 12vh;
  object-fit: cover;
  
}

.isMarked{
    border: teal solid 3px
}
</style>