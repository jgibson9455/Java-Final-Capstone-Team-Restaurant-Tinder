<template>
<body>
  <div class="whatever"></div>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Login</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <div id="username-signin">
      <label for="username" class="sr-only">username:  </label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      </div>
      <div id="password-signin">
      <label for="password" class="sr-only">password:  </label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      </div>
      
      <button type="submit">Sign in</button>
      <router-link class="link" :to="{ name: 'register' }">Create an Account</router-link>

    <div class="logo">
      <img src='../img/logo-color.png'/>
    </div>
    </form>
    
  </div>
</body>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/home");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.form-signin {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: white;
  object-fit: fill;
  height: 400px;
  text-align: center;
  border-radius: 1%;
  
}

body {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas: 
  "whatever whatever whatever"
  ". login ."
  ". login ."
  ". . ."
  ;
  grid-gap: 10px;
}

#login {
  grid-area: login;
}

.whatever {
  grid-area: whatever;
  height: 30%;
  padding-bottom: 40px;
}

body {
  background-image: linear-gradient(to bottom left,  #FF655B, #FD297B);
  height: 700px;
}

h1 {
  border-bottom: 2px solid grey;
  padding-bottom: 5px;
}

#username-signin, #password-singin {
  display: inline-block;
  padding: 10px;
}

.sr-only {
  font-size: 20px;
}

button {
  margin-top: 20px;
}

.link {
  padding-top: 20px;
}

h1, .sr-only, button, .link {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.logo {
  text-align: center;
  padding-top: 20px;
}

img {
  width: 100px;
  border-radius: 50%;
}

a {
  color: #FD297B;
  text-decoration: none;
  font-weight: 600;
}

a:hover {
  color: #FF655B;
  text-decoration: underline;
}
</style>
