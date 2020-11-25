import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    auctions: null,
    searchWord: ""
  },
  mutations: {
    updateAuctions(state, data) {
      state.auctions = data;
    },
    setSearchWord(state, data){
      state.searchWord = data;
    }
  },
  actions: {
    async fetchAllAuctionsWithQuery({ commit }, searchQuery) {
      let auctionResults = await fetch(
        `/api/v1/auctions?search=${searchQuery}`
      );
      auctionResults = await auctionResults.json();
      commit("updateAuctions", auctionResults);
      commit("setSearchWord", searchQuery)
    },
  },
  modules: {},
});
