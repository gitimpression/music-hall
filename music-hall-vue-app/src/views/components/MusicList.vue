<template>
  <div class="music-list-panel">
    <div class="search">
      <input type="text" v-model="keyword" placeholder="search...">
    </div>
    <div class="list">
      <ul>
        <!-- onselectstart 取消双击选择 -->
        <li v-show="musicData.length == 0">
          无数据
        </li>
        <li :class="{active: playingMusic.name == item.name}" v-for="item in musicData" :key="item.name"
          @click="handleMusicItemClick(item)" onselectstart="return false" :title="item.name">
          {{ item.name }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MusicList',
  props: {
    musicData: {
      type: Array,
      require: true
    },
    playingMusic: {
      type: Object,
      require: false
    }
  },
  data() {
    return {
      keyword: "",
    }
  },
  methods: {
    // 歌单被双击
    handleMusicItemClick(music) {
      this.$emit("changeCurrentMusic", music)
    }
  },
  watch: {
    keyword() {
      if (this.keyword == '') {
        // 清空了搜索框
        this.$emit('changeMusicData', this.$store.getters.musicData)
      } else {
        // 过滤歌单
        let newData = this.musicData.filter(music => music.name.includes(this.keyword))
        this.$emit('changeMusicData', newData)
      }
    }
  },
}
</script>

<style lang="sass" scoped>
.music-list-panel
  position: relative
  z-index: 9
  width: 300px
  height: 100%
  background-color: rgba(0, 0, 0, 0.5)
  display: inline-block
  .search
    width: 100%
    display: flex
    flex-direction: column
    margin-bottom: 5px
    input
      background-color: transparent
      border: 1px solid #727272
      outline: none
      padding: 8px
      font-size: 14px
      color: #b8b8b8
      border-radius: 4px
  .list
    height: calc( 100% - 50px )
    overflow: auto
    ul
      cursor: pointer
      li
        color: #666666
        margin: 4px auto
        transition: color .2s
        padding: 6px auto
        font-size: 16px
        user-seletct: none
        text-overflow: ellipsis
        white-space: nowrap
        overflow: hidden
        &:hover
          color: #959595

@media ( min-width: 0px) and ( max-width: 540px)
  .music-list-panel
    .search
      input
        font-size: 18px
    .list
      ul
        li
          font-size: 18px
          margin-bottom: 6px

@media ( min-width: 540px) and ( max-width: 820px)
  .music-list-panel
    .search
      input
        font-size: 22px
    .list
      ul
        li
          font-size: 22px
          margin-bottom: 10px

// 正在播放的音乐
.active
  color: white!important

// 滚动条设置样式
*::-webkit-scrollbar
  width: 8px

*::-webkit-scrollbar-thumb
  background: #363636 // 滑块颜色
  border-radius: 4px // 滑块圆角

// 兼容Firefox、IE
*
  scrollbar-width: 10px
</style>