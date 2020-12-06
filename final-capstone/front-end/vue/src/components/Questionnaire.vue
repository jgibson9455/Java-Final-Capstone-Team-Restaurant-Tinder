<template>
    <div> 
      <h1>Preferences</h1>
      <form class="preferences">
        <div>
          <h2>Favorite Foods: </h2>
          
          <input type="checkbox" id="Mexican" value="Mexican" required=true />
            <label for="Mexican" class="food-form">Mexican</label><br>

            <input type="checkbox" id="Asian" value="Asian" required=true />
            <label for="Asian" class="food-form">Asian</label><br>
        </div>  

        <div>
        <h2>Allergies: </h2>
            <input type="checkbox" id="Dairy" value="Dairy" required=true/>
           <label for="Dairy" class="food-form">Dairy </label><br>

            <input type="checkbox" id="Peanut Butter" value="Peanut Butter" required=true/>
           <label for="Peanut Butter" class="food-form">Peanut Butter </label><br>

            <input type="checkbox" id="Shellfish" value="Shellfish" required=true/>
           <label for="Shellfish" class="food-form">Shellfish </label><br>

        </div>

        <div>
        <h2>Vegetarian or Vegan? </h2>
            <input type="checkbox" id="Vegetarian" required=true/>
           <label for="Vegetarian" class="food-form">Vegetarian </label><br>

            <input type="checkbox" id="Vegan" required=true/>
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

</style>/