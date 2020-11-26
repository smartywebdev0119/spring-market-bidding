<template>
  <div class="login-wrapper">
    <h2 class="text-primary">Log in</h2>
    <form @submit.prevent="login">
      <input
        id="username"
        type="text"
        required
        class="form-control"
        placeholder="Username.."
      />
      <input
        id="password"
        type="password"
        required
        class="form-control"
        placeholder="Password.."
      />
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
  login(e) {
    let loginCredentials = {
      username: e.target.username.value,
      password: e.target.password.value,
    };
    this.attemptLogIn(loginCredentials);
  }

  async attemptLogIn(userToLogin) {
    let credentials =
      "username=" +
      encodeURIComponent(userToLogin.username) +
      "&password=" +
      encodeURIComponent(userToLogin.password);

    let response = await fetch("/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: credentials,
    });
    console.log(response);
    if (response.url.includes("error")) {
      console.log("You fuckin failed!");
    } else {
      console.log("You fucking success!");
      await this.$store.dispatch("whoami");
      this.$router.push("/");
    }
    // try {
    //   response = await response.json();
    //   if (response.error) {
    //     console.log("Wrong username/password");
    //   } else {
    //     console.log("Successfully logged in");
    //     await this.$store.dispatch("whoami");
    //     this.$router.push("/");
    //   }
    // } catch (e) {
    //   console.log("ERROR")
    // }
  }
}
</script>

<style lang="scss" scoped>
.login-wrapper {
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
