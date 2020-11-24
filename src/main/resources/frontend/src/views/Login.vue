<template>
  <div class="login-wrapper">
    <h2 class="text-primary">Log in</h2>
    <form @submit.prevent="loginForm">
      <div class="form-group">
        <input
          type="email"
          required
          class="form-control"
          placeholder="Username.."
        />
      </div>
      <div class="form-group">
        <input
          type="password"
          required
          class="form-control"
          placeholder="Password.."
        />
      </div>
      <button class="btn btn-primary" type="submit" value="submit">
        Log in
      </button>
      <button class="btn btn-primary">Register</button>
    </form>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";

@Component({
  components: {},
})
export default class Login extends Vue {
  loginForm() {}
}
</script>

<style lang="scss" scoped>
.login-wrapper {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  h2 {
    grid-column: 1/2;
  }

  .form-group {
    grid-column: 1/2;
  }
}
</style>
