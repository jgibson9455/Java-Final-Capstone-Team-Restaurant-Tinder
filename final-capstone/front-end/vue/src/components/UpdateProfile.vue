<template>
  <div> 
      <h1>Create Profile</h1>
      <form class="profile">
           <label for="firstName" class="profile-form">First Name: </label>
          <input type="text" id="firstName" placeholder="First Name" required=true v-model="profile.firstName"/>

           <label for="lastName" class="profile-form">Last Name: </label>
          <input type="text" id="lastName" placeholder="Last Name" required=true v-model="profile.lastName"/>

           <label for="email" class="profile-form">Email Address: </label>
          <input type="email" id="email" placeholder="email" required=true v-model="profile.email"/>

           <label for="zipCode" class="profile-form">Zip Code: </label>
          <input type="text" id="zipCode" placeholder="Zip Code" required=true v-model="profile.zipCode" />

          <input type="submit"/>
      </form>
    

  </div>
</template>

<script>
import appServices from "@/services/ApplicationServices.js"
export default {
       name: 'update-profile',
       props: ["userName"],
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
           appServices.updateProfile(this.userName, profile).then(response => {
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
         appServices.get(this.userName).then(response => {
           this.$store.commit("UPDATE_PROFILE", response.data);
           this.firstName = response.data.firstName,
           this.lastName = response.data.lastName,
           this.email = response.data.email,
           this.zipCode = response.data.zipCode
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