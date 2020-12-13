<template>
<body class="register-body">
  <div class="whatever">
    <h1 class="title2">Restaurant Tinder</h1>
  </div>
  <div class="banner2">
    <img src='@/img/banner.jpeg'/>
  </div>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 id="create-account" class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>

      <div class="username-reg">
      <label for="username" class="sr-only"> </label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus/>
      </div>

      <div class="password-reg">
      <label for="password" class="sr-only"> </label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required/>
      </div>

      <div class="confirm-reg">
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required/>
      </div>


      
      <button id="btn" class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      <router-link class="link-account" :to="{ name: 'login' }">Have an account?</router-link>
      <div class="logo-account">
      <img src='../img/logo-color.png'/>
    </div>
    </form>
  </div>
  </body>
</template>

<script>
import authService from '../services/AuthService';
import appService from '../services/ApplicationServices';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      profile: {
        userName: '',
        firstName: '',
        lastName: '',
        emailAddress: '',
        zipCode: ''
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.profile.userName = this.user.username;
              this.registerProfile();
              this.$router.push({
                path: '/',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },   
      registerProfile() {
        appService.createProfileFromUsername(this.profile);
      },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>



<style>
 .form-register {
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
.register-body {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas: 
  "ban ban ban"
  "whatever whatever whatever"
  ". register ."
  ". register ."
  ". . ."
  ;
  grid-gap: 10px;
}

#register {
  grid-area: register;
  display: flex;
  justify-content: center;
  align-items: center;
}

.whatever {
  grid-area: whatever;
  height: 30%;
  padding-bottom: 40px;
}

.banner2 {
  grid-area: ban;
}

.register-body {
  /* background-image: linear-gradient(to bottom left,  #FF655B, #FD297B); */
  height: 700px;
}

#create-account {
  color: #FD297B;
  border-bottom: 2px solid grey;
  margin-bottom: 18px;
  padding-bottom: 5px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.username-reg {
  display: inline-block;
  font-size: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  
}
.password-reg {
  display: inline-block;
  padding-top: 15px;
  font-size: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}
.confirm-reg {
  display: inline-block;
  padding-top: 15px;
  font-size: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  text-align: right;
}
.form-control {
  height: 1.5em;
  width: 15em;
}
#btn {
  margin-top: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.link-account {
  padding-top: 10px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.logo-account {
  text-align: center;
  padding-top: 4px;
  
}

.logo-account > img {
  width: 100px;
  border-radius: 50%;
}

.title2 {
  text-align: center;
  font-size: 45px;
  margin-top: -10px;
  margin-bottom: -10px;
  color: #FD297B;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.banner2 > img {
  /* width: 1000px; */
  width: 100%;
  max-height: 250px;
}

.banner2 {
  display: flex;
  justify-content: center;
  align-items: center;
}

a {
  color: #FD297B;
  text-decoration: none;
  font-weight: 600;
  font-size: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

a:hover {
  color: #FF655B;
  text-decoration: underline;
}

</style>
