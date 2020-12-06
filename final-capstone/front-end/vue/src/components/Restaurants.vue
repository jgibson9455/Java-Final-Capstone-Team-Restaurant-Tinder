<template>
    <div>
        <div v-for="restaurant in restaurants"
        v-bind:key="restaurant.id">
        <h4>{{ restaurant.restaurantName }}</h4>
        <img class="images" v-bind:src="restaurant.imageLink"/>
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
    .images {
        height: 250px;
        width: 250px;
    }

</style>