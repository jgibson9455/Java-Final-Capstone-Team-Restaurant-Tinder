<template>
    <div class="restaurant-page">
            <h1 class="page-head"><u id="rest-line">Restaurants</u></h1>
            <!-- <div class="loading" v-if="!isLoading">
                   <img src='@/img/celerywalk.gif'/>
            </div> -->
        <div class="restaurant-items">
                
            <div class="restaurant-head">
            <restaurant-card
                    v-for="restaurant in restaurants"
                    v-bind:key="restaurant.id"
                    v-bind:restaurant="restaurant">
            </restaurant-card>
            </div>
              <!-- <h2 id="all-rest-name">{{ restaurant.restaurantName }}</h2>
                <p id="all-rest-type">{{ restaurant.typeName }}</p>
                

                <img class="rest-images" v-bind:src="restaurant.imageLink"/> -->

        
        </div>    
    </div>  
</template>

<script>

import RestaurantCard from '../components/RestaurantCard.vue'
import theApplicationService from '../services/ApplicationServices'

export default {
    name: 'restaurants',
    data(){
        return {
            restaurants: [],
            isLoading: true,
            showModal: false   
        }
    },
    components: {
        RestaurantCard
    },
    props: [
        'restaurant'
    ],
created() {
    theApplicationService.getAllRestaurants()
        .then(apiData => {
            this.restaurants = apiData.data;
            this.getRestaurantTypes();
        })
},

methods: {
 getRestaurantTypes() {
    for(let i = 0; i < this.restaurants.length; i++){
    theApplicationService.getTypeById(this.restaurants[i].typeId)
        .then(apiData => {
            this.$set(this.restaurants[i], 'typeName', apiData.data.typeName) //this instead of below this.$set(object, 'key', value)
//            this.restaurants[i].typeName = apiData.data.typeName;

        })

    }
 }
}       

}
</script>

<style scoped>

.page-head {
    color: #FF5864 ;
    font-size: 3.5em;
    text-align: center;
    padding-top: 25px;
    padding-bottom: 30px;
    margin-top: 5px;
    margin-bottom: -15px;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    /* text-shadow: 2px 5px 5px rgba(59, 59, 57, 0.445),
                -5px 6px 7px  rgba(59, 59, 57, 0.445); 
                
      text-shadow: black 0px 0px 1px,   black 0px 0px 1px,   black 0px 0px 1px,
             black 0px 0px 1px,   black 0px 0px 1px,   black 0px 0px 1px*/

   /*text-shadow: 2px 5px 5px rgba(8, 8, 8, 0.75),
                -5px 6px 7px rgba(8, 8, 8, 0.75);*/

    /* -webkit-font-smoothing: antialiased; */
    }
/* #pref-line {
    text-decoration: none;
    border-bottom: 5px solid #FF5864;
} */
h2 {
    margin-bottom: 5px;
}
h4 {
    margin-top: 5px;
}
.restaurant-page {
    /*background-image: linear-gradient(to bottom left,  #FF655B, #FD297B);*/
    padding: 15px;
    margin-top: 15px;
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
    display: flex;
    flex-wrap: wrap;
    text-align: center;
    justify-content: space-between;
    font-size: 22px; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    font-size: 20px;
    /* text-shadow: blanchedalmond 0px 0px .75px,   blanchedalmond 0px 0px .75px,   blanchedalmond 0px 0px .75px,
             blanchedalmond 1px 0px .75px,   blanchedalmond 0px 0px .75px,   blanchedalmond 0px 0px .75px; */
    color: rgb(8, 8, 8);
    }
#all-rest-name{

}
#all-rest-type{
    margin-top: -8px;

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
.image {
    display: flex;
    justify-content: center;
    padding: 13%;

    } 
/* img {
    width: 2%;
} */

</style>