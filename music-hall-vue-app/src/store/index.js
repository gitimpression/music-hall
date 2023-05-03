import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import { musicList } from "@/request/music.js";

export default new Vuex.Store({
  state: {
    musicData: [],
  },
  getters: {
    musicData: (state) => state.musicData,
  },
  mutations: {
    changeMusicData(state, data) {
      state.musicData = data;
    },
  },
  actions: {
    reqMusicData(context) {
      return musicList().then((res) => {
        if (res.ok) {
          context.commit("changeMusicData", res.data);
        } else {
          console.log(res);
          context.commit("changeMusicData", []);
        }
      });
    },
  },
  modules: {},
});
