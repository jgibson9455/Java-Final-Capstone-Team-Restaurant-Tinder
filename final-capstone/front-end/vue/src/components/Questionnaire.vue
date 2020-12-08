<template>
    <div> 
      <h1>Preferences</h1>
      <form class="preferences">
        <div>
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

        <div>
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

        <div>
        <h2>Vegetarian or Vegan? </h2>
            <input type="checkbox" id="Vegetarian"/>
           <label for="Vegetarian" class="food-form">Vegetarian </label><br>

            <input type="checkbox" id="Vegan"/>
           <label for="Vegan" class="food-form">Vegan </label>

        </div>

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

</style>