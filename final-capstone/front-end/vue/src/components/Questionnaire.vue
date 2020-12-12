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
              <button class="pref1" v-bind:id="type.typeId" v-on:click.prevent="addToPreferences(type.typeId,1)">like</button>
              <button class="pref1" v-bind:id="type.typeId" v-on:click.prevent="addToPreferences(type.typeId,2)">dislike</button>
            </div>
        </div>
      <div class="margin"></div>
        <button type="submit" v-on:click.prevent="savePreferences()">Submit Preferences</button>
      </form>
  </div>


  <div class="all-pref"> 
    <h3> View all food preferences </h3>
 
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

<style>
.preferences {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: blanchedalmond;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
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
  padding-top: 8.5px;
  padding-bottom: 20px;
  align-content: center;
  /* text-shadow: 2px 5px 5px rgba(8, 8, 8, 0.75),
                -5px 6px 7px rgba(8, 8, 8, 0.75); */
  color: #FF5864;
}
.main {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  height: 700px;
}
.food-form{
width: 300px;
position: relative;
left: -20px;
display: inline-block;
vertical-align: middle; 
}
button[class="pref1"] {
  width: 75px;
  position: relative;
  left:100px;
  vertical-align: middle;
}
.margin {
  padding: 10px;
}
.button-container {
  display: flex;
  flex-direction: column;
}
.all-pref {
  text-align: center;
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