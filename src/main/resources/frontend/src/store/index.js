import Vue from "vue";
import Vuex from "vuex";
import { messageHandler } from "../core/WebsocketHandler";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loggedInUser: null,
    websocket: null,
    auction: null,
    auctions: null,
    searchResults: null,
    searchWord: "",
  },
  mutations: {
    setWebsocket(state, data) {
      state.websocket = data;
    },
    setAuction(state, value) {
      const clonedValue = JSON.parse(JSON.stringify(value));
      state.auction = clonedValue;
    },
    setAuctions(state, data) {
      state.auctions = data;
    },
    setSearchResults(state, data) {
      state.searchResults = data;
    },
    setSearchWord(state, data) {
      state.searchWord = data;
    },
    setloggedInUser(state, data) {
      state.loggedInUser = data;
    },
    setAuctionBids(state, data) {
      state.auctions
        ?.find((auction) => auction.auction_id === data.auction_id)
        .bids.unshift(data);

      state.auction?.auction_id === data.auction_id &&
        state.auction.bids.unshift(data);
    },
    setNewAuction(state, data) {
      state.auctions?.unshift(data);
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

      if (searchQuery) {
        commit("setSearchWord", searchQuery);
        commit("setSearchResults", auctionResults);
      } else {
        commit("setSearchWord", "");
        commit("setAuctions", auctionResults);
      }
    },

    async whoami({ commit }) {
      let user = await fetch("/auth/whoami");
      try {
        user = await user.json();
        // console.log(user);
        commit("setloggedInUser", user);
      } catch {
        console.log("Not authenticated");
      }
    },

    async connectToWebsocket(store) {
      store.state.websocket = new WebSocket(
        "ws://localhost:3000/your-socket-route"
      );

      store.state.websocket.onopen = () => {
        console.log("Connected...");
      };

      store.state.websocket.onclose = () => {
        console.log("Connection terminated...");
      };

      store.state.websocket.onmessage = (e) => {
        messageHandler(store, e);
      };
    },
  },
  modules: {},
});
