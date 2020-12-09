<template>
<div class="main">
<div class="heading">
    <h1>Heading</h1>  
</div>

<div class="random"><h2> {{ randomRestaurant.restaurantName }}</h2></div>

<img class="image" v-bind:src="randomRestaurant.imageLink"/>
 
  <div class="buttons">
      


      <div class="dislike">
         <button>Dislike</button>
      </div>

      <div class="skip">
         <button>Skip</button>
      </div>

      <div class="like">
         <button
         v-on:click.prevent="addRestaurantToFavorites(restaurant)"
         >Like</button>
      </div>

 </div>

<router-link/>
<router-link/>
  </div>

</template>

<script>
import ApplicationServices from '../services/ApplicationServices.js'
export default {
    name: 'matchmaking',
    data() {
        return{
        likes: [],
        dislikes: [],
        restaurants: [],
        randomRestaurant: {}
        }

    },
    created() {
        ApplicationServices.getAllRestaurants()
        .then(apiData => {
            this.restaurants = apiData.data;
            this.getRestaurantsById()
        })

    },
    methods:{
        getRestaurantsById(){
            let randomNum = Math.floor(Math.random() * this.restaurants.length - 1);
            ApplicationServices.getRestaurantById(randomNum).then(apiData => {
                this.randomRestaurant = apiData.data;
            })    
        },
        addRestaurantToFavorites(restaurant) {
            this.$store.commit('ADD_TO_FAVES', restaurant)
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