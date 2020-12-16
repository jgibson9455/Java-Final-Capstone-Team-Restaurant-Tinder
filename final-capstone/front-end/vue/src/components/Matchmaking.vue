<template>
<div class="match-main">
  <link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Bad+Script&display=swap" rel="stylesheet">
    <div class="match-heading">
        <h1>How's this sound?</h1>  
    </div><!--end of heading-->

    <div class="loading" v-if="isLoading">
       <img src='@/img/celerywalk.gif'/>
    </div>

    <div class="random">
        
            <div class="match-elements">
                <img class="image" v-bind:src="randomRestaurant.imageLink"/>
                    <div class="match-info">
                        <h1 id="match-rest-name"> {{ randomRestaurant.restaurantName }}</h1>
                        <h3 id="match-rest-city"> {{ randomRestaurant.city}}  </h3>
                    
                    <div class="flex-end">
                        <button id="details-link" @click="showModal = true">
                            Click for details
                        </button>
                    </div><!--flex end-->

                    </div><!--match info-->
                <!-- <p id="match-rest-descript"> {{ randomRestaurant.restaurantDescrip}}  </p> -->
            </div><!--match elements-->

        <div class="modal-test">


            <transition name="fade" appear>
                <div class="modal-overlay" v-if="showModal" @click="showModal = false"></div>
            </transition>
            <transition name="slide" appear>
                <div class="modal" v-if="showModal">
                    <img class="modal-image" v-bind:src="randomRestaurant.imageLink"/>
                    <h1 id="match-modal-rest-name"> {{ randomRestaurant.restaurantName }}</h1>
                    <h1 id="match-modal-rest-type"> {{ randomRestaurant.typeName }}</h1>
                    <h2 id="match-modal-rest-phone"> {{ randomRestaurant.phoneNumber}} </h2>
                    <h3 id="match-modal-rest-address"> {{ randomRestaurant.address}} {{ randomRestaurant.city}} {{ randomRestaurant.zipCode}}  </h3>
                    <p id="match-modal-rest-descript"> {{ randomRestaurant.restaurantDescrip}}  </p>
                    <button class="button" @click="showModal = false">
                        Close
                    </button>
                </div> <!--modal-->
            </transition>
        </div><!--random-->

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
          v-bind:to="{name: 'home'}"><strong>View Your Favorites</strong></router-link>
          <router-link class="all-restaurants" v-bind:to="{name: 'restaurants'}"><strong>View All Restaurants</strong></router-link>
        </div><!--end of match-links div-->
  </div> <!--end of matches main div-->

</template>

<script>
import ApplicationServices from '../services/ApplicationServices.js'
import ZomatoServices from '../services/ZomatoServices.js'

export default {
    name: 'match-making',
    data() {
        return{
        isLoading: true,
        restaurants: [],
        randomRestaurant: {},
        matchingResult: {
            userName: this.$store.state.user.username,
            restaurantId: 0,
            preferenceId: 0
        },
        restaurant: {},
        profile:{
            userName: "",
            firstName: "",
            lastName: "",
            email: "",
            city: ""
        },
        cityEntityType: "",
        cityEntityId: "",
        showModal: false,
        alreadyInFavorites: [],
        allCuisines: [],
        dislikedPreferences: []
        }
    },
    created() {
        ApplicationServices.getProfileByUsername(this.$store.state.user.username)
        .then((response) =>{
            this.profile = response.data;
            ZomatoServices.getCityInfo(this.profile.city)
            .then((response) =>{
                console.log(this.profile.greaterCity);
                response.data.location_suggestions.forEach((location)=>{
                    if(location.city_name == this.profile.greaterCity){
                        this.cityEntityType = location.entity_type;
                        this.cityEntityId = location.entity_id
                    }
                })
                ZomatoServices.getAllRestaurantsByEntities(this.cityEntityId, this.cityEntityType)
                    .then((response) =>{
                        response.data.restaurants.forEach((place) =>{
                            this.restaurant.restaurantId = place.restaurant.id;
                            this.restaurant.restaurantName =  place.restaurant.name;
                            this.restaurant.restaurantDescrip = "Cuisines: " + place.restaurant.cuisines  + 
                            " Hours of Operation: " + place.restaurant.timings + " Average Rating: " +
                            place.restaurant.user_rating.aggregate_rating;
                            this.restaurant.cuisines = place.restaurant.cuisines;
                            this.restaurant.zipCode =  place.restaurant.location.zipcode;
                            this.restaurant.city =  place.restaurant.location.locality;
                            this.restaurant.phoneNumber =  place.restaurant.phone_numbers;
                            this.restaurant.address =  place.restaurant.location.address;
                            this.restaurant.imageLink = 'https://cdn.dribbble.com/users/1012566/screenshots/4187820/topic-2.jpg'

                            this.restaurants.push(this.restaurant);
                            this.restaurant = {};
                        });

                    ApplicationServices.getMatchingResultsByUserName(this.$store.state.user.username)
                    .then((response) =>{
                        this.alreadyInFavorites = response.data;
                    });

                    ApplicationServices.getPreferencesByUsername(this.$store.state.user.username)
                    .then((response)=>{
                        this.dislikedPreferences = response.data;
                    });

                    this.getRandomRestaurant();
                    });
                });
                 this.isLoading = false;
            });     
    },
    methods:{
        getRandomRestaurant(){
            let isValid;
            do{
                isValid = true;
                let randomNum = Math.floor(Math.random() * (this.restaurants.length));
            
                for(let i = 0; i < this.alreadyInFavorites.length; i++){
                    if(this.alreadyInFavorites[i].restaurantId == this.restaurants[randomNum].restaurantId){
                        isValid = false
                    }
                }
                let splitCuisiens  = this.restaurants[randomNum].cuisines.split(', ');
                console.log(splitCuisiens);
                console.log(splitCuisiens.length)

                let matchedDislikes = 0;
                for(let i = 0; i < this.dislikedPreferences.length; i++){
                    console.log(this.dislikedPreferences[i].typeName);
                    if(this.dislikedPreferences[i].preferenceId == 2 && 
                    splitCuisiens.indexOf(this.dislikedPreferences[i].typeName) > -1){
                        console.log("included!");
                        matchedDislikes++;
                        console.log(matchedDislikes);
                    }
                }

                 if(matchedDislikes >= splitCuisiens.length){
                    isValid = false;
                    console.log('Taken out due to 0 matches')
                }

                if(isValid){
                    this.randomRestaurant = this.restaurants[randomNum];
                }
            }while(!isValid);
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
            if(this.randomRestaurant){
                let randomId = this.randomRestaurant.restaurantId;
                for(let i = 0; i < this.restaurants.length; i++){
                    if(this.restaurants[i].restaurantId === randomId){
                        this.restaurants.splice(i, 1);
                        break;
                    }
                }
            }
        },
//          getRestaurantTypes() {
//     for(let i = 0; i < this.restaurants.length; i++){
//     ApplicationServices.getTypeById(this.restaurants[i].typeId)
//         .then(apiData => {
//             this.$set(this.restaurants[i], 'typeName', apiData.data.typeName)
//         })

//     }
//  }

    }      
}
</script>

<style scoped>
.loading {
    display: flex;
    justify-content: center;
}
.modal-test {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}
.modal-overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 98;
    background-color: rgba(0, 0, 0, 0.3);
}
.modal {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 99;

    width: 100%;
    max-width: 400px;
    background-color: #FFF;
    border-radius: 16px;

    padding: 25px;
}
.modal > h1 {
    color: #222;
    font-size: 32px;
    font-weight: 900;
    margin-bottom: 15px;
}
.modal > p {
    color: #666;
    font-size: 18px;
    font-weight: 400;
    margin-bottom: 15px;   
}
.fade-enter-active,
.fade-leave-active {
    transition: opacity .9s;
}
.fade-enter,
.fade-leave-to {
    opacity: 0;
}
.slide-enter-active,
.slide-leave-active {
    transition: transform .5s;
}
.slide-enter,
.slide-leave-to {
    transform: translateY(-50%) translateX(100vw);
}
.match-main {
    display: flex;
    flex-direction: column;
    align-items: center;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}
.match-heading {
    font-family: 'Bad Script', cursive;
    color: #FF5864;
    font-size: x-large;
    margin-top: -35px;
    margin-bottom: -35px;
}
 .image {
    align-self: center;
    width: 350px;
    border-radius: 5px;
    padding-left: 3px;
    padding-right: 3px;
    border-bottom-style: dotted;
    border-block-start-color: black;
}
.match-elements{
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
}
#match-rest-city{
    margin-top: -15px;
    margin-bottom: 5px;
} 
 .modal-image {
    align-self: center;
    height: 300px;
    width: 360px;
}
.random {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    text-align: center;
    background-color: blanchedalmond;
    border-style: solid;
    border-color: black;
    border-width: 3px;
    border-radius: 5px; 
    word-wrap: break-word;
    height: 495px;
    width: 350px;
}
#match-modal-rest-name{
    margin-top: 10px;
    margin-bottom: -25px;
}
 #match-modal-rest-phone{
    margin-bottom: 5px;
} 
 #match-modal-rest-address{
    margin-bottom: 20px;
} 
#details-link {
    color: #FE3C72;
    font-size: larger;
    cursor: pointer;
    display: inline-block;
    border-radius: 8px;
    transition: 0.4s ease-out;
    background-color: white;
}
#details-link:hover {
    color: black;
    box-shadow: 2px 2px rgba(0, 0, 0, 0.6);
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
    margin-top: -20px;
    margin-bottom: 5px;
}
.all-restaurants {
    margin-top: -5px;
}
.your-faves, .all-restaurants {
    color: #FE3C72;
    text-align: center;
    text-decoration: none;
    font-size: larger;
    padding-top: 5px;
}
.your-faves:hover, .all-restaurants:hover {
    color: black;
    text-decoration: underline;
}


</style>