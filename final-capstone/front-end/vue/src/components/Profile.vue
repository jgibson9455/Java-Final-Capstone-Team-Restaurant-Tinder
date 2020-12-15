<template>

<body class="profile">
    <!-- <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Mada&display=swap" rel="stylesheet"> -->

  <link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Bad+Script&display=swap" rel="stylesheet">

  <div class="space"></div>

  <div class="banner">
    <img src='@/img/banner.jpeg'/>
  </div>

  <div class='pic'>
    <img class="profile-pic" src='@/img/profile.png'/>
  </div>

  <h1 id="my-profile">My Profile</h1>

  <div class="home">
    <div class="top-half">

          <div class="profile-nav">
              <router-link class="profile-navs" v-bind:to="{name: 'update-profile-view'}">Update Profile Info</router-link> |
              <router-link class="profile-navs" v-bind:to="{name: 'questionnaire'}">Food Questionnaire</router-link>
          </div>
    
       <router-link class="restaurant-navs" v-bind:to="{name: 'restaurants'}">View All Restaurants</router-link> 
    
       <router-link v-bind:to="{name: 'match-making'}">
         <button class="start"
         ><b>Continue Swiping</b></button>
        </router-link>

        <h2 class="fav-head"
          v-on:click="showFavs = (showFavs ? false : true)"
          ><u id="fav-rest-line">{{showFavs === false ? "View Favorites" : "Hide Favorites"}}</u></h2>
      
      <div class="rest-card" v-if="showFavs === true">
        
        <restaurant-card class="flex-container"
          v-for="restaurant in fav"
          v-bind:key="restaurant.restaurantId"
          v-bind:restaurant="restaurant"
        ></restaurant-card>
        
      </div>
   
    </div>
  <footer class="footer"><img src="@/img/logo-black.png"></footer>
  </div>
</body>
</template>

<script>
import RestaurantCard from '../components/RestaurantCard.vue'
import appService from '../services/ApplicationServices'
import ZomatoServices from '../services/ZomatoServices'

export default {
    name: 'profile',
      data() {
        return {
         profile: {
           userName: "",
           firstName: "",
           lastName: "",
           email: "",
           city: ""
      },
      favoriteRestaurant: {},
      fav: [],
      matching: [],
      showFavs: true

    }
  },
    components: {
    RestaurantCard
  },
  created() {
    appService.getMatchingResultsByUserName(this.$store.state.user.username).then((response) =>{
      this.matching = response.data;
    }).then(() =>{
      this.matching.forEach((match) =>{
        ZomatoServices.getRestaurantById(match.restaurantId).then((apiData) =>{
            this.favoriteRestaurant.restaurantId = apiData.data.id;
            this.favoriteRestaurant.restaurantName =  apiData.data.name;
            this.favoriteRestaurant.restaurantDescrip = "Cuisines: " + apiData.data.cuisines  + 
            " Hours of Operation: " + apiData.data.timings + " Average Rating: " +
            apiData.data.user_rating.aggregate_rating;
            this.favoriteRestaurant.zipCode =  apiData.data.location.zipcode;
            this.favoriteRestaurant.city =  apiData.data.location.locality;
            this.favoriteRestaurant.phoneNumber =  apiData.data.phone_numbers;
            this.favoriteRestaurant.address =  apiData.data.location.address;
            this.favoriteRestaurant.imageLink = 'https://cdn.dribbble.com/users/1012566/screenshots/4187820/topic-2.jpg'

          this.fav.push(this.favoriteRestaurant);
          this.favoriteRestaurant = {};
        })
      })
    })
  },  
}
</script>

<style>
.banner > img {
  width: 150%;
  max-height: 175px;
}
.banner {
  display: flex;
  justify-content: center;
  align-items: center;
}
.profile {
  /* font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif; */
  /* font-family: 'Mada', sans-serif; */
  height: 25em;
  align-content: center;
  padding-top: 30px;
  font-size: 17px;
}
#my-profile {
  font-family: 'Bad Script', cursive;
  font-size: 2.85em;
  padding-bottom: 25px;
  align-content: center;
  color: #FF5864;
}
.home > .top-half {
  display: flex;
  flex-direction: column;
  text-align: center;
  align-items: center;
  margin-left: 200px;
  margin-right: 200px;
  padding-top: 25px;
  padding-bottom: 20px;
}
.profile-nav {
  margin-top: -30px;
  /* font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif; */
  font-family: 'Mada', sans-serif;
}
.profile-nav > .profile-navs {
  color: black;
  font-size: 25px;
}
.restaurant-navs{
  color: black;
  cursor: pointer;
  margin-top: 15px;
  font-size: 22px;
  /* font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif; */
  font-family: 'Mada', sans-serif;

}
.restaurant-navs:hover{
  color: #FE3C72;
}
.profile-navs:hover{
  color: #FE3C72;  
}
.favorite-restaurants{
    font-size: 24px;
}
#my-profile {
  /* text-align: center; */
  margin-bottom: -30px;
  margin-top: -10px;
}
.fav {
  text-decoration: underline;
  text-decoration-color: lightgray;
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
  align-items: center;
  margin-left: 200px;
  margin-right: 200px;
  margin-top: 80px;
}
.middle {
  padding-left: -10px;
  padding-right: -10px;
}
.rest-1, .rest-2, .rest-3 {
  background-color: #FF5864;
  color: white;
  border: 3px solid black;
  border-radius: 3%;
}
.start {
     background-color: blanchedalmond;
     font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
     box-shadow: 
     0 1px 2px #fff, 
     0 -1px 1px #666, 
     inset 0 -1px 1px rgba(0,0,0,0.5),
     inset 0 1px 1px rgba(255,255,255,0.8);  
     border-radius: 40px;
     border: none;
     color: #FD297B;
     padding: 16px 32px;
     text-align: center;
     font-size: 40px; 
     /* margin: 2px 1px; */
     opacity: 0.6;
     transition: 0.3s;
     display: inline-block;
     text-decoration: none;
     cursor: pointer;
     margin-top: 30px;
}
.start:hover{
  opacity: 1;
}
.profile-pic {
  border-radius: 50%;
  width: 8.5em;
  margin-top: -70px;
  margin-left: 20px;
  /* opacity: 100%; */
}
/* .pic {
  display: flex;
  justify-content: center;
  margin-bottom: -20px;
} */


.favorites {
   font-size: 1.5em; 
   display: flex;
   flex-direction: column;
}
.fav-head {
  display: flex;
  color: #FE3C72;
  justify-content: center;
  font-size: xx-large;
  font-family: 'Mada', sans-serif;
  /* font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif; */
  font-family: 'Mada', sans-serif;

}
#fav-rest-line {
  color: #FE3C72;
  cursor: pointer;
}

#fav-rest-line:hover{
  color: black;
}
.logo {
  display: flex;
  justify-content: center;
  padding: 5%;
}
.rest-card {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: flex-end;
  /* display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr; */
  text-align: center;

}
img {
    width: 2em;
}
.footer{
  /* position: fixed;  */
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 175px;
  bottom: 0; 
  width: 100%; 
  height: 3em; 
  background:#FF5C5C; 
  opacity: 65%;
}
</style>