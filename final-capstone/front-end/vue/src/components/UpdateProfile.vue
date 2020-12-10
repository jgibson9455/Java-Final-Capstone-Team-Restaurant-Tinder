<template>
  <div>
  <h1>Create Profile </h1>
      <form class="profile" >
          <label for="firstName" class="profile-form">First Name: </label>
          <input type="text" id="firstName" placeholder="First Name" required=true v-model="profile.firstName"/>

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
              this.$router.back('/home');
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

<style>


</style>