<template>
  <div class="player-panel">
    <div class="player-control" @mousemove="handleMousemove">

      <!-- 上一曲 -->
      <div class="play-control-btn" @click="handleMusicPre()">
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
      <div class="play-control-btn" @click="handleMusicNext()">
        <svg t="1682918692275" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
          p-id="" width="32" height="32">
          <path
            d="M655.706179 465.602819L332.053897 218.588294c-38.414608-29.327534-93.791393-1.929039-93.791392 46.396277v494.029051c0 48.325316 55.376785 75.725617 93.791392 46.398084l323.652282-247.014525c30.602722-23.357989 30.602722-69.436372 0-92.794362zM781.064814 780.798397V451.684117v-164.562559c0-19.628152 5.904521-60.475733-17.057907-75.841215-25.523642-17.068744-59.747828 1.210165-59.747828 31.919454v493.676839c0 19.628152-5.915358 60.473927 17.047069 75.841215 25.532673 17.068744 59.758666-1.211971 59.758666-31.919454z"
            fill="#c9c9c9" p-id="9609"></path>
        </svg>
      </div>

      <audio @ended="handleMusicEnded()" @playing="handleMusicPlaying()" ref="audio" style="width: 0; height:0"
        @error="handleMusicError()" type="audio/mpeg" :src="getMusicSrc" @canplay="handleMusicCanplay()"></audio>

      <!-- 进度控制 -->
      <div class="controls-bar">
        <!-- 歌曲信息 -->
        <div class="music-info">
          <div class="singer" ref="singer">
            <!-- 内容过多滚动显示 -->
            <div class="singer-info" ref="singerInfo">
              {{ getMusicName }}
            </div>
          </div>
          <div class="time">
            {{  currentTime | timeFilter }}/{{music.duration | timeFilter}}
          </div>
        </div>
        <!-- 音乐进度控制 -->
        <div class="play-bar">
          <div class="drag-playhead" id="drag-playhead" ref="drag" @mousedown="handleDragMousedown"
            @mousemove="handleDragMousemove">
            <!-- 实际进度条 -->
            <span class="real-play-bar" :style="{width: getPlayheadMarginLeft}">
            </span>
            <!-- 进度控制点 -->
            <span class="playhead" :style="{marginLeft: playheadMarginLeft + 'px'}" id="playhead" ref="dragPointer"
              @mousedown="handleDragPointerMousedown" @mouseup="handleDragPointerMouseup"
              @mousemove="handleDragPointerMousemove">
            </span>
          </div>
        </div>

      </div>
      <!-- 音乐音量控制 -->
      <div class="volume-control">
        <svg t="1683092177907" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
          p-id="2623" width="24" height="24">
          <path
            d="M260.256 356.576l204.288-163.968a32 32 0 0 1 52.032 24.96v610.432a32 32 0 0 1-51.968 24.992l-209.92-167.552H96a32 32 0 0 1-32-32v-264.864a32 32 0 0 1 32-32h164.256zM670.784 720.128a32 32 0 0 1-44.832-45.664 214.08 214.08 0 0 0 64.32-153.312 213.92 213.92 0 0 0-55.776-144.448 32 32 0 1 1 47.36-43.04 277.92 277.92 0 0 1 72.416 187.488 278.08 278.08 0 0 1-83.488 198.976zM822.912 858.88a32 32 0 1 1-45.888-44.608A419.008 419.008 0 0 0 896 521.152c0-108.704-41.376-210.848-114.432-288.384a32 32 0 0 1 46.592-43.872c84.16 89.28 131.84 207.04 131.84 332.256 0 127.84-49.76 247.904-137.088 337.728z"
            fill="#c9c9c9" p-id="2624"></path>
        </svg>
        <div class="volume-bar" id="volume-bar" ref="volumeBar" @mousedown="handleVolumeBarMousedown"
          @mousemove="handleVolumeBarMousemove">
          <!-- 实际音量条 -->
          <span class="real-volume-bar" :style="{width: getVolumeMarginLeft}">
          </span>
          <!-- 音量控制点 -->
          <span class="volume-head" :style="{marginLeft: getVolumeMarginLeft}" id="volume-head" ref="volumeHead"
            @mousedown="handleVolumeHeadMousedown" @mouseup="handleVolumeHeadMouseup"
            @mousemove="handleVolumeHeadMousemove">
          </span>
        </div>
      </div>
      <!-- 播放模式 -->
      <div class="play-mode">
        <!-- 顺序播放 -->
        <svg v-show="playMode=='seq'" @click="handleChangePlayMode('ran')" t="1683277907229" class="icon"
          viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2128" width="16" height="16">
          <path
            d="M342.69 297.61c-22.96 0-41.3-18.63-41.3-41.32 0-22.67 18.34-41.03 41.3-41.03h457.64c61.35 0 117.2 25.32 157.94 65.49 40.76 40.71 65.73 96.88 65.73 158.23v146.07c0 61.65-24.97 117.52-65.73 158.23-40.73 40.17-96.58 65.46-157.94 65.46H342.69c-61.68 0-117.52-25.29-158.23-65.46-36.09-36.62-60.52-85.52-64.6-139.06h-95.4C11.09 604.22 0 592.88 0 579.52c0-6.71 2.93-13.1 7.57-17.16l67.21-67.82 67.8-67.48c9.29-9.59 24.73-9.59 34.32 0h0.56l67.5 67.48 67.21 67.82c9.58 9.29 9.58 25 0 34.91-5.24 4.65-12.22 7.57-19.23 6.95h-91.02c4.05 31.15 18.9 59.66 40.11 80.9h0.3c25.88 25.59 61.41 41.56 100.37 41.56h457.64c38.66 0 74.16-15.98 99.75-41.56C925.97 659.53 942 624.01 942 585.05V438.98c0-38.96-16.03-74.46-41.91-100.05-25.59-25.91-61.09-41.32-99.75-41.32H342.69zM187.63 555.35h47.74l-25.32-25.88v-0.27l-50.32-50.05-50.34 50.05v0.27L83.8 555.35h103.83z"
            fill="#c9c9c9" p-id="2129"></path>
        </svg>
        <!-- 随机播放 -->
        <svg v-show="playMode=='ran'" @click="handleChangePlayMode('seq')" t="1683278250122" class="icon"
          viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2521" width="16" height="16">
          <path
            d="M823.456 243l-109.68-109.72c-15.619-15.625-15.614-40.951 0.01-56.57 15.623-15.618 40.95-15.613 56.568 0.01l177.935 178C973.481 279.923 955.633 323 920 323H747.43c-37.616 0-72.746 18.808-93.615 50.122l-23.12 34.693c-12.25 18.383-37.085 23.354-55.468 11.103-18.383-12.25-23.354-37.085-11.103-55.468l23.12-34.692C622.948 275.182 683.059 243 747.43 243h76.025zM394.812 617.51c12.251-18.383 37.085-23.354 55.468-11.103 18.383 12.25 23.354 37.085 11.103 55.468l-72.885 109.367C352.794 824.818 292.683 857 228.311 857H104c-22.091 0-40-17.909-40-40s17.909-40 40-40h124.311c37.617 0 72.747-18.808 93.616-50.122l72.885-109.368zM742.888 781c-61.531 0-118.99-30.757-153.114-81.96L319.396 293.325A104 104 0 0 0 232.854 247H104c-22.091 0-40-17.909-40-40s17.909-40 40-40h128.854c61.531 0 118.99 30.757 153.114 81.96l270.378 405.714A104 104 0 0 0 742.888 701H920c35.633 0 53.481 43.078 28.29 68.28l-177.936 178c-15.618 15.623-40.945 15.628-56.569 0.01-15.623-15.619-15.628-40.945-0.01-56.57L823.456 781h-80.568z"
            fill="#c9c9c9" p-id="2522"></path>
        </svg>
      </div>
    </div>
  </div>
</template>

<script>
import { musicHallCDN } from "@/request/music";
export default {
  name: 'Player',
  props: {
    playing: {
      type: Boolean,
      require: true
    },
    music: {
      type: Object,
      require: true
    }
  },
  data() {
    return {
      currentTime: 0,
      currentTimeTimer: null,
      isDraging: false, // 是否正在拖动进度条
      isVolume: false, // 是否正在拖动音量条
      playheadMarginLeft: 0, // 进度点位移
      volumeMarginLeft: 0, // 音量点偏移
      defaultVolume: 0.3, // 播放器 默认音量
      lyricsAnimTimer: null, // 歌词动画
      playMode: "seq", // 默认播放模式为顺序播放
    }
  },
  methods: {
    // 点击 暂停播放按钮
    handleMusicPause() {
      this.emitChangePlayStatus(false)
    },
    // 点击 播放按钮
    handleMusicPlay() {
      this.emitChangePlayStatus(true)
    },
    // 上一曲
    handleMusicPre() {
      this.$emit("changeMusicPre")
    },
    // 下一曲
    handleMusicNext() {
      this.$emit("changeMusicNext")
    },
    // 播放完毕
    handleMusicEnded() {
      this.emitChangePlayStatus(false)
      // 下一曲
      this.$emit("changeMusicNext")
    },
    // 歌曲可以播放
    handleMusicCanplay() {
      console.log("canplay ...");
      if (this.playing) {
        this.$refs.audio.play()
      }
    },
    // 加载完成, 准备开始播放
    handleMusicPlaying() {
      this.emitChangePlayStatus(true)
    },
    // 歌曲加载错误
    handleMusicError() {
      let errCode = this.$refs.audio.error.code
      console.log("error code: ", errCode);
      // 暂停播放
      this.emitChangePlayStatus(false)
      // 检查错误类型
      switch (errCode) {
        case 1:
          // 1 = MEDIA_ERR_ABORTED - 取回过程被用户中止
          console.log("取回过程被用户中止");
          break;
        case 2:
          // 2 = MEDIA_ERR_NETWORK - 当下载时发生错误
          console.log("下载时发生错误");
          break;
        case 3:
          // 3 = MEDIA_ERR_DECODE - 当解码时发生错误
          console.log("解码时发生错误");
          break;
        case 4:
          // 4 = MEDIA_ERR_SRC_NOT_SUPPORTED - 不支持音频/视频
          console.log("不支持音频");
          break;
        default:
          console.log("未知错误, errCode:", errCode);
          break;
      }
    },
    // 切换播放模式
    handleChangePlayMode(val) {
      console.log("切换播放模式：", val);
      this.playMode = val
    },
    // 进度控制 - 按下 进度条
    handleDragMousedown(e) {
      this.isDraging = true
      this.setPlayheadMarginLeft(e)
    },
    // 进度控制 - 拖动 进度条
    handleDragMousemove(e) {
      this.setPlayheadMarginLeft(e)
    },
    // 进度控制 - 点击 进度点
    handleDragPointerMousedown() {
      this.isDraging = true
    },
    // 进度控制 - 松开 进度点
    handleDragPointerMouseup() {
      this.isDraging = false
      this.setCurrentTime()
    },
    // 进度控制 - 拖动 进度点
    handleDragPointerMousemove(e) {
      this.setPlayheadMarginLeft(e)
    },
    // 移动鼠标
    handleMousemove(e) {
      if (this.isDraging) {
        // 调整 进度偏移
        this.setPlayheadMarginLeft(e)
      } else if (this.isVolume) {
        // 调整 音量偏移
        this.setVolumeMarginLeft(e)
      }
    },
    // 音量控制 - 按下 音量条
    handleVolumeBarMousedown(e) {
      this.isVolume = true
      this.setVolumeMarginLeft(e)
    },
    // 音量控制 - 拖动 音量条
    handleVolumeBarMousemove(e) {
      // 设置偏移
      this.setVolumeMarginLeft(e)
      // 设置音量
      this.setVolume()
    },
    // 音量控制 - 按下 控制点
    handleVolumeHeadMousedown() {
      this.isVolume = true
    },
    // 音量控制 - 松开 控制点
    handleVolumeHeadMouseup() {
      this.isVolume = false
      // 设置音量
      this.setVolume()
    },
    // 音量控制 - 拖动 控制点
    handleVolumeHeadMousemove(e) {
      this.setVolumeMarginLeft(e)
    },
    // 设置音量
    setVolume() {
      // 根据偏移确定音量大小
      let v = (this.volumeMarginLeft / document.getElementById("volume-bar").offsetWidth).toFixed(1)
      if (v > 0 && v < 1) {
        this.$refs.audio.volume = v
      }
    },
    // 设置进度
    setCurrentTime() {
      if (this.music.duration) {
        let c = this.playheadMarginLeft / document.getElementById("drag-playhead").offsetWidth * this.music.duration
        this.$refs.audio.currentTime = c
      }
    },
    // 设置 进度控制点 偏移量
    setPlayheadMarginLeft(e) {
      if (this.isDraging) {
        let x = e.pageX - 320 - this.$refs.dragPointer.offsetLeft - 5
        this.playheadMarginLeft += x
      }
    },
    // 设置 音量控制点 偏移量
    setVolumeMarginLeft(e) {
      if (this.isVolume) {
        let x = e.pageX - 320 - this.$refs.volumeHead.offsetLeft - 5
        this.volumeMarginLeft += x
      }
    },
    // 初始化 音量
    initVolume() {
      this.$refs.audio.volume = this.defaultVolume
      this.volumeMarginLeft = document.getElementById("volume-bar").offsetWidth * this.defaultVolume
    },
    // 开启歌词长度检测
    startLyricsTimer() {
      let i = -20
      this.lyricsAnimTimer = setInterval(() => {
        // 检测歌曲名称长度
        let w1 = this.$refs.singerInfo.offsetWidth
        let w2 = this.$refs.singer.offsetWidth
        if (!w1 || !w2) {
          return
        }
        // 歌词过长 需要动态展示
        if (w2 < w1) {
          // 歌词 超出长度
          let w3 = w1 - w2
          // i为负数表示等待
          i++
          if (i >= 0) {
            this.$refs.singerInfo.style.marginLeft = -i + "px"
          }
          if (i >= w3) {
            // 等待2秒 重播
            i = -20;
          }
        }
      }, 100);
    },
    // 发送事件 改变播放状态
    emitChangePlayStatus(val) {
      this.$emit("changePlayStatus", val)
    },
  },
  watch: {
    playing(newVal, oldVal) {
      console.log("play status ", oldVal, " -> ", newVal);
      if (newVal) {
        this.$refs.audio.play()
        // 更新播放时长
        this.currentTimeTimer = setInterval(() => {
          this.currentTime = Math.ceil(this.$refs.audio.currentTime)
          // 进度条改变
          let percentage = this.currentTime / this.music.duration
          let width = document.getElementById("drag-playhead").offsetWidth
          this.playheadMarginLeft = percentage * width
        }, 500);
      } else {
        this.$refs.audio.pause()
        // 暂停 清除计时器
        clearInterval(this.currentTimeTimer)
      }
    },
    music() {
      // 歌曲切换时q
      if (!this.music) {
        console.log("没有歌曲，清除定时器");
        clearInterval(this.currentTimeTimer)
        clearInterval(this.lyricsAnimTimer)
        return
      }

      // 开启进度定时器
      if (this.music.name && this.playing && !this.currentTimeTimer) {
        console.log("开启进度定时器");
        this.currentTimeTimer = setInterval(() => {
          // 更新播放时长
          this.currentTime = Math.ceil(this.$refs.audio.currentTime)
          // 进度条改变
          let percentage = this.currentTime / this.music.duration
          let width = document.getElementById("drag-playhead").offsetWidth
          this.playheadMarginLeft = percentage * width
        }, 500);
      }

      // 歌词偏移重置
      this.$refs.singerInfo.style.marginLeft = "0px"
    },
    playheadMarginLeft() {
      if (this.playheadMarginLeft < 0) {
        this.playheadMarginLeft = 0
      } else if (this.playheadMarginLeft > document.getElementById("drag-playhead").offsetWidth) {
        this.playheadMarginLeft = document.getElementById("drag-playhead").offsetWidth
      }
    },
    volumeMarginLeft() {
      if (this.volumeMarginLeft < 0) {
        this.volumeMarginLeft = 0
      } else if (this.volumeMarginLeft > document.getElementById("volume-bar").offsetWidth) {
        this.volumeMarginLeft = document.getElementById("volume-bar").offsetWidth
      }
    },
  },
  computed: {
    getMusicSrc() {
      return this.music.name ? musicHallCDN + this.music.name : ""
    },
    getPlayheadMarginLeft() {
      return this.playheadMarginLeft + "px"
    },
    getVolumeMarginLeft() {
      return this.volumeMarginLeft + "px"
    },
    getMusicName() {
      return this.music.name ? this.music.name : ""
    }
  },
  filters: {
    // 61 -> 01:01
    timeFilter(val) {
      if (!val) {
        return "--:--"
      }
      let m = Math.floor(val / 60)
      if (m < 10) {
        m = "0" + m
      }
      let s = val % 60
      if (s < 10) {
        s = "0" + s
      }
      return m + ":" + s
    }
  },
  mounted() {
    // 松开鼠标 滑动状态取消
    document.onmouseup = () => {
      this.isVolume = false
      this.isDraging = false
    }
    // 初始化音量
    this.initVolume()

    // 开启歌词动画检测定时器
    this.startLyricsTimer()

    // 监控网络状态
    window.addEventListener("offline", () => {
      console.log("网络链接中断");
    })
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
    .controls-bar
      padding-left: 20px
      display: flex
      flex-direction: column
      align-items: center
      padding-top: 22px
      .music-info
        width: 100%
        display: flex
        flex-direction: row
        justify-content: space-between
        .singer
          width: 300px
          color: #9f9f9f
          font-size: 14px
          height: 18px
          line-height: 18px
          position: relative
          overflow: hidden
          .singer-info
            position: absolute
            white-space: nowrap
        .time
          color: #9f9f9f
          font-size: 14px
          height: 18px
          line-height: 18px
      .play-bar
        width: 400px
        height: 20px
        .drag-playhead
          width: 100%
          height: 4px
          margin-top: 8px
          background-color: #727272
          cursor: pointer
          .real-play-bar
            background-color: #c9c9c9
            height: 4px
            display: inline-block
            position: absolute
          .playhead
            width: 10px
            height: 10px
            position: absolute
            display: inline-block
            background-color: white
            margin-top: -2px
            border-radius: 50%
            // ransition: margin-left .1s 这行代码不要打开
    .volume-control
      width: 160px
      height: 40px
      margin-left: 20px
      margin-top: 20px
      display: flex
      flex-direction: row
      align-items: center
      .volume-bar
        width: 100%
        height: 4px
        background-color: #727272
        margin-left: 10px
        cursor: pointer
        .real-volume-bar
          background-color: #c9c9c9
          height: 4px
          display: inline-block
          position: absolute
        .volume-head
          position: absolute
          width: 10px
          height: 10px
          background-color: white
          border-radius: 50%
          margin-top: -3px
          cursor: pointer
    .play-mode
      margin-left: 20px
      margin-top: 24px
      cursor: pointer
</style>