<template>
  <div class="home-page">
    <div class="bg-grey">HEADER D3mTr@din'Bo!z</div>
  <div class="background">
    <img src="../assets/img/auction1.jpg" alt="">
  </div>
  <AuctionList/>
  </div>
</template>

<script>
import { Component, Vue } from "vue-property-decorator";
import  AuctionList  from "../components/AuctionList"

@Component({
  components: {
    AuctionList,
  },
})
export default class Home extends Vue {

}
</script>

<style lang="scss" scoped>
.background{
  max-width: 100vw;
  overflow: hidden;
  max-height: 30vh;
}
img{
  width: 100%;
  object-fit: cover;
}

</style>
