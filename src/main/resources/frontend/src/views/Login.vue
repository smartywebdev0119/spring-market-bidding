<template>
  <div class="login-wrapper">
    <h2 class="text-primary">Log in</h2>
    <form @submit.prevent="loginForm">
      <input type="email" required class="form-control" placeholder="Username.." />
      <input type="password" required class="form-control" placeholder="Password.." />
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
.login-wrapper form {
  display: grid;
  grid-template-columns: 1fr;
  row-gap: 8px;

  input{
    width: 70%;
    justify-self: center;
  }
  
  .btn{
    width: 55%;
    justify-self: center;
  }
}
</style>
