<template>
<body class="flex-container">
  
    <div class="main">
      <div class="heading"> 
      <h2><u id="pref-line">Preferences</u></h2>
      </div>
      <form class="preferences">
        <div class="favorites">
          <h2>Favorite Foods: </h2>
          
            <input type="checkbox" id="Mexican" value="Mexican"/>
            <label for="Mexican" class="food-form">Mexican</label><br>

            <input type="checkbox" id="Asian" value="Asian" />
            <label for="Asian" class="food-form">Asian</label><br>

            <input type="checkbox" id="Seafood" value="Seafood" />
            <label for="Seafood" class="food-form">Seafood</label><br>

            <input type="checkbox" id="Italian" value="Italian" />
            <label for="Italian" class="food-form">Italian</label><br>
        </div>

        <div class="dislikes">
        <h2>Disliked Food: </h2>
            <input type="checkbox" id="Mexican-dis" value="Mexican"/>
            <label for="Mexican-dis" class="food-form">Mexican</label><br>

            <input type="checkbox" id="Asian-dis" value="Asian"/>
            <label for="Asian-dis" class="food-form">Asian</label><br>

            <input type="checkbox" id="Seafood-dis" value="Seafood"/>
           <label for="Seafood-dis" class="food-form">Seafood</label><br>

           <input type="checkbox" id="Italian-dis" value="Italian"/>
            <label for="Italian-dis" class="food-form">Italian</label><br>

        </div>

        <!--<div class="veggie">
        <h2>Vegetarian or Vegan? </h2>
            <input type="checkbox" id="Vegetarian"/>
           <label for="Vegetarian" class="food-form">Vegetarian </label><br>

            <input type="checkbox" id="Vegan"/>
           <label for="Vegan" class="food-form">Vegan </label>

        </div> -->
      <div class="margin"></div>
        <router-link v-bind:to="{name: 'home'}"><input class="button" type="submit"/></router-link>
      </form>
    

  </div>
  </body>
</template>

<script>
export default {
    name: 'questionnaire'
}
</script>

<style>
.preferences {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: blanchedalmond;
  /*object-fit: fill;*/
  height: 400px;
  flex-shrink: 2;
  text-align: center;
  border-radius: 2%;
  margin-left: 200px;
  margin-right: 200px;
  flex-wrap: wrap;
}

.heading {
  text-align: center;
  font-size: 2.2em;
  padding-top: 10px;
  padding-bottom: 20px;
  align-content: center;
  text-shadow: 2px 5px 5px rgba(8, 8, 8, 0.75),
                -5px 6px 7px rgba(8, 8, 8, 0.75);
    color: blanchedalmond;
}
.main {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  background-image: linear-gradient(to bottom left,  #FF655B, #FD297B);
  height: 700px;
}
.food-form{
 width: 300px;
position: relative;
left: -20px;
display: inline-block;
vertical-align: middle;
  
}
input[type="checkbox"] {
  width: 20px;
  position: relative;
  left:100px;
  vertical-align: middle;

}
.margin {
  padding: 10px;
}
#pref-line {
    text-decoration: none;
    border-bottom: 5px solid blanchedalmond;
    -webkit-font-smoothing: antialiased;
}


@media  (max-width: 500px) {
  .preferences {
    flex-direction: row;
    flex-wrap: wrap;
  }
  .favorites, .dislikes {
    flex-direction: row;
    flex-wrap: wrap;
  }
}

</style>