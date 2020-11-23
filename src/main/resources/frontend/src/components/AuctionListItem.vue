<template>
  <div class="class-based-tutorial">
    <div class="card d-flex h-100 mb-6" style="">
        <div class="col no-gutters">

        <img class="card-img-top" :src="auction.image_URL" alt="Card image cap">
        <div class="card-body">
        <p class="card-title">{{auction.title}}</p>
        </div>
  </div>
</div>
  </div>
</template>

<script>
import { Vue, Component, Prop} from "vue-property-decorator";
@Component({
  
  components: {

  },
})

export default class ClassBasedTutorial extends Vue {
    @Prop ({
    required: true,
    })
    auction

  created() {
    
  }

}
</script>
<style lang="scss" scoped>
.card-title{
    font-size: 0.75em;
}

</style>