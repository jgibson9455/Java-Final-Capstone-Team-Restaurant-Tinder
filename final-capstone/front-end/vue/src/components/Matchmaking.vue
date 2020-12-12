<template>
<div class="match-main">

    <div class="match-heading">
        <h1>How's this sound?</h1>  
    </div><!--end of heading-->

    <div class="random">
        
        <img class="image" v-bind:src="randomRestaurant.imageLink"/>
        <h2 id="rest-name"> {{ randomRestaurant.restaurantName }}</h2>
        <p id="rest-descript"> {{ randomRestaurant.Description}} The description of the restaurant will go here. </p> <!--???-->
        <router-link id="details-link" v-bind:to="{name: favorites}">Click for details</router-link>
    </div><!--end of random div-->


  <div class="buttons">
      
      <div id="dislike">
         <a href
         v-on:click.prevent="addToDislike(randomRestaurant)">
         <img id="dislike-pic" src="../img/Dislike.png">
         </a>
        <h5 class="button-label">Nope</h5>
      </div>


      <div id="skip">
         <a href
         v-on:click.prevent="getRandomRestaurant()">
         <img id="skip-pic" src="../img/Skip.png">
         </a>
         <h5 class="button-label">Next</h5>
      </div>

      <div id="like">
         <a href
         v-on:click.prevent="addRestaurantToFavorites(randomRestaurant)">
         <img id="like-pic" src="../img/Like.png">
         </a>
         <h5 class="button-label">Like</h5>
      </div>
  </div> <!--end of buttons div-->
   

        <div class="match-links">
          <router-link class="your-faves"
          v-bind:to="{name: 'home'}">View Your Favorites</router-link>
          <router-link class="all-restaurants" v-bind:to="{name: 'restaurants'}">View All Restaurants</router-link>
        </div><!--end of match-links div-->
  </div> <!--end of matches main div-->

</template>

<script>
import ApplicationServices from '../services/ApplicationServices.js'
export default {
    name: 'match-making',
    data() {
        return{
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
           let randomNum = Math.floor(Math.random() * (this.restaurants.length)) + 1;
           for(let i = 0; i < this.restaurants.length; i++){
               if(randomNum === this.restaurants[i].restaurantId) {
                ApplicationServices.getRestaurantById(this.restaurants[i].restaurantId).then(apiData => {
                this.randomRestaurant = apiData.data;
            })
               }
           }
        },
        addRestaurantToFavorites(restaurant) {
            this.matchingResult.preferenceId = 1;
            this.matchingResult.restaurantId = restaurant.restaurantId;
            ApplicationServices.saveMatchingResult(this.matchingResult);
            this.removeFromRestaurants();
            this.getRandomRestaurant();
        },
        addToDislike(restaurant) {
            this.$store.commit('ADD_TO_DISLIKE', restaurant);
            console.log(this.$store.state.dislikes);
            this.removeFromRestaurants();
            this.getRandomRestaurant();  
        },
        removeFromRestaurants() {
            let randomId = this.randomRestaurant.restaurantId;
            for (let i = 0; i < this.restaurants.length; i++) {
                if(this.restaurants[i].restaurantId === randomId) {
                 // let index =   this.restaurants.indexOf(this.restaurants[i]);
                  this.restaurants.splice(i, 1);
                  break;
                }
            }
        }
    }      
}
</script>

<style scoped>
.match-main {
    display: flex;
    flex-direction: column;
    align-items: center;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}
.match-heading {
    color: #FF5C5C;
    font-size: x-large;
    margin-top: -35px;
    margin-bottom: -20px;
    /* text-shadow: 2px 5px 5px rgba(59, 59, 57, 0.445),
                -2px 6px 7px  rgba(59, 59, 57, 0.445);  */
    /* color: blanchedalmond; */
}
 .image {
    align-self: center;
    height: 275px; /*275*/
    width: 352px; /*330*/
    margin: -47px;
}
.random {
    display: flex;
    flex-direction: column;
    /* justify-content: center;
    align-items: center; */
    text-align: center;
    background-color: blanchedalmond;
    border-style: solid;
    border-color: black;
    border-width: 3px;
    border-radius: 5px; 
    word-wrap: break-word;
    height: 475px;
    width: 350px;
}
#rest-name{
    margin-bottom: -3px;
}
#rest-descript{
    margin-bottom: 40px;
}
#details-link {
    color: blue;
    font-size: larger;
}
#details-link:hover {
    color: rgb(108, 108, 238);
}
.buttons {
    display: flex;
    justify-content: space-around;
    color: gray;
    margin-top: 10px;
    width: 435px;
} 
.button-label {
  text-align: center;
  margin-top: -5px;
}
#dislike-pic{
  height: 55px;
  width: 55px;
}
#skip-pic{
  height: 55px;
  width: 55px;
}
#like-pic{
    height: 55px;
    width: 55px;
}
.match-links {
    display: flex;
    flex-direction: column;

}
.your-faves{
    margin-top: -5px;
    margin-bottom: 5px;
}
.your-faves, .all-restaurants {
    color: blue;
    text-align: center;
}
.your-faves:hover, .all-restaurants:hover {
    color: rgb(108, 108, 238);
}


</style>