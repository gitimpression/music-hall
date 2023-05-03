<template>
  <div class="content-panel">
    包含 歌曲和歌手两部分
    <!-- singer content -->
    <span>

      <ul>
        <li>正在播放：{{ music.name }}</li>
        <li>歌手：{{ music.singer }}</li>
        <li>专辑：{{ music.album }}</li>
        <li>时长: {{ music.duration | durationFilter }}</li>
      </ul>
    </span>
    <!-- song content -->
  </div>
</template>

<script>
export default {
  name: 'Content',
  props: {
    music: {
      type: Object,
      require: false
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
  methods: {
  },
}
</script>

<style lang="sass" scoped>
.content-panel
  width: calc( 100% - 40px )
  height: calc( 100% - 120px )
  color: white
  background-color: rgba(255, 255, 255, .2)
  padding: 20px
</style>