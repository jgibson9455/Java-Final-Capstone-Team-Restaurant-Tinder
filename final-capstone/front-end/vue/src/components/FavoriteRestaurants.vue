<template>
  <div class="favorites">
      <restaurant-card
      v-for="restaurant in fav"
      v-bind:key="restaurant.restaurantId"
      v-bind:restaurant="restaurant"
      />
  </div>
</template>

<script>
import RestaurantCard from '../components/RestaurantCard.vue'
import appService from '../services/ApplicationServices'
export default {
  data() {
    return {
      fav: [],
      matching: []
    }
  },
  components: {
    RestaurantCard
  },
  created() {
    appService.getMatchingResultsByUserName(this.$store.state.user.username).then(response => {
      this.matching = response.data;
    }).then(res => {
      this.matching.forEach((match) => {
      appService.getRestaurantById(match.restaurantId).then(response => {
        this.fav.push(response.data);
      })
      })
      console.log(res);
    })
  },
  props: [
    'restaurant'
  ]
}

</script>

<style>

</style>