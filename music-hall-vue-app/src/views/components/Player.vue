<template>
  <div class="player-panel">
    <div class="player-control">
      <!-- 上一曲 -->
      <div class="play-control-btn">
        <svg t="1682918670857" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
          p-id="8828" width="32" height="32">
          <path
            d="M364.302083 465.602819L687.954365 218.588294c38.416414-29.327534 93.791393-1.929039 93.791392 46.396277v494.029051c0 48.325316-55.374979 75.725617-93.791392 46.398084L364.302083 558.397181c-30.600916-23.357989-30.600916-69.436372 0-92.794362zM238.945254 780.798397V451.684117v-164.562559c0-19.628152-5.904521-60.475733 17.057907-75.841215 25.523642-17.068744 59.747828 1.210165 59.747828 31.919454v493.676839c0 19.628152 5.915358 60.473927-17.047069 75.841215-25.53448 17.068744-59.758666-1.211971-59.758666-31.919454z"
            fill="#c9c9c9" p-id="8829"></path>
        </svg>
      </div>

      <!-- 播放 -->
      <div class="play-control-btn" @click="handleMusicPlay()" v-show="!playing">
        <svg t="1682919329797" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
          p-id="12499" width="32" height="32">
          <path
            d="M512 0C230.4 0 0 230.4 0 512s230.4 512 512 512 512-230.4 512-512S793.6 0 512 0z m0 981.333333C253.866667 981.333333 42.666667 770.133333 42.666667 512S253.866667 42.666667 512 42.666667s469.333333 211.2 469.333333 469.333333-211.2 469.333333-469.333333 469.333333z"
            fill="#c9c9c9" p-id="12500"></path>
          <path
            d="M672 441.6l-170.666667-113.066667c-57.6-38.4-106.666667-12.8-106.666666 57.6v256c0 70.4 46.933333 96 106.666666 57.6l170.666667-113.066666c57.6-42.666667 57.6-106.666667 0-145.066667z"
            fill="#c9c9c9" p-id="12501"></path>
        </svg>
      </div>

      <!-- 暂停 -->
      <div class="play-control-btn" @click="handleMusicPause()" v-show="playing">
        <svg t="1682920125131" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
          p-id="13469" width="32" height="32">
          <path
            d="M512 1024C228.266667 1024 0 795.733333 0 512S228.266667 0 512 0s512 228.266667 512 512-228.266667 512-512 512z m0-42.666667c260.266667 0 469.333333-209.066667 469.333333-469.333333S772.266667 42.666667 512 42.666667 42.666667 251.733333 42.666667 512s209.066667 469.333333 469.333333 469.333333z m-106.666667-682.666666c12.8 0 21.333333 8.533333 21.333334 21.333333v384c0 12.8-8.533333 21.333333-21.333334 21.333333s-21.333333-8.533333-21.333333-21.333333V320c0-12.8 8.533333-21.333333 21.333333-21.333333z m213.333334 0c12.8 0 21.333333 8.533333 21.333333 21.333333v384c0 12.8-8.533333 21.333333-21.333333 21.333333s-21.333333-8.533333-21.333334-21.333333V320c0-12.8 8.533333-21.333333 21.333334-21.333333z"
            fill="#c9c9c9" fill-opacity=".9" p-id="13470"></path>
        </svg>
      </div>

      <!-- 下一曲 -->
      <div class="play-control-btn">
        <svg t="1682918692275" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
          p-id="" width="32" height="32">
          <path
            d="M655.706179 465.602819L332.053897 218.588294c-38.414608-29.327534-93.791393-1.929039-93.791392 46.396277v494.029051c0 48.325316 55.376785 75.725617 93.791392 46.398084l323.652282-247.014525c30.602722-23.357989 30.602722-69.436372 0-92.794362zM781.064814 780.798397V451.684117v-164.562559c0-19.628152 5.904521-60.475733-17.057907-75.841215-25.523642-17.068744-59.747828 1.210165-59.747828 31.919454v493.676839c0 19.628152-5.915358 60.473927 17.047069 75.841215 25.532673 17.068744 59.758666-1.211971 59.758666-31.919454z"
            fill="#c9c9c9" p-id="9609"></path>
        </svg>
      </div>

      <audio ref="audio" style="width: 0; height:0" type="audio/mpeg" :src="musicSrc" controls
        :autoplay="playing"></audio>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Player',
  props: {
    playing: {
      type: Boolean,
      require: true
    },
    musicSrc: {
      type: String,
      require: false
    }
  },
  data() {
    return {
    }
  },
  methods: {
    // 点击 暂停播放按钮
    handleMusicPause() {
      this.$emit("changePlayStatus", false)
    },
    // 点击 播放按钮
    handleMusicPlay() {
      this.$emit("changePlayStatus", true)
    }

  },
  watch: {
    playing(newVal, oldVal) {
      console.log("play status ", oldVal, " -> ", newVal);
      if (newVal) {
        this.$refs.audio.play()
      } else {
        this.$refs.audio.pause()
      }
    }
  },
  mounted() {
  }
}
</script>

<style lang="sass" scoped>
.player-panel
  width: 100%
  height: 80px
  color: white
  background-color: rgba(255, 255, 255, 0.1)
  position: absolute
  bottom: 0
  left: 320px
  .player-control
    display: flex
    align-items: center
    .play-control-btn
      margin-left: 24px
      margin-top: 24px
      cursor: pointer
</style>