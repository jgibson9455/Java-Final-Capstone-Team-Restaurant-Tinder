<template>
<!-- <u id="update-prof-line">-->
  <div class="update-prof-main">

      <div class="banner">
        <img src='@/assets/banner.jpeg'/>
      </div><!--end of banner div-->
  
  <h1 id="prof-head"><strong>Update Profile</strong></h1>
  
    <div class="form">

      <form class="update-profile" >
          <label for="firstName" class="profile-form">First Name: </label>
          <input type="text" id="firstName" placeholder="First Name" v-model="profile.firstName" required="true"/>

           <label for="lastName" class="profile-form">Last Name: </label>
          <input type="text" id="lastName"  placeholder="Last Name" required=true v-model="profile.lastName" />

           <label for="email" class="profile-form">Email Address: </label>
          <input type="email" id="email" placeholder="Email" required=true v-model="profile.email"/>

           <label for="city" class="profile-form">Your City: </label>
          <input type="text" id="city" placeholder="Your City" required=true v-model="profile.city" />

          <label for="greaterCity" class="profile-form">Greater City(ex: Cleveland): </label>
          <input type="text" id="greaterCity" placeholder="Greater City" required=true v-model="profile.greaterCity"/>

          <button id="update-button" type="submit" v-on:click.prevent="saveProfile()">Update Profile</button>
      </form>

    </div><!--end of form div-->

  </div><!--end of update prof main div-->
</template>

<script>
import ApplicationServices from '../services/ApplicationServices';

export default {
       name: 'update-profile',
       data() {
         return {
           profile: {
            firstName: "",
            lastName: "",
            email: "",
            city: "",
            greaterCity: ""
           }
         }
       },
       methods: {
         saveProfile() {
           ApplicationServices.updateProfile(this.profile)
           .then((response) =>{
             alert(
               "Your profile has successfully been updated!"
             )
              this.$store.commit("UPDATE_PROFILE", response.data);
              // if (this.$store.state.token != "") {
              //   this.$router.back({path: '/profile'});
              // } else {
              //   this.$router.push({path: '/'});
              // }
              this.$router.push({path: '/profile'});
              
           })
         }
       },
        created(){
            ApplicationServices.getProfileByUsername(this.$store.state.user.username)
            .then((response)=>{
              this.profile = response.data;
            })
         }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Mada:wght@500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Bad+Script&display=swap');

.update-profile {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center; 
  background-color: blanchedalmond;
  padding-top: 50px;
  width: 300px;
  text-align: center;
  border-radius: 2%;
  margin-left: 200px;
  margin-right: 200px;
  padding-left: 25px;
  padding-right: 25px;
  flex-wrap: wrap;
}

#prof-head {
  font-family: 'Bad Script', cursive;
  text-align: center;
  font-size: 3.75em;
  padding-bottom: 20px;
  margin-bottom: -10px;
  margin-top: -5px;
  align-content: center;
  color: #FF5864;
}
.form {
  display: flex;
  justify-content: center;
}
#update-prof-main {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}
.banner > img {
  width: 99%;
  height: 175px;
}
.banner {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 37px;
}
#update-button {
  margin-top: 20px;
  margin-bottom: 20px;
}

</style>