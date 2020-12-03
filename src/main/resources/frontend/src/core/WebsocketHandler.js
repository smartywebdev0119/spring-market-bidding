export const messageHandler = (store, e) => {
  let data = JSON.parse(e.data);

  switch (data.action) {
    case "newBid":
      store.commit("setAuctionBids", data.payload);
      break;
    case "newAuction":
      data.payload.bids = [];
      console.log("New Auction: ", data.payload);
      store.commit("setNewAuction", data.payload);
      break;
    default:
      break;
  }
};
