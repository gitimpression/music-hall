<template>
  <div class="content-panel">
    <!-- 模糊背景 -->
    <div class="blur-bg" :style="{backgroundImage: 'url('+getSingerImgSrc+')'}">
    </div>
    <!-- 歌手内容 -->
    <div class="singer-panel">
      <img :src="getSingerImgSrc" v-show="getSingerImgSrc && music.name" width="260" height="260">
    </div>
    <!-- 歌曲内容 -->
    <div class="song-panel" v-show="music.name">
      <h2>{{ music.name ? music.name : "-" }}</h2>
      <span><span style="color: #c9c9c9">专辑：</span> {{ music.album ? music.album : "-" }}</span>
      <span style="margin-left: 4rem"><span style="color: #c9c9c9">歌手：</span>
        {{ music.singer ? music.singer : "-" }}</span>
      <div class="song-word">
        <ul>
          <li>
            {{ music.name ? "暂无歌词" : "-" }}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { getSingerImg } from "@/data/singer.js";
export default {
  name: 'Content',
  props: {
    music: {
      type: Object,
      require: false,
    }
  },
  filters: {
    durationFilter(val) {
      return `${(val / 60).toFixed(0)}:${val % 60}`
    }
  },
  data() {
    return {
    }
  },
  computed: {
    // 获取歌手照片
    getSingerImgSrc() {
      return getSingerImg(this.music.singer)
    }
  },
  methods: {
  },
}
</script>

<style lang="sass" scoped>
.content-panel
  min-width: 300px
  width: calc( 100% - 40px )
  height: calc( 100% - 120px )
  color: white
  background-color: rgba(0, 0, 0, .2)
  padding: 100px 20px
  display: flex
  flex-direction: row
  justify-content: center
  .blur-bg
    background-repeat: no-repeat
    background-size: cover
    filter: blur(90px)
    position: absolute
    width: 100%
    height: 969px
    left: 0
    top: 0
    opacity: .6
  .singer-panel
    margin-right: 116px
    z-index: 9
  .song-panel
    z-index: 9
    .song-word
      max-height: 400px
      overflow: auto
      margin-top: 2rem
      ul
        li
          color: #c9c9c9
          margin-bottom: .5rem

@media ( min-width: 0px) and ( max-width: 820px)
  .content-panel
    display: none

  // 滚动条设置样式
*::-webkit-scrollbar
  width: 8px

*::-webkit-scrollbar-thumb
  background: #727272 // 滑块颜色
  border-radius: 4px // 滑块圆角

// 兼容Firefox、IE
*
  scrollbar-width: 10px
</style>