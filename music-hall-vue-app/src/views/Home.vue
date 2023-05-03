<template>
  <div class="home">

    <div class="left">
      <!-- 左侧 - 音乐列表 -->
      <MusicList :musicData="musicData" @changeMusicData="changeMusicData" @changeCurrentMusic="changeCurrentMusic" />
    </div>

    <div class="right">
      <!-- 右侧 - 歌曲内容 -->
      <Content :music="currentMusicInfo" />
      <!-- 右侧底部 - 音乐播放器 - 播放控制  -->
      <Player :musicSrc="musicSrc" :playing="playing" @changePlayStatus="changePlayStatus" />
    </div>

  </div>
</template>

<script>

import MusicList from "@/views/components/MusicList.vue";
import Content from "@/views/components/Content.vue";
import Player from "@/views/components/Player.vue";
import { musicHallCDN } from "@/request/music";

export default {
  name: 'Home',
  components: {
    MusicList,
    Content,
    Player
  },
  data() {
    return {
      musicData: [],
      musicSrc: "",
      currentMusicInfo: {},
      playing: false
    }
  },
  methods: {
    // 歌单数据变化
    changeMusicData(data) {
      this.musicData = data
    },
    // 切歌
    changeCurrentMusic(music) {
      // 拼接歌曲URL
      this.musicSrc = musicHallCDN + music.name
      // 当前歌曲信息
      this.currentMusicInfo = music
      // 播放
      this.playing = true
    },
    // 放歌
    // 播放状态改变
    changePlayStatus(val) {
      this.playing = val
    }
  },
  mounted() {
    // 读取歌单
    let mData = this.$store.getters.musicData
    if (mData.length == 0) {
      // 本地没有数据 向服务器请求歌单
      this.$store.dispatch("reqMusicData").then(() => {
        mData = this.$store.getters.musicData
        if (mData.length == 0) {
          console.log("load music data fail ");
          this.musicData = null
        } else {
          this.musicData = mData
        }
      });
    } else {
      this.musicData = mData
    }
  }
}
</script>

<style lang="sass" scoped>
.home
  width: 100%
  height: 100%
  display: flex
  flex-direction: row
  .right
    display: flex
    flex-direction: column
    width: 100%
    height: 100%
</style>
