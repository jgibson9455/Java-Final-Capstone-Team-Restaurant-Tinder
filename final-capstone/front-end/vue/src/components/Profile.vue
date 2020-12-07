<template>
   <div class="home">
    
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
   <router-link v-bind:to="{name: 'update-profile-view'}">Update Profile</router-link>
   <router-link v-bind:to="{name: 'questionnaire'}">Food Questionnaire</router-link>
   <router-link v-bind:to="{name: 'restaurants'}">Restaurant List</router-link>
   
  </div>
</template>

<script>

export default {
    name: 'profile',
      data() {
        return {
         profile: {
           userID: "",
           firstName: "",
           lastName: "",
           email: "",
           zipCode: "",
    }
      }
       }

}
</script>

<style>

</style>