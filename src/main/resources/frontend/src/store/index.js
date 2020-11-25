import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    auction: null,
    auctions: null,
  },
  mutations: {
    setAuction(state, value) {
      state.auction = value;
    },
    updateAuctions(state, data) {
      state.auctions = data;
    },
  },
  actions: {
    async fetchAuction({ commit }, id) {
      const raw = await fetch(`/api/v1/auctions/${id}`);
      const auction = await raw.json();
      commit("setAuction", auction);
    },
    async fetchAllAuctionsWithQuery({ commit }, searchQuery) {
      let auctionResults = await fetch(
        `/api/v1/auctions?search=${searchQuery}`
      );
      auctionResults = await auctionResults.json();
      commit("updateAuctions", auctionResults);
    },
  },
  modules: {},
});
