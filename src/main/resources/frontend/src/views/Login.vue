<template>
  <div class="login-wrapper">
    <h2 class="">Log in</h2>
    <form action="" class="login-form">
      <input type="email" required class="email-input" />
      <input type="password" required class="password-input" />
    </form>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";

@Component({
  components: {},
})
export default class Login extends Vue {}
</script>

<style lang="scss" scoped></style>
