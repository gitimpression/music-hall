<template>
  <div class="home">

    <div class="left">
      <!-- 左侧 - 音乐列表 -->
      <MusicList :musicData="musicData" @changeMusicData="changeMusicData" @changeCurrentMusic="changeCurrentMusic"
        :playingMusic="currentMusicInfo" />
    </div>

    <div class="right">
      <!-- 右侧 - 歌曲内容 -->
      <Content :music="currentMusicInfo" />
      <!-- 右侧底部 - 音乐播放器 - 播放控制  -->
      <Player :music="currentMusicInfo" :playing="playing" @changePlayStatus="changePlayStatus"
        @changeMusicPre="changeMusicPre" @changeMusicNext="changeMusicNext" @changeCurrentMusic="changeCurrentMusic" />
    </div>

  </div>
</template>

<script>

import MusicList from "@/views/components/MusicList.vue";
import Content from "@/views/components/Content.vue";
import Player from "@/views/components/Player.vue";

export default {
  name: 'Home',
  components: {
    MusicList,
    Content,
    Player
  },
  data() {
    return {
      musicData: [], // 所有歌曲信息
      currentMusicInfo: {}, // 当前播放的音乐
      playing: false // 播放状态
    }
  },
  methods: {
    // 歌单数据变化
    changeMusicData(data) {
      this.musicData = data
    },
    // 切歌
    changeCurrentMusic(music) {
      // 网络在线
      if (window.navigator.onLine) {
        // 当前歌曲信息
        this.currentMusicInfo = music
        // 播放
        this.playing = true
      } else {
        // 无网络
        console.log("请检查网络连接...");
      }

    },
    // 播放状态改变
    changePlayStatus(val) {
      this.playing = val
    },
    // 下一曲
    changeMusicNext() {
      // 下一曲索引
      let targetMusicIndex = this.musicData.indexOf(this.currentMusicInfo) + 1
      if (targetMusicIndex >= this.musicData.length) {
        // 没有下一首 从头开始播放
        console.log("没有下一首 从头开始播放");
        if (this.musicData.length > 0) {
          this.currentMusicInfo = this.musicData[0]
        } else {
          alert("当前没有歌曲可以播放")
        }
      } else {
        // 下一曲
        this.currentMusicInfo = this.musicData[targetMusicIndex]
      }
      this.changeCurrentMusic(this.currentMusicInfo)
    },
    // 上一曲
    changeMusicPre() {
      // 上一曲索引
      let targetMusicIndex = this.musicData.indexOf(this.currentMusicInfo) - 1
      if (targetMusicIndex < 0) {
        // 没有上一曲 从尾巴开始播放
        console.log("没有上一曲 从尾巴开始播放");
        if (this.musicData.length > 0) {
          this.currentMusicInfo = this.musicData[this.musicData.length - 1]
        } else {
          alert("当前没有歌曲可以播放")
        }
      } else {
        // 上一曲
        this.currentMusicInfo = this.musicData[targetMusicIndex]
      }
      this.changeCurrentMusic(this.currentMusicInfo)

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
  },
  watch: {
    currentMusicInfo(newVal, oldVal) {
      console.log(oldVal.name, " -> ", newVal.name);
      if (!newVal.name) {
        this.playing = false
      }
    },
    playing(newVal) {
      if (!this.currentMusicInfo.name && newVal) {
        // 没有选择歌曲但开启播放
        this.playing = false
      }
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

@media ( min-width: 0px) and ( max-width: 820px)
  .home
    .left
      margin-bottom: 74px
      width: 100%
      padding: 10px
      width: calc( 100% - 20px )
      .music-list-panel
        width: 100%
    .right
      width: 0px
</style>
