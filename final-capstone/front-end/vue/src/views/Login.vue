<template>
<body>
<<<<<<< HEAD
  <div class="main-title">
=======
  <head> <meta name="viewport" content="width=device-width, initial-scale=1.0"> </head>
  <div class="whatever">
>>>>>>> 3e48aec24fdab643f7f43960e38946508300e31f
    <h1 class="title"><strong>Restaurant Tinder</strong></h1>
  </div>
  
  <div class="loading" v-if="isLoading">
    <img src='@/img/celerywalk.gif'/>
  </div>


  <div class="banner">
    <img src='@/img/banner.jpeg'/>
  </div>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 id="login-title" class="h3 mb-3 font-weight-normal">Login</h1>
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
      <label for="username" class="sr-only">  </label>
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
      <label for="password" class="sr-only">  </label>
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
    <!-- <footer class="footer"><img src="@/img/logo-black.png"></footer> -->
</body>
</template>

<script>
import ApplicationServices from '../services/ApplicationServices';
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      isLoading: true,
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false,
      profile: {
            userName: "",
            firstName: "",
            lastName: "",
            email: "",
            city: ""
      }
    };
  },
  created() {
    this.isLoading = false;
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token)
            this.$store.commit("SET_USER", response.data.user)
            ApplicationServices.getProfileByUsername(response.data.user.username)
            .then((response) => { 
            // this.profile = response.data.profile
            this.$store.commit("UPDATE_PROFILE", response.data);
            this.$router.push({ path: '/matches' });
            })
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Bad+Script&display=swap');
form {
margin-bottom: 50px;
}
.form-signin {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: blanchedalmond;
  object-fit: fill;
  height: 420px;
  width: 400px;
  text-align: center;
  border-radius: 5%;
  border-radius: 5%;
  border: 2px solid black;
}

body {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas: 
  "banner banner banner"
  "title title title"
  ". login ."
  ". login ."
  ". footer ."
  ;
  grid-gap: 10px;
}

#login {
  grid-area: login;
  display: flex;
  justify-content: center;
  align-items: center;
}

.main-title {
  grid-area: title;
  height: 30%;
  padding-bottom: 40px;
}

.banner {
  grid-area: banner;
}

body {
  /* background-image: linear-gradient(to bottom left,  #FF655B, #FD297B); */
  height: 700px;
}

#login-title {
  color: #FD297B;
  border-bottom: 2px solid grey;
  padding-bottom: 5px;
}

.title {
  text-align: center;
  font-size: 50px;
  margin-top: -10px;
  margin-bottom: -20px;
  color: #FD297B;
  font-family: 'Bad Script', cursive;
}

#username-signin, #password-singin {
  display: inline-block;
  padding: 20px;
}

.form-control {
  padding: 5px;
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

#login-title, .sr-only, button, .link {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.logo {
  text-align: center;
  padding-top: 11px;
}

.logo > img {
  width: 100px;
  border-radius: 50%;
}

.banner > img {
  /* width: 1000px; */
  width: 100%;
  max-height: 250px;
}

.banner {
  display: flex;
  justify-content: center;
  align-items: center;
}

a {
  color: #FD297B;
  text-decoration: none;
  font-weight: 600;
  font-size: 20px;
}

a:hover {
  color: #FF655B;
  text-decoration: underline;
}
img {
    width: 2em;
}
/* .footer{
  grid-area: footer;
  position: fixed;  
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 175px;
  bottom: 0; 
  width: 92em; 
  height: 3em; 
  background:#FF5C5C; 
  opacity: 65%;
}*/
@media screen and (max-width: 800px) {
  
  .main {
    height: 50em;
  }

  body {
    grid-template-columns: 1fr;
  }
  img {
    display: none;
}
.form-signin {
   flex-direction: row;
   flex-wrap: wrap;
   justify-content: center;
   align-content: center;
}
.footer {
 display: none;
}
}
</style>
