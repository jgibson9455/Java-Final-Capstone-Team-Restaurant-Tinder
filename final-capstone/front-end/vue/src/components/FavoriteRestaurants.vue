<template>
  <div class="favorites">

    <!-- <div class="loading" v-if="isLoading">
       <img src='@/img/celerywalk.gif'/>
    </div> -->

    <h2 class="fav-head"
    v-on:click="showFavs = (showFavs ? false : true)"
    ><u id="fav-rest-line">{{showFavs === false ? "View Favorites" : "Hide Favorites"}}</u></h2>
    <div class="rest-card" v-if="showFavs === true">
      <restaurant-card class="flex-container"
      v-for="restaurant in fav"
      v-bind:key="restaurant.restaurantId"
      v-bind:restaurant="restaurant"
      ></restaurant-card></div>
      <!-- <div class="logo"><img src="../img/logo-black.png"></div> -->
  </div>
  
</template>

<script>
import RestaurantCard from '../components/RestaurantCard.vue'
import appService from '../services/ApplicationServices'
import ZomatoServices from '../services/ZomatoServices'
export default {
  data() {
    return {
      favoriteRestaurant: {},
      fav: [],
      matching: [],
      showFavs: true
      // isLoading: true
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
  props: [
    'restaurant'
  ]
}

</script>

<style scoped>
/* .loading {
    display: flex;
    justify-content: center;
} */
.favorites {
   margin-top: 39px;
   font-size: 1.5em; 
   font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
   display: flex;
   flex-direction: column;
}
.fav-head {
  display: flex;
  /*color: #FD297B;*/
  color: #FF5864; 
  justify-content: center;
}
#fav-rest-line {
  color: #FD297B;
  cursor: pointer;
}

#fav-rest-line:hover{
  color: #FF655B;
}
.logo {
  display: flex;
  justify-content: center;
  padding: 5%;
}
.rest-card {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  text-align: center;
  align-items: center;
  /*display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  
  flex-direction: row;*/
}
</style>