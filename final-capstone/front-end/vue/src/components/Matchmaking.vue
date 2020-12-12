<template>
<div class="match-main">

    <div class="match-heading">
        <h1>How's this sound?</h1>  
    </div><!--end of heading-->

    <div class="random">
        
        <img class="image" v-bind:src="randomRestaurant.imageLink"/>
        <h1 id="match-rest-name"> {{ randomRestaurant.restaurantName }}</h1>
        <h3 id="match-rest-city"> {{ randomRestaurant.city}}  </h3>
        <p id="match-rest-descript"> {{ randomRestaurant.restaurantDescrip}}  </p>

        <!--open modal
        v-bind:to="{name: 'details'}"-->
        <div class="something">
            <button id="details-link" @click="showModal = true">
                Click for details
            </button>

            <transition name="fade" appear>
                <div class="modal-overlay" v-if="showModal" @click="showModal = false"></div>
            </transition>
            <transition name="slide" appear>
                <div class="modal" v-if="showModal" @click="showModal" = >
                    <img class="image" v-bind:src="randomRestaurant.imageLink"/>
                    <h1 id="match-rest-name"> {{ randomRestaurant.restaurantName }}</h1>
                    <h3 id="match-rest-city"> {{ randomRestaurant.city}}  </h3>
                    <p id="match-rest-descript"> {{ randomRestaurant.restaurantDescrip}}  </p>
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
        showModal: false
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
        },
        // modalLogic() {
        //     //get modal
        //     let modal = document.getElementById("modal");
        //     //get button that opens modal
        //     let link = document.getElementById("details-link");
        //     //get span element that closes modal
        //     let span = document.getElementsByClassName("close")[0];
        //     //when user clicks link, open modal
        //     link.onclick = function() {
        //         modal.style.display = "block";
        //     }
        //     //when user clicks on span (x), close the modal
        //     span.onclick = function() {
        //         modal.style.display = "none";
        //     }
        //     //when user clicks outside modal, close it
        //     window.onclick = function(event) {
        //         if(event.target == modal) {
        //             modal.style.display = "none";
        //         }
        //     }
        // }
    }      
}
</script>

<style scoped>
.something {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
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
    border-radius: ;
}
.fade-enter-active,
.fade-leave-active {
    transition: opacity 1s ease-in;
}
.fade-enter,
.fade-leave-to {
    opacity: 0.5s;
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
    height: 275px; /*275*/
    width: 352px; /*330*/
    margin: -47px;
    padding-bottom: .5px;
    padding-left: 2px;
    padding-right: 2px;
    border-bottom-style: dotted;
    border-block-start-color: black;
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
#details-link {
    color: blue;
    font-size: larger;
    margin-top: auto;
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