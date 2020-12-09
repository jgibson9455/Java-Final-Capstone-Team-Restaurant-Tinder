<template>
    <div class="restaurant-page">
            <h1 class="page-head"><u>Restaurants</u></h1>
          <div class="restaurant-items">
                
            <div class="restaurant-head"
                v-for="restaurant in restaurants"
                v-bind:key="restaurant.id">
                <h2>{{ restaurant.restaurantName }}</h2>
                <h2 class="rest-type">Restaurant Type</h2>
            <!-- //</div>

            //<div class="rest-image-link"> -->
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
            
        })
    //     ,
    // theApplicationService.getTypeById(restaurants.typeID)
    //     .then(apiData => {
    //         this.restaurantTypes = apiData.data;
            
    //     })
        
}
}
</script>

<style>
.page-head {
    color: blanchedalmond;
    font-size: 5em;
    text-align: center;
    margin-top: 5px;
    margin-bottom: -15px;
    /* text-shadow: 2px 5px 5px rgba(59, 59, 57, 0.445),
                -5px 6px 7px  rgba(59, 59, 57, 0.445); 
                
      text-shadow: black 0px 0px 1px,   black 0px 0px 1px,   black 0px 0px 1px,
             black 0px 0px 1px,   black 0px 0px 1px,   black 0px 0px 1px*/

    text-shadow: 2px 5px 5px rgba(59, 59, 57, 0.445),
                -2px 6px 7px  rgba(59, 59, 57, 0.445); 
    -webkit-font-smoothing: antialiased;
    }
u {
    text-decoration: none;
    padding-bottom: 0px;
    border-bottom: 5px solid blanchedalmond;
    -webkit-font-smoothing: antialiased;
}
.restaurant-page {

    background-image: linear-gradient(to bottom left,  #FF655B, #FD297B);;
    padding: 15px;
    background-clip: padding-box;
    padding-bottom: 40em;
}
.restaurant-items {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    /* flex: 1;
    overflow: auto; */

}
.restaurant-head {
    text-align: center;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    font-size: 20px;
    text-shadow: blanchedalmond 0px 0px .75px,   blanchedalmond 0px 0px .75px,   blanchedalmond 0px 0px .75px,
             blanchedalmond 0px 0px .75px,   blanchedalmond 0px 0px .75px,   blanchedalmond 0px 0px .75px;
    color: rgb(8, 8, 8);
    }
.rest-type {
    margin-top: -22px;
    font-size: 22px;
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