<template>
  <div> 
      <h1>Create Profile</h1>
      <form class="profile">
           <label for="firstName" class="profile-form">First Name: </label>
          <input type="text" id="firstName" placeholder="First Name" required=true />

           <label for="lastName" class="profile-form">Last Name: </label>
          <input type="text" id="lastName" placeholder="Last Name" required=true/>

           <label for="email" class="profile-form">Email Address: </label>
          <input type="email" id="email" placeholder="email" required=true/>

           <label for="zipCode" class="profile-form">Zip Code: </label>
          <input type="text" id="zipCode" placeholder="Zip Code" required=true/>

          <input type="submit"/>
      </form>
    

  </div>
</template>

<script>
export default {
       name: 'update-profile',
       
       
}
</script>

<style>

</style>