<template>
  <div class="contatiner">

      <body class="startup-body">
            <div class="start-banner">
                <img id="banner-logo" src='../img/homebanner.jpg'/>
            </div>
    <h1 class="start-head">Restaurant Tinder</h1>
       
        <div class="link-div">
    
            <div class="link-box"> 
             <button id="loginb">
                <router-link v-bind:to="{name: 'login'}">Login</router-link></button>
            <button id="registerb">
                <router-link v-bind:to="{ name: 'register' }">Register</router-link></button>
       
            <div class="start-logo">
                <img id="starting-logo" src='../img/logo-color.png'/>
            </div>
       
        </div>



    </div>
    </body>

  </div>
</template>

<script>
export default {
    name: 'startup-page'

}
</script>

<style>

 /* .container {
 
} */

.startup-body {
    background-image: linear-gradient(to bottom left,  #FF655B, #FD297B);
    height: 50em;
}

.link-div {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 300px;
}

.link-box {
    display: flex;
    flex-direction: column;
    justify-content: center; /*centers the items within this box*/
    align-items: center;
    background-color: blanchedalmond;
    height: 250px;
    width: 250px;
    border-radius: 5%;
}

.start-logo {
  text-align: center;
  padding-top: 30px;
}

#starting-logo {
  height: 75;
  width: 75px;
  border-radius: 50%;

}
 .start-banner {
  text-align: center;
  padding-top: 30px;
} 
#banner-logo {
  height: 200px;
  width: 500px;
}
 #loginb {
     background-color:#ffbfb4;
     padding-top: 5px;
     padding-bottom: 5px;
     padding-right: 18.5px;
     padding-left: 18.5px;
     border-style: outset;
     border-width: 4px;
     border-color:rgb(100, 38, 38);
     margin-bottom: 2px
}

#registerb {
     background-color:#ffbfb4;
     padding-top: 5px;
     padding-bottom: 5px;
     padding-right: 7px;
     padding-left: 7px;
     border-style: outset;
     border-width: 4px;
     border-color:rgb(100, 38, 38);
     margin-top: 2px
}

 .start-head {
     font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
     text-align: center;
    text-shadow: 2px 5px 5px rgb(59, 59, 57),
                -5px 6px 7px  rgb(59, 59, 57); 
    color: blanchedalmond;
} 
</style>