<template>
<!-- <u id="update-prof-line">-->
  <div class="update-prof-main">
    
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
    </div>

      <!-- <div class="banner">
        <img src='@/img/banner.jpeg'/>
      </div> -->

  </div>
</template>

<script>
import ApplicationServices from '../services/ApplicationServices';
//import appServices from "@/services/ApplicationServices.js"
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
  /* align-content: center; */
  /* justify-content: center; */
  /* align-self: center; */
  background-color: blanchedalmond;
  padding-top: 50px;
  /* height: 250px; */
  width: 300px;
  text-align: center;
  border-radius: 2%;

  margin-left: 200px;
  margin-right: 200px;
  padding-left: 25px;
  padding-right: 25px;
  flex-wrap: wrap;
}
  /* flex-shrink: 2; */

#prof-head {
  /*font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;*/
  /*font-family: 'mada', sans-serif;*/
  font-family: 'Bad Script', cursive;
  text-align: center;
  font-size: 3.75em;
  padding-top: 43px;
  padding-bottom: 20px;
  margin-bottom: -10px;
  align-content: center;
  /* text-shadow: 2px 5px 5px rgba(8, 8, 8, 0.75),
                -5px 6px 7px rgba(8, 8, 8, 0.75); */
  color: #FF5864;
}
.form {
  display: flex;
  justify-content: center;
}
#update-prof-main {
  /* display: flex; */
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  /* height: 700px; */
  /* justify-content: center; */
}
.banner > img {
  /* width: 1000px; */
  width: 100%;
  max-height: 125px;
}
.banner {
  display: flex;
  justify-content: center;
  align-items: center;
}
#update-button {
  margin-top: 20px;
  margin-bottom: 20px;
}
/* .food-form{
 width: 300px;
position: relative;
left: -20px;
display: inline-block;
vertical-align: middle; 
} */
/* input[type="checkbox"] {
  width: 20px;
  position: relative;
  left:100px;
  vertical-align: middle;
}
.margin {
  padding: 10px;
} */

</style>