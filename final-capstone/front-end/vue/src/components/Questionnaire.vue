<template>
<body>

   
      <div class="heading"> 
      <h2>Preferences</h2>
      </div>
 

      <form class="preferences">
        <h2>Popular Food Preferences: </h2>
        <div class="favorites">

          <div class="button-container" v-for="type in top20" v-bind:key="type.typeId" > 
            <h5>{{type.typeName}}</h5>
              <button class="pref1" v-bind:id="type.typeId" v-on:click.prevent="addToPreferences(type.typeId,1)">like</button>
              <button class="pref1" v-bind:id="type.typeId" v-on:click.prevent="addToPreferences(type.typeId,2)">dislike</button>
            </div>

        </div> <!-- favorites div -->

           
           <button class="submit" type="submit" v-on:click.prevent="savePreferences()">Submit Preferences</button>
      </form>
  


  <div class="all-pref"> 
    <button class="submit" v-on:click.prevent="toggleShowAllPrefs()"> View all food preferences </button>
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
      aProfilePreference: {
        userName: "",
        typeId: "",
        preferenceId: ""
      },
    top20: [],
    selectedLikeIds: [],
    selectedDislikeIds: [],
    savedPreferences: []
      }
    },
    methods: {
      savePreferences() {
        appServices.getPreferencesByUsername(this.$store.state.user.username).then((response) => {
          response.data.forEach((preference) => {
            this.savedPreferences.push(preference);
          });
        });
        this.$router.back('/home');
      },
      addToPreferences(id, value){
        this.aProfilePreference.userName = this.$store.state.user.username;
        this.aProfilePreference.preferenceId = value;
        this.aProfilePreference.typeId = id;
        appServices.addPreference(this.aProfilePreference).then(()=> {this.aProfilePreference = "";})
      },
      toggleShowAllPrefs() {
        alert('here');
      }
},
created() {
      appServices.getAllRestaurantTypes().then((response) => {
      this.top20 = response.data;
      });

      appServices.getPreferencesByUsername(this.$store.state.user.username).then((response) =>{
        response.data.forEach((preference) =>{
          this.savedPreferences.push(preference);
      });
    });

    }
}
</script>

<style scoped>
body{
  display: grid;
  grid-template-columns: .25fr 1fr 1fr 1fr .25fr;
  grid-template-areas: 
  "head head    head    head    head"
  ".    cont    cont    cont    ."
  ".    allpref allpref allpref ."
  "foot foot    foot    foot    foot"

}
.preferences {
  grid-area: cont;
  background-color: blanchedalmond;
  border-radius: 2%;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  align-items: center;
  text-align: center;
  }

.favorites {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  padding-bottom: 50px;
  }

.heading {
  grid-area: head;
  text-align: center;
  font-size: 2.2em;
  padding-top: 8.5px;
  padding-bottom: 20px;
  align-content: center;
  color: #FF5864;
}

.all-pref {
  grid-area: allpref;
  text-align: center;
}
 button {
   padding: 5px;
   margin: 5px;
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