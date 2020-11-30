<template>
  <div class="register-wrapper">
    <h2 class="text-primary">Create Account</h2>
    <form @submit.prevent="registerForm">
      <input
        type="text"
        required
        class="form-control"
        :class="errorMessage ? 'is-invalid' : ''"
        v-model="user.username"
        placeholder="Username.."
      />
      <small class="form-text text-danger" v-show="errorMessage">
        {{ this.errorMessage }}
      </small>
      <input
        type="email"
        required
        class="form-control"
        inputmode="email"
        v-model="user.email"
        placeholder="Email.."
        pattern="[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+"
      />
      <input
        type="password"
        required
        class="form-control"
        v-model="user.password"
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
  async registerForm() {
    await fetch("/api/v1/users", {
      method: "POST",
      headers: {
        "Content-Type": "Application/json",
      },
      body: JSON.stringify(this.user),
    })
      .then((response) => {
        if (response.ok) return response.json();
        else if (response.status == 400) {
          this.errorMessage = `${this.user.username} already exist`;
        }
      })
      .then((data) => {
        if (data) {
          console.log("created: ", data);
        }
      })
      .catch((error) => {
        console.error(error);
      });
  }

  user = {
    username: null,
    email: null,
    password: null,
    roles: "USER",
  };
  errorMessage = "";
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

    small {
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
