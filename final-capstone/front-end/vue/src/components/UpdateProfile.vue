<template>
  <div class="update-prof-main">
  <h1 id="prof-head"><u id="update-prof-line">Create Profile</u></h1>
      <form class="update-profile" >
          <label for="firstName" class="profile-form">First Name: </label>
          <input type="text" id="firstName" placeholder="First Name" required v-model="profile.firstName"/>

           <label for="lastName" class="profile-form">Last Name: </label>
          <input type="text" id="lastName"  placeholder="Last Name" required=true v-model="profile.lastName" />

           <label for="email" class="profile-form">Email Address: </label>
          <input type="email" id="email" placeholder="Email" required=true v-model="profile.email"/>

           <label for="city" class="profile-form">Your City: </label>
          <input type="text" id="city" placeholder="Your City" required=true v-model="profile.city" />

          <button type="submit" v-on:click="saveProfile()">Update Profile</button>
      </form>
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
            city: ""
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
              this.$router.back('/profile' );
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
.update-profile {
font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: blanchedalmond;
  /*object-fit: fill;*/
  height: 400px;
  flex-shrink: 2;
  text-align: center;
  border-radius: 2%;
  margin-left: 200px;
  margin-right: 200px;
  flex-wrap: wrap;
}
#prof-head {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  text-align: center;
  font-size: 2.75em;
  padding-top: 43px;
  padding-bottom: 20px;
  align-content: center;
  /* text-shadow: 2px 5px 5px rgba(8, 8, 8, 0.75),
                -5px 6px 7px rgba(8, 8, 8, 0.75); */
  color: #FF5864;
}
#update-prof-main {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  height: 700px;
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