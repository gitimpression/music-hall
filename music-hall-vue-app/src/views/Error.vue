<template>
  <div class="error-panel">
    <div class="error-content">
      <div class="error-text-item">
        ran into some issues, it could be
      </div>
      <div class="error-info-list">
        <ul>
          <li>server error</li>
          <li>page not found</li>
        </ul>
      </div>
      <div class="error-text-item" style="display: flex; flex-direction: row; align-items: center">
        <div>
          try visiting the <a href="http://localhost:8080/" style="color: #ffffff;text-decoration:none">homepage</a>
          again
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Error',
  data() {
    return {
      cursorInterval: null
    }
  },
  mounted() {
  },
  methods: {
  }
}
</script>

<style lang="sass" scoped>
$fontSize: 1.75rem
$paddingTop: 4rem
.error-panel
  color: #c9c9c9
  font-size: $fontSize
  display: flex
  flex-direction: row
  justify-content: center
  padding-top: $paddingTop
  .error-content
    display: flex
    flex-direction: column
    align-items: flex-start
    padding: 10px
    .error-text-item
      text-align: left
      a
        color: #c9c9c9
    .error-info-list
      ul
        padding-left: 20px
        li
          list-style: disc

@media (min-width: 0px) and (max-width: 480px)
  .error-panel
    font-size: 1rem

@media (min-width: 480px) and (max-width: 800px)
  .error-panel
    font-size: 1.5rem
</style>