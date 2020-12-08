<template>
<body class="profile">
  <div class="space"></div>

  <h1 id="my-profile"> My Profile</h1>

   <div class="home">
    
    <div class="welcome">
    <p>Here is where you can view a list of restaurants as well as look at your favorited restaurants that you saved for later.
    Also, you can update your profile as well as choose your food preferences with the Food Questionnaire.</p>
    </div>

    <div class="profile-nav">
   <router-link class="profile-navs" v-bind:to="{name: 'update-profile-view'}">Update Profile</router-link> |
   <router-link class="profile-navs" v-bind:to="{name: 'questionnaire'}">Food Questionnaire</router-link>
    </div>

   <router-link class="restaurant-navs" v-bind:to="{name: 'restaurants'}">View Restaurant List</router-link> 

   <p class="start">Start</p>
   
   <h3 class="fav">Favorite Restaurants</h3>
   <div class="fav-temp">
     <div class="rest-1">
     <p>Restaurant 1</p>
     <img class="first" src="../img/rest-1.jpg"/>
     </div>
     <div class="rest-2">
     <p>Restaurant 2</p>
     <img class="middle" src="../img/rest-2.jpg"/>
     </div>
     <div class="rest-3">
     <p>Restaurant 3</p>
     <img class="last" src="../img/rest-3.jpg"/>
     </div>
   </div>
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
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  height: 700px;
  align-content: center;
  padding-top: 30px;
  /* display: grid;
  grid-template-columns: 1fr 2fr 2fr 1fr;
  grid-template-areas:
  "space space space space"
  ". home home ."
  ". home home ."
  ". home home ."
  ". . . ."
  ;
  grid-gap: 10px; */
}

.home {
  text-align: center;
  background-color: blanchedalmond;

  margin-left: 200px;
  margin-right: 200px;
  /* width: 700px; */
  align-content: center;
  padding-bottom: 50px;
  /* display: flex;
  flex-direction: column;
  object-fit: fill; */
  /* grid-area: home; */
}

/* .space {
  grid-area: space;
} */

.profile-nav > .profile-navs {
  color: black;
  font-size: 15px;
  padding-bottom: 15px;
}

.home > .restaurant-navs {
  color: black;
}

#my-profile {
  text-align: center;
}

.fav {
  text-decoration: underline;
  text-decoration-color: lightgray;
}

#my-profile {
  font-size: 40px;
  padding-top: 10px;
  padding-bottom: 20px;
  align-content: center;
  text-shadow: 2px 5px 5px rgba(8, 8, 8, 0.75),
                -5px 6px 7px rgba(8, 8, 8, 0.75);
    color: blanchedalmond;
}

.welcome {
  font-size: 15px;
  margin-top: -20px;
  padding-top: 20px;
}

.middle, .first, .last {
  width: 200px;
  height: 100px;
  object-fit: cover;
}

.fav-temp {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}

.middle {
  padding-left: -10px;
  padding-right: -10px;
}

.rest-1, .rest-2, .rest-3 {
  background-color: #FF5864;
  color: white;
  border: 3px solid black;
}

.start {
  background-color:#ffbfb4;
     padding-top: 5px;
     padding-bottom: 5px;
     padding-right: 18.5px;
     padding-left: 18.5px;
     border-style: outset;
     border-width: 4px;
     border-color:rgb(100, 38, 38);
     margin-bottom: 2px;
     border-radius: 15px;
}

</style>