<template>
<div class="match-main">

    <div class="match-heading">
        <h1>How's this sound?</h1>  
    </div><!--end of heading-->

    <div class="random">
        
        <img class="image" v-bind:src="randomRestaurant.imageLink"/>
        <h1 id="match-rest-name"> {{ randomRestaurant.restaurantName }}</h1>
        <h3 id="match-rest-city"> {{ randomRestaurant.city}}  </h3>
        <!-- <p id="match-rest-descript"> {{ randomRestaurant.restaurantDescrip}}  </p> -->

        <!--open modal
        v-bind:to="{name: 'details'}"-->
        <div class="modal-test">
            <button id="details-link" @click="showModal = true">
                Click for details
            </button>

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
                </div>
            </transition>
        </div>
        <!-- content for popup box
        <div id="modal">
            <div class="rest-content">
                <span class="close">&times;</span>
                <p> {{randomRestaurant.restaurantName}}</p>
            </div> end of content div
        </div>end of modal div -->

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
import ZomatoServices from '../services/ZomatoServices.js'
import YelpServices from '../services/YelpServices.js'
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
        showModal: false
        }
    },
    created() {
        ApplicationServices.getProfileByUsername(this.$store.state.user.username)
        .then((response) =>{
            this.profile = response.data;
            ZomatoServices.getCityInfo(this.profile.city)
            .then((response) =>{
                this.cityEntityType = response.data.location_suggestions[0].entity_type;
                this.cityEntityId = response.data.location_suggestions[0].entity_id;

                ZomatoServices.getAllRestaurantsByEntities(this.cityEntityId, this.cityEntityType)
                    .then((response) =>{
                        response.data.restaurants.forEach((place) =>{
                            this.restaurant.restaurantId = place.restaurant.id;
                            this.restaurant.restaurantName =  place.restaurant.name;
                            this.restaurant.restaurantDescrip = "Cuisines: " + place.restaurant.cuisines  + 
                            " Hours of Operation: " + place.restaurant.timings + " Average Rating: " +
                            place.restaurant.user_rating.aggregate_rating;
                            this.restaurant.zipCode =  place.restaurant.location.zipcode;
                            this.restaurant.city =  place.restaurant.location.locality;
                            this.restaurant.phoneNumber =  place.restaurant.phone_numbers;
                            this.restaurant.address =  place.restaurant.location.address;
                            this.restaurant.imageLink = 'https://cdn.dribbble.com/users/1012566/screenshots/4187820/topic-2.jpg'

                            YelpServices.getImage(this.restaurant.restaurantName, this.profile.city).then((response)=>{
                                this.restaurant.imageLink = response.businesses[0].image_url
                            });

                            this.restaurants.push(this.restaurant);
                            this.restaurant = {};
                        });
                       this.getRandomRestaurant();
                    });
                });
            });     
    },
    methods:{
        getRandomRestaurant(){
            let randomNum = Math.floor(Math.random() * (this.restaurants.length)) + 1;
            this.randomRestaurant = this.restaurants[randomNum];
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
    color: #FF5C5C;
    font-size: x-large;
    margin-top: -35px;
    margin-bottom: -25px;
    /* text-shadow: 2px 5px 5px rgba(59, 59, 57, 0.445),
                -2px 6px 7px  rgba(59, 59, 57, 0.445);  */
    /* color: blanchedalmond; */
}
 .image {
    align-self: center;
    max-height: 350px; /*275*/
    width: 352px; /*330*/
    /* margin: -5px; */
    padding-bottom: .5px;
    padding-left: 2px;
    padding-right: 2px;
    border-bottom-style: dotted;
    border-block-start-color: black;
}
 .modal-image {
    align-self: center;
    height: 300px;
    width: 360px;
    /* margin: -47px;
    padding-bottom: .5px;
    padding-left: 2px;
    padding-right: 2px; */
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
    height: 495px;
    width: 350px;
}
#match-rest-name{
    margin-top: 50px;
    margin-bottom: -15px;
}
#match-rest-city{
    margin-bottom: -5px;
}
/* #match-rest-descript{
    margin-bottom: 60px;
} */
#match-modal-rest-name{
    margin-top: 10px;
    margin-bottom: -25px;
}
 /* #match-modal-rest-type{
    margin-bottom: 60px;
}  */
 #match-modal-rest-phone{
    margin-bottom: 5px;
} 
 #match-modal-rest-address{
    margin-bottom: 20px;
} 
#details-link {
    color: blue;
    font-size: larger;
    margin-top: 30px;
    /**/
    cursor: pointer;
    display: inline-block;
    border-radius: 8px;
    transition: 0.4s ease-out;
}
#details-link:hover {
    color: rgb(108, 108, 238);
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
    color: blue;
    text-align: center;
}
.your-faves:hover, .all-restaurants:hover {
    color: rgb(108, 108, 238);
}


</style>