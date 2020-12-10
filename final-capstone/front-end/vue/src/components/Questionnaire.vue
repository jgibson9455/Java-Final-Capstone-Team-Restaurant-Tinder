<template>
<body class="flex-container">
    <div class="main">
      <div class="heading"> 
      <h2><u id="pref-line">Preferences</u></h2>
      </div>
      <form class="preferences">
        <div class="favorites">

          <h2>Popular Food Preferences: </h2>
          <div class="button-container" v-for="type in top20" v-bind:key="type.typeId" > 
            <h5>{{type.typeName}}</h5>
              <button class v-bind:id="type.typeId" v-on:click.prevent="toggleLikes(type.typeId)">like</button>
              <button class v-bind:id="type.typeId" v-on:click.prevent="toggleDislikes(type.typeId)">dislike</button>
            </div>
            

        </div>
      <div class="margin"></div>
        <button type="submit" v-on:click="savePreferences()">Submit Preferences</button>
      </form>
    

  </div>
  </body>
</template>

<script>
import appServices from '@/services/ApplicationServices.js'
export default {
    name: 'questionnaire',
    data() {
      return {
      type: {
      typeId: "",
      typeName: ""
    },
      profilePreferences: {
        userName: this.$store.state.user.userName,
        typeId: "",
        preferenceId: ""
      },
    top20: [],
    selectedLikeIds: [],
    selectedDislikeIds: []
      }
    },
    methods: {
    toggleLikes(id){
    //Not in either array
    if(this.selectedLikeIds.indexOf(id) === -1 && this.selectedDislikeIds.indexOf(id) === -1){
      this.selectedLikeIds.push(id);
    }else{
      //Check if the id is IN the dislikes
      if(this.selectedDislikeIds.indexOf(id) !== -1){
      //It is so display alert message
        alert("You cannot have a type in both like and dislike")
      }else{
      //It already is in the liked array, take it out
        this.selectedLikeIds.splice(this.selectedLikeIds.indexOf(id), 1);
      }
    }
  },
      toggleDislikes(id){
    //Not in either array
    if(this.selectedDislikeIds.indexOf(id) === -1 && this.selectedLikeIds.indexOf(id) === -1){
      this.selectedDislikeIds.push(id);
    }else{
      //Check if the id is IN the dislikes
      if(this.selectedLikeIds.indexOf(id) !== -1){
      //It is so display alert message
        alert("You cannot have a type in both like and dislike")
      }else{
      //It already is in the liked array, take it out
        this.selectedDislikeIds.splice(this.selectedDislikeIds.indexOf(id), 1);
      }
    }
      },
      savePreferences() {
        this.selectedLikeIds.forEach((id) => {
          this.profilePreferences.preferenceId = 1;
          this.profilePreferences.typeId = id;
          this.$store.state.commit('SET_PREFERENCE_LIKE_STATUS', this.profilePreferences);
          this.appServices.addPreferences(this.profilePreferences);
        })
        this.selectedDislikeIds.forEach((id) => {
          this.profilePreferences.preferenceId = 2;
          this.profilePreferences.typeId = id;
          this.$store.state.commit('SET_PREFERENCE_DISLIKE_STATUS', this.profilePreferences);
          this.appServices.addPreferences(this.profilePreferences);
        })
        this.$router.back(`/home`);
      }
    
},
created() {
      appServices.getAllRestaurantTypes().then((response) => {
      this.top20 = response.data;
      });

      this.$store.state.profilePreferences.forEach((preference) => {
        if(preference.preferenceId === 1) {
          this.selectedLikeIds.push(preference.typeId)
        }else if (preference.preferenceId === 2) {
          this.selectedDislikeIds.push(preference.typeId);
        }
      })
    }
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


@media screen and (max-width: 800px) {
  .preferences {
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
  }
  .main {
    height: 50em;
  }
}

</style>