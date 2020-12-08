<template>
    <div class="main">
      <div class="heading"> 
      <h1>Preferences</h1>
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
            <input type="checkbox" id="Mexican" value="Mexican"/>
            <label for="Mexican" class="food-form">Mexican</label><br>

            <input type="checkbox" id="Asian" value="Asian"/>
            <label for="Asian" class="food-form">Asian</label><br>

            <input type="checkbox" id="Seafood" value="Seafood"/>
           <label for="Seafood" class="food-form">Seafood</label><br>

           <input type="checkbox" id="Italian" value="Italian"/>
            <label for="Italian" class="food-form">Italian</label><br>

        </div>

        <!--<div class="veggie">
        <h2>Vegetarian or Vegan? </h2>
            <input type="checkbox" id="Vegetarian"/>
           <label for="Vegetarian" class="food-form">Vegetarian </label><br>

            <input type="checkbox" id="Vegan"/>
           <label for="Vegan" class="food-form">Vegan </label>

        </div> -->

        <input type="submit"/>
      </form>
    

  </div>
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
  background-color: white;
  object-fit: fill;
  height: 400px;
 
  text-align: center;
  border-radius: 50%;
}
.heading {
  text-align: center;
}
.main {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  background-image: linear-gradient(to bottom left,  #FF655B, #FD297B);
  height: 700px;
}
.favorites, .dislikes {
    display: flex;
    justify-content: center;
    align-items:center;
    
    flex-direction: column;

}


</style>