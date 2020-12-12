<!--this is the users profile page-->
<template>
  <div>
    
    <profile/>
    <favorite-restaurants/>

  </div>
</template>

<script>
import FavoriteRestaurants from '../components/FavoriteRestaurants.vue';
import Profile from '../components/Profile.vue'

export default {
  name: "home",
  components: {
    Profile,
    FavoriteRestaurants
  }
  
};
</script>
