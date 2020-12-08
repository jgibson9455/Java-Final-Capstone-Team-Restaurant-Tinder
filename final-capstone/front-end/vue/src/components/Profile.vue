<template>
<body>
   <div class="home">
    
    <h1> My Profile</h1>

    <p>This is your profile</p>
   <router-link v-bind:to="{name: 'update-profile-view'}">Update Profile</router-link> |
   <router-link v-bind:to="{name: 'questionnaire'}">Food Questionnaire</router-link> |
   <router-link v-bind:to="{name: 'restaurants'}">Restaurant List</router-link>
   
  </div>
</body>
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
           zipCode: ""
      }
    }
  }
}
</script>

<style>
.home {
  text-align: center;
}

</style>