<template>
<body>
    
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Bad+Script&display=swap" rel="stylesheet">
    
    <div class="restaurant-page">

                <div class="loading" v-if="isLoading">
                   <img src='@/assets/veggies.gif'/>
                </div><!--end of loading div-->

            <h1 class="page-head"><u id="rest-line">Restaurants</u></h1>


            <div class="restaurant-items">
                
                <div class="restaurant-head">
                    <restaurant-card class="card"
                        v-for="restaurant in restaurants"
                        v-bind:key="restaurant.id"
                        v-bind:restaurant="restaurant">
                    </restaurant-card>
                </div><!--end of restaurant head div-->

                <!-- <h2 id="all-rest-name">{{ restaurant.restaurantName }}</h2>
                <p id="all-rest-type">{{ restaurant.typeName }}</p>

                <img class="rest-images" v-bind:src="restaurant.imageLink"/> -->
            </div> <!--end of restaurant-items div-->

    </div>  <!--end of restaurant page div-->

</body>
</template>

<script>

import RestaurantCard from '../components/RestaurantCard.vue'
import theApplicationService from '../services/ApplicationServices'
import ZomatoServices from '../services/ZomatoServices.js';

export default {
    name: 'restaurants',
    data(){
        return {
            restaurants: [],
            localRestaurant: {},
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
    theApplicationService.getProfileByUsername(this.$store.state.user.username)
        .then((response) =>{
            this.profile = response.data;
            ZomatoServices.getCityInfo(this.profile.city)
            .then((response) =>{
                response.data.location_suggestions.forEach((location)=>{
                    if(location.city_name == this.profile.greaterCity){
                        this.cityEntityType = location.entity_type;
                        this.cityEntityId = location.entity_id
                    }
                })

                ZomatoServices.getAllRestaurantsByEntities(this.cityEntityId, this.cityEntityType)
                    .then((response) =>{
                        response.data.restaurants.forEach((place) =>{
                            this.localRestaurant.restaurantId = place.restaurant.id;
                            this.localRestaurant.restaurantName =  place.restaurant.name;
                            // this.localRestaurant.restaurantDescrip = "Cuisines: " + place.restaurant.cuisines  + 
                            // " Hours of Operation: " + place.restaurant.timings + " Average Rating: " +
                            // place.restaurant.user_rating.aggregate_rating;
                            this.localRestaurant.restaurantCuisine = "Cuisines: " + place.restaurant.cuisines;
                            this.localRestaurant.restaurantHours = "Hours of Operation: " + place.restaurant.timings;
                            this.localRestaurant.restaurantRating = "Average Rating: " + place.restaurant.user_rating.aggregate_rating;
                            this.localRestaurant.cuisines = place.restaurant.cuisines;
                            this.localRestaurant.zipCode =  place.restaurant.location.zipcode;
                            this.localRestaurant.city =  place.restaurant.location.locality;
                            this.localRestaurant.phoneNumber =  place.restaurant.phone_numbers;
                            this.localRestaurant.address =  place.restaurant.location.address;
                            let cuisinesSplit = place.restaurant.cuisines.split(", ");
                            if(cuisinesSplit.length > 0){
                                this.localRestaurant.imageLink = `${cuisinesSplit[0]}.png`;
                            }else{
                                this.localRestaurant.imageLink = 'https://cdn.dribbble.com/users/1012566/screenshots/4187820/topic-2.jpg'
                             }

                            this.restaurants.push(this.localRestaurant);
                            this.localRestaurant = {};
                        });
                        this.isLoading = false;
                    });
            });
        });
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
.loading > img {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 30%;
}
.page-head {
    color: #FF5864 ;
    font-size: 3.5em;
    text-align: center;
    padding-top: 25px;
    padding-bottom: 30px;
    margin-top: 5px;
    margin-bottom: -15px;
    font-family: 'Bad Script', cursive;
  }
.restaurant-items {
    display: flex;
    flex-direction: column;
    justify-content: center;
    flex-wrap: wrap;
}
.restaurant-head {
    display: flex;
    flex-wrap: wrap;
    align-items: flex-end;
    text-align: center;
    justify-content: space-around;
    font-size: 22px; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    font-size: 20px;
    color: rgb(8, 8, 8);
    }
h2 {
    margin-bottom: 5px;
}
h4 {
    margin-top: 5px;
}
.restaurant-page {
    padding: 15px;
    margin-top: 15px;
    background-clip: padding-box;
    padding-bottom: 15em;
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
    padding: 13%;
    } 
</style>