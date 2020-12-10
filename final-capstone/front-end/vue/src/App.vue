<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'startup' }">Home</router-link>&nbsp;|&nbsp;    
      <router-link v-bind:to="{ name: 'home' }">Profile</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      
    </div>
    <router-view />
  </div>
</template>

<script>
export default {

  created(){
    window.addEventListener('beforeunload', ()=>{
        this.$store.commit("LOGOUT");
    }, false)
  }
}
</script>

<style scoped>
#nav > a {
  color: #FD297B;
  text-decoration: none;
  font-weight: 600;
}

#nav > a:hover {
  color: #FF655B;
  text-decoration: underline;
}

</style>
