<template>
<body class="profile">
   <div class="home">
    
    <h1> My Profile</h1>
    <div class="welcome">
    <p>Here is where you can view a list of restaurants as well as look at your favorited restaurants that you saved for later. <br>
    Also, you can update your profile as well as choose your food preferences with the Food Questionnaire.</p>
    </div>

    <div class="profile-nav">
   <router-link class="profile-navs" v-bind:to="{name: 'update-profile-view'}">Update Profile</router-link> |
   <router-link class="profile-navs" v-bind:to="{name: 'questionnaire'}">Food Questionnaire</router-link>
    </div>

    

   <router-link class="restaurant-navs" v-bind:to="{name: 'restaurants'}">View Restaurant List</router-link> 
   
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
.profile {
  background-image: linear-gradient(to bottom left, #FF655B, #FD297B);
  height: 700px;
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas:
  ". . ."
  ". home ."
  ". home ."
  ". home ."
  ". . ."
  ;
  grid-gap: 10px;

}

.home {
  text-align: center;
  background-color: white;
  width: 700px;
  align-content: center;
  display: flex;
  flex-direction: column;
}

.profile-nav > .profile-navs {
  color: black;
  font-size: 15px;
  padding-bottom: 15px;
}

.home > .restaurant-navs {
  color: black;
}

.home > h1 {
  font-size: 40px;
  padding-top: 50px;
  display: block;
  border-bottom: 5px solid lightgray;
}

.welcome {
  font-size: 15px;
}

</style>