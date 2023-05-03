<template>
  <div class="login-panel">

    <!-- login form -->
    <input type="text" placeholder="account" v-model="user.account">
    <input type="password" placeholder="password" v-model="user.password">
    <button :disabled="loading" @click="handleSubmit()">sign in</button>
    <span class="msg" :class="msgType">{{ msg }}</span>

    <!-- loding panel -->
    <div class="login-loading" v-show="loading">
      <span class="login-loading-text">
        {{ loadingMsg }}
      </span>
    </div>

  </div>
</template>

<script>
import { login } from "@/request/user";
export default {
  name: 'Login',
  data() {
    return {
      loading: false,
      msg: "Please enter your account and password",
      msgType: "info",
      msgTypeEnum: {
        success: "success",
        info: "info",
        error: "error",
        warn: "warn",
      },
      loadingMsg: "loading",
      user: {
        account: "admin",
        password: "admin"
      }
    }
  },
  methods: {
    handleSubmit() {
      this.loading = true
      login(this.user).then((res) => {
        console.log(res);
        this.loading = false
        this.msg = res.msg
        if (res.ok) {
          this.msgType = this.msgTypeEnum.success
          localStorage.setItem("token", res.data)
          this.$router.replace({ name: 'Home' });
        } else {
          this.msgType = this.msgTypeEnum.error
        }
      }).catch((err) => {
        console.log(err);
      });
    }
  },
}
</script>

<style lang="sass" scoped>
.login-panel
  width: 300px
  background-color: white
  display: flex
  flex-direction: column
  padding: 40px
  text-align: center
  background-color: rgba(255 ,255 ,255 , .8)
  border-radius: 6px
  margin-left: 50%
  margin-top: 10%
  transform: translateX(-50%)
  overflow: hidden
  position: absolute
  input
    margin-bottom: 20px
    outline: none
    font-size: 18px
    padding: 4px
    background-color: transparent
    border: none
    border-bottom: solid 1px grey
    transition: border-color .3s
    &:focus
      border-bottom-color: black
  button
    font-size: 18px
    padding: 4px
    cursor: pointer
    margin-bottom: 16px
    background-color: transparent
    border: solid 1px grey
    transition: border-color .3s
    &:hover
      border-color: black
  .msg
    text-align: left
    font-size: 14px
  .login-loading
    position: absolute
    top: 0
    left: 0
    bottom: 0
    right: 0
    background-color: rgba(255 ,255 ,255 , .45)
    .login-loading-text
      height: 100%
      color: black
      display: flex
      justify-content: center
      align-items: center

// 提示消息类型样式
.error
  color: #a30000
.success
  color: green
.info
  color: gery
.warn
  color: #af7100
</style>