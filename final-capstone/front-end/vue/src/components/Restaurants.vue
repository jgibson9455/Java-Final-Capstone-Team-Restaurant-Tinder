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
            
            <!--<div class="modal-main">
             <button id="details-link" @click="showModal = true">
                Click for details
             </button>
            <transition name="fade" appear>
                <div class="modal-overlay" v-if="showModal" @click="showModal = false"></div>
            </transition>
            <transition name="slide" appear>
                <div class="modal" v-if="showModal">
                    <img class="modal-image" v-bind:src="restaurant.imageLink"/>
                    <h1 id="match-modal-rest-name"> {{ restaurant.restaurantName }}</h1>
                    <h1 id="match-modal-rest-type"> {{ restaurant.typeName }}</h1>
                    <h2 id="match-modal-rest-phone"> {{ restaurant.phoneNumber}} </h2>
                    <h3 id="match-modal-rest-address"> {{ restaurant.address}} {{ restaurant.city}} {{ restaurant.zipCode}}  </h3>
                    <p id="match-modal-rest-descript"> {{ restaurant.restaurantDescrip}}  </p>
                    <button class="close-button" @click="showModal = false">
                        Close
                    </button>
                </div>
            </transition>

            </div>  -->

        
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
/* .modal-main {
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
    padding: 45px;
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
 .modal-image {
    align-self: center;
    height: 300px;
    width: 360px;
    margin: -45px;
    // padding-top: 10px;
    padding-bottom: 5px; //
    // padding-left: 20px;
    padding-right: 20px; //
}
#match-modal-rest-name{
    margin-top: 10px;
    margin-bottom: -25px;
}
 // #match-modal-rest-type{
    margin-bottom: 60px;
}  //
 #match-modal-rest-phone{
    margin-bottom: 5px;
} 
 #match-modal-rest-address{
    margin-bottom: 20px;
} */
/*.details-link {
    color: blue;
    font-size: larger;
    margin-top: auto;
    
    cursor: pointer;
    display: inline-block;
    border-radius: 8px;
    transition: 0.4s ease-out;
}
.details-link:hover {
    color: rgb(108, 108, 238);
    box-shadow: 2px 2px rgba(0, 0, 0, 0.6);
}*/
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