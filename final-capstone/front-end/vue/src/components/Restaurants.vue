<template>
    <div class="restaurant-page">
            
          <div class="restaurant-items">

            <div class="restaurant-head"
                v-for="restaurant in restaurants"
                v-bind:key="restaurant.id">
                <h1>{{ restaurant.restaurantName }}</h1>
                <h4 class="rest-type">restaurant type</h4>
            <!-- </div>

            <div class="rest-image-link">     -->
                <img class="rest-images" v-bind:src="restaurant.imageLink"/>
            </div>

          </div>
    </div>
</template>

<script>
import theApplicationService from '../services/ApplicationServices'

export default {
    name: 'restaurants',
    data(){
        return {
            restaurants: [],
            restaurantTypes: []
        }
    },
created() {
    theApplicationService.getAllRestaurants()
        .then(apiData => {
            this.restaurants = apiData.data;
            
        }),

    theApplicationService.getAllRestaurantTypes()
        .then(apiData => {
            this.restaurantTypes = apiData.data;
            
        })
        
}
}
</script>

<style>

.restaurant-page {
    background-image: linear-gradient(to bottom left,  #FF655B, #FD297B);;
    height: 80em
}
.restaurant-items {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;

}
.restaurant-head {
    text-align: center;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    font-size: em;
    text-shadow: 2px 5px 5px rgba(8, 8, 8, 0.75),
                -5px 6px 7px rgba(8, 8, 8, 0.75);
    color: blanchedalmond;
    }
.rest-type {
    margin-top: -22px;
}
.rest-images {
    height: 250px;
    width: 250px;
    border-style: solid;
    border-color: black;
    border-width: 2px;
    border-radius: 5px; 
    } 

</style>