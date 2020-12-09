<template>
  <div>
  <h1>Create Profile</h1>
      <form class="profile" >
          <label for="firstName" class="profile-form">First Name: </label>
          <input type="text" id="firstName" placeholder="First Name" required=true v-model="firstName"/>

           <label for="lastName" class="profile-form">Last Name: </label>
          <input type="text" id="lastName" placeholder="Last Name" required=true v-model="lastName"/>

           <label for="email" class="profile-form">Email Address: </label>
          <input type="email" id="email" placeholder="email" required=true v-model="email"/>

           <label for="zipCode" class="profile-form">Zip Code: </label>
          <input type="text" id="zipCode" placeholder="Zip Code" required=true v-model="zipCode" />

          <button type="submit" v-on:click="saveProfile()">Update Profile</button>
      </form>
    

  </div>
</template>

<script>
import appServices from "@/services/ApplicationServices.js"
export default {
       name: 'update-profile',
       props: ["profile"],
       data() {
         return {
            firstName: "",
            lastName: "",
            email: "",
            zipCode: ""
         }
       },
       methods: {
         saveProfile() {
           const profile = {
             firstName: this.firstName,
             lastName: this.lastName,
             email: this.email,
             zipCode: this.zipCode
             
           };
           appServices.updateProfile(profile).then(response => {
             if(response.status === 200) {
               this.$router.push(`/profile`)
             }
           }).catch(msgError => {
             if(msgError.response.status == 404) {
               alert("error");
             }
           });
         }
       },
       created() {
         appServices.getProfileByUsername().then(response => {
           this.$store.commit("UPDATE_PROFILE", response.data);
          this.profile = response.data;
         })
         .catch(error => {
           if(error.response.status == 404) {
             alert("error: 404 ya dummy");
           }
         });
       }
       
};
</script>

<style>


</style>