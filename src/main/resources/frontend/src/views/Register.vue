<template>
  <div class="register-wrapper">
    <h2 class="text-primary">Create Account</h2>
    <form @submit.prevent="registerForm">
      <input
        type="text"
        required
        class="form-control"
        id="username"
        placeholder="Username.."
      />
      <input
        type="email"
        required
        class="form-control"
        inputmode="email"
        id="email"
        placeholder="Email.."
      />
      <input
        type="password"
        required
        class="form-control"
        id="password"
        placeholder="Password.."
      />
      <button class="btn btn-primary" type="submit" value="submit">
        Create User
      </button>
      <router-link to="/login" tag="button" class="btn btn-primary"
        >Already a member?</router-link
      >
    </form>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";

@Component({
  components: {},
})
export default class Login extends Vue {
  registerForm() {}
}
</script>

<style lang="scss" scoped>
.register-wrapper {
  h2 {
    text-align: center;
  }

  form {
    display: grid;
    grid-template-columns: 1fr;
    row-gap: 8px;

    input {
      width: 70%;
      justify-self: center;
    }

    .btn {
      width: 55%;
      justify-self: center;
    }
  }
}
</style>
