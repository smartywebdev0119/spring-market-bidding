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
    searchWord: "",
  },
  mutations: {
    setWebsocket(state, data) {
      state.websocket = data;
    },
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
    setAuctionBids(state, data) {
      state.auctions
        ?.find((auction) => auction.auction_id === data.bidAuction.auction_id)
        .bids.unshift(data);

      state.auction?.auction_id === data.bidAuction.auction_id &&
        state.auction.bids.unshift(data);
    },
    setNewAuction(state, data) {
      
      state.auctions?.unshift(data);
    }
  },
  actions: {
    async fetchAuction({ commit }, id) {
      const raw = await fetch(`/api/v1/auctions/${id}`);
      const auction = await raw.json();
      let bids = await fetch(`/api/v1/bids/auction/${auction.auction_id}`);
      bids = await bids.json(); 
      auction.bids = bids;
      commit("setAuction", auction);
    },

    async fetchAllAuctionsWithQuery({ commit }, searchQuery) {
      let auctionResults = await fetch(
        `/api/v1/auctions?search=${searchQuery}`
      );
      auctionResults = await auctionResults.json();

      for (let auction of auctionResults) {
        let bids = await fetch(`/api/v1/bids/auction/${auction.auction_id}`);
        bids = await bids.json();
        auction.bids = bids;
      }

      console.log(auctionResults);
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
      }
    },
  },
  modules: {},
});
