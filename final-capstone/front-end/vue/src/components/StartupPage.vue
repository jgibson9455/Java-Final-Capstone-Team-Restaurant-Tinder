<template>
  <div>
      <h1>Restaurant Tindr</h1>
      <div class="links"> 
          <router-link v-bind:to="{name: 'login'}">Login</router-link>
          <router-link v-bind:to="{ name: 'register' }">Register</router-link>
      </div>
  </div>
</template>

<script>
export default {
    name: 'startup-page'

}
</script>

<style>

</style>