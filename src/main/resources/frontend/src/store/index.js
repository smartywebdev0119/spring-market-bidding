import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    auctions: []
  },
  mutations: {
    setAuctions(state, auctions){
      state.auctions = auctions;
    }
  },
  actions: {

    async getAuctions(store){
      let auctions = await fetch("/api/v1/auctions");
      auctions = await auctions.json();
      store.commit("setAuctions", auctions)
    }
  },
  modules: {

  }
})
