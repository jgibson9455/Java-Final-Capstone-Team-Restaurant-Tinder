<template>
  <div class="favorites">
      <restaurant-card
      v-for="restaurant in $store.state.favorites"
      v-bind:key="restaurant.restaurantId"
      v-bind:restaurant="restaurant"
      />
  </div>
</template>

<script>
import RestaurantCard from '../components/RestaurantCard.vue'
export default {
  components: {
    RestaurantCard
  },
  props: [
    'restaurant'
  ]
}

</script>

<style>

</style>