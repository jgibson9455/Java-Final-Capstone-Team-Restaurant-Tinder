<template>
  <div id="app">
    <div id="nav" v-if="$store.state.token != ''">
      

      <router-link v-bind:to="{ name: 'match-making' }">
        <img class="nav-logo" src='@/img/logo-color.png'/>
      </router-link>&nbsp;  

      <router-link v-bind:to="{ name: 'home' }">
        <img class="nav-profile" src='@/img/profile.png'/>
      </router-link>&nbsp;  

      <router-link v-bind:to="{ name: 'logout' }">
        <img class="nav-logout" src='@/img/logout.png'/>
      </router-link>
      
    </div> <!--if nav closing div-->
       <router-view />

      
       <!-- <footer class="footer"><img src="@/img/logo-black.png"></footer> -->
       <!-- <h5>C 2020</h5> -->
  
</div> <!--id app closing div-->
</template>

<script>
export default {

  // created(){
  //   window.addEventListener('beforeunload', ()=>{
  //       this.$store.commit("LOGOUT");
  //   }, false)
  // }

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

.nav-logo, .nav-profile, .nav-logout {
  width: 30px;
  border-radius: 50%;
}

.nav-logo:hover, .nav-profile:hover, .nav-logout:hover {
  width: 40px;
  margin-top: -10px;
  margin-bottom: -10px;
  transition: 0.2s ease;
}

@media screen and (max-width: 768px) {
  #nav {
    margin-bottom: 25px;
  }
}

@media screen and (max-width: 500px) {
  #nav {
    text-align: center;
  }
}
/*img {
    width: 2em;
}
/* .footer{
  position: fixed;  
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 175px;
  bottom: 0; 
  width: 100%; 
  height: 3em; 
  background:#FF5C5C; 
  opacity: 65%;
} */

</style>
