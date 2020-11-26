export const fetchBidsByAuctionId = async (id) => {
  let bids = await fetch(`/api/v1/bids/auction/${id}`);
  bids = await bids.json();
  return bids;
};
