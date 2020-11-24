<template>
  <div class="">
    <div class="card h-100 mb-6" style="">
      <div class="">
        <img class="card-img-top" :src="auction.image_URL" alt="Card image cap">
        <div class="card-body">
        <p class="card-title font-italic">{{auction.title}}</p>
        <p class="card-title"><span class="price-font">{{highestBid}}</span> kr - <span class="text-right">{{amountOfBids}} bids</span></p>
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

export default class AuctionListItem extends Vue {
    @Prop ({
    required: true,
    })
    auction

    amountOfBids = 0;
    highestBid = 0;
    bids = [];
    response = [];

  created() {
    this.response = this.getBidByAuctionId(this.auction.auction_id);
  }

  async mounted(){
    this.bids = await this.response;
    this.getHighestBid(this.bids);
    this.amountOfBids = this.bids.length;
  }

  getHighestBid(bids){
    if(bids.length > 0){
      bids.sort(function(a, b){return b.bid_price - a.bid_price});
      this.highestBid = bids[bids.length -1].bid_price; 
    }else{
      this.highestBid = this.auction.start_price
    }
  }

  async getBidByAuctionId(id){
    let bids = await fetch(`/api/v1/bids/auction/${id}`);
    bids = await bids.json();
    return bids;
  }

}
</script>
<style lang="scss" scoped>
.card-title{
    font-size: 0.75em;
}
.card{
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}
.card-title{
  width: 100%;
  white-space: nowrap;
  overflow: hidden;
  overflow: ellipsis;
}
.price-font{
  font-weight: bold;
  font-size: 1.8em;
  color: #288781 
}
</style>