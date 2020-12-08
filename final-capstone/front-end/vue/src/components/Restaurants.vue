<template>
    <div class="restaurant-page">
            
          <div class="restaurant-items">

            <div class="restaurant-head"
                v-for="restaurant in restaurants"
                v-bind:key="restaurant.id">
                <h4>{{ restaurant.restaurantName }}</h4>
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
            restaurants: []
        }
    },
created() {
    theApplicationService.getAllRestaurants()
        .then(apiData => {
            this.restaurants = apiData.data;
        })
}

}
</script>

<style>

.restaurant-page {
    background-image: linear-gradient(to bottom left,  #FF655B, #FD297B);;

}
.restaurant-items {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;

}

.restaurant-head {
    text-align: center;
}
.rest-images {
    height: 250px;
    width: 250px;
    /* border-style: dotted;
    border: black;
    border-radius: 5px;  */
    } 

</style>