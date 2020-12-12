<template>
  <div class="favorites">
    <h2 class="fav-head"
    v-on:click="showFavs = (showFavs ? false : true)"
    ><u id="fav-rest-line">View Favorites</u></h2>
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
export default {
  data() {
    return {
      fav: [],
      matching: [],
      showFavs: false
    }
  },
  components: {
    RestaurantCard
  },
  created() {
    appService.getMatchingResultsByUserName(this.$store.state.user.username).then(response => {
      this.matching = response.data;
    }).then(res => {
      this.matching.forEach((match) => {
      appService.getRestaurantById(match.restaurantId).then(response => {
        this.fav.push(response.data);
      })
      })
      console.log(res);
    })
  },
  props: [
    'restaurant'
  ]
}

</script>

<style scoped>
.favorites {
   margin-top: 39px;
   font-size: 1.5em; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
   display: flex;
   flex-direction: column;
}
.fav-head {
  display: flex;
  /*color: #FD297B;*/
  color: #FF5864; 
  justify-content: center;
}
#fav-rest-line:hover{
  color: #FD297B;
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