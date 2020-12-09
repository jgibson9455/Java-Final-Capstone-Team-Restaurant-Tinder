<template>
  <div class="favorites">
      <matchmaking
      v-bind:restaurant="restaurant"
      v-for="restaurant in $store.state.favorites"
      v-bind:key="restaurant.restaurantId"/>
  </div>
</template>

<script>

import Matchmaking from '../components/Matchmaking.vue'

export default {
  components: {
    Matchmaking
  }
}

</script>

<style>

</style>