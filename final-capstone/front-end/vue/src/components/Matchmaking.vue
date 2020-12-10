<template>
<div class="main">
<div class="heading">
    <h1>Heading</h1>  
</div>

<div class="random"><h2> {{ randomRestaurant.restaurantName }}</h2></div>

<img class="image" v-bind:src="randomRestaurant.imageLink"/>
 
  <div class="buttons">
      


      <div class="dislike">
         <button v-on:click.prevent="addToDislike(randomRestaurant)">Dislike</button>
      </div>

      <div class="skip">
         <button v-on:click.prevent="getRandomRestaurant()">Skip</button>
      </div>

      <div class="like">
         <button
         v-on:click.prevent="addRestaurantToFavorites(randomRestaurant)"
         >Like</button>
      </div>

 </div>

  </div>

</template>

<script>
import ApplicationServices from '../services/ApplicationServices.js'
export default {
    name: 'match-making',
    data() {
        return{
        //likes: [],
        //dislikes: [],
        restaurants: [],
        randomRestaurant: {},
        matchingResult: {
            userName: this.$store.state.user.username,
            restaurantId: 0,
            preferenceId: 0
        }
        }

    },
    created() {
        ApplicationServices.getAllRestaurants()
        .then(apiData => {
            this.restaurants = apiData.data;
            this.getRandomRestaurant();
        })
          
    },
    methods:{
        getRandomRestaurant(){
            let randomNum = Math.floor(Math.random() * this.restaurants.length-1);
            ApplicationServices.getRestaurantById(randomNum).then(apiData => {
                this.randomRestaurant = apiData.data;
            })
        },
        addRestaurantToFavorites(restaurant) {
            this.matchingResult.preferenceId = 1;
            this.matchingResult.restaurantId = restaurant.restaurantId;
            ApplicationServices.saveMatchingResult(this.matchingResult);
            this.getRandomRestaurant();
        },
        addToDislike(restaurant) {
            this.$store.commit('ADD_TO_DISLIKE', restaurant);
            console.log(this.$store.state.dislikes);
            this.getRandomRestaurant();
        }

    }
      
}
</script>

<style scoped>
.image {
    height: 250px;
    width: 250px;
    border-style: solid;
    border-color: black;
    border-width: 2px;
    border-radius: 5px; 
}
.main {
    display: flex;
    align-items: center;
    flex-direction: column;

}
.buttons {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
}

</style>