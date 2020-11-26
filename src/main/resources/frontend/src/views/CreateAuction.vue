<template>
  <div class="container">
    <div class="text-center header-text">Create Auction</div>
    <div>
        <p>Title</p>
        <input type="text" value="" required>
    </div>
    <div>
        <p>Starting price</p>
        <input/>
    </div>
    <div>
        <p>Auction end date</p>
        <input/>
    </div>
    <div>
        <p>Image url</p>
        <input/>
    </div>
    <div>
        <p>Description</p>
        <input/>
    </div>
    <button type="button" class="btn btn-primary">>Create auction</button>
    
  </div>
</template>

<script>
import { Component, Vue } from "vue-property-decorator";

@Component({
  components: {
    
  },
})
export default class Home extends Vue {

    newAuction = {
        title: null,
        description: null,
        start_price: null,
        end_date: null,
        image_URL: null,
        timestamp: null
    };

  created(){}
    
}
</script>

<style lang="scss" scoped>
.header-text{
    font-size: 2em;
    color: #288781;
}

</style>
