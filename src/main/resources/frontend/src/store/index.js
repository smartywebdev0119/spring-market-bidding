import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loggedInUser: null,

    auction: null,
    auctions: null,
    searchWord: "",
  },
  mutations: {
    setAuction(state, value) {
      state.auction = value;
    },
    setAuctions(state, data) {
      state.auctions = data;
    },
    setSearchWord(state, data) {
      state.searchWord = data;
    },
    setloggedInUser(state, data) {
      state.loggedInUser = data;
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
      commit("setAuctions", auctionResults);
      commit("setSearchWord", searchQuery);
    },
    async whoami({ commit }) {
      let user = await fetch("/auth/whoami");
      try {
        user = await user.json();
        console.log(user);
        commit("setloggedInUser", user);
      } catch (e) {
        e.printStackTrace();
        console.log("Not authenticated");
      }
    },

    
  },
  modules: {},
});
