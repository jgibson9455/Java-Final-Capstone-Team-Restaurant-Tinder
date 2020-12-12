<template>
    <div class="restaurant-details-main">
        <div
            v-for="restaurant in restaurants"
            v-bind:key="restaurant.id">
                <img class="detail-image" v-bind:src="restaurant.imageLink"/>
                <h1 id="detail-rest-name"> {{ restaurant.restaurantName }}</h1>
                <h3 id="detail-rest-city"> {{ restaurant.city}}  </h3>
                <p id="detail-rest-descript"> {{ restaurant.restaurantDescrip}}  </p>
        </div>
    </div>
</template>

<script>
import ApplicationServices from '../services/ApplicationServices.js'

export default {
    name: 'details',
    data() {
        return {
            restaurants: []
        }
    },
    created() {
        ApplicationServices.getAllRestaurants()
        .then(apiData => {
            this.restaurants = apiData.data;
            this.getRestaurantTypes();
        })
          
    },
    methods: {
 getRestaurantTypes() {
    for(let i = 0; i < this.restaurants.length; i++){
    ApplicationServices.getTypeById(this.restaurants[i].typeId)
        .then(apiData => {
            this.$set(this.restaurants[i], 'typeName', apiData.data.typeName)
        })

    }
 }
}
}
</script>

<style>
 .detail-image {
    align-self: center;
    height: 275px;
    width: 330px;
}
</style>