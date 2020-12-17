<template>
<div>
  <head> <meta name="viewport" content="width=device-width, initial-scale=1.0"> </head>
  <link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Bad+Script&display=swap" rel="stylesheet">
   
      <div class="heading"> 
      <h2>Preferences</h2>
      </div>
 

      <form class="preferences">
        <h2>Popular Food Preferences: </h2>
        <div class="favorites">

          <div class="button-container" v-for="type in top20" v-bind:key="type.typeId" > 
            <h5 class="food-name">{{type.typeName}}</h5>
              <button class="pref1" v-bind:id="type.typeId" v-on:click.prevent="addToPreferences(type.typeId,1, type.typeName)"><img src="../img/Like.png"/></button>
              <button class="pref1" v-bind:id="type.typeId" v-on:click.prevent="addToPreferences(type.typeId,2, type.typeName)"><img src="../img/Dislike.png" /></button>
            </div>

        </div> <!-- favorites div -->

           <button class="submit" type="submit" v-on:click.prevent="savePreferences()">Submit Preferences</button>
           <button class="submit" v-on:click.prevent="isHidden = !isHidden">{{isHidden ? "View all food preferences" : "Hide all food preferences"}} </button>
          
         <div class="scroll" v-if="isHidden === false">
            <div class="button-container" v-for="type in allCuisines" v-bind:key="type.cuisine_id" > 
            <h5 class="food-name">{{type.typeName}}</h5>
              <button class="pref1" v-bind:id="type.typeId" v-on:click.prevent="addToPreferences(type.typeId,1, type.typeName)">
                <img src="../img/Like.png"/>
                 </button>
              
              <button class="pref1" v-bind:id="type.typeId" v-on:click.prevent="addToPreferences(type.typeId,2, type.typeName)">
                <img src="../img/Dislike.png"/>
                </button>
            </div>
    </div> <!--scoll div -->    
      </form>
      

</div>
</template>

<script>
import appServices from '@/services/ApplicationServices.js'
// import zomatoServices from '@/services/ZomatoServices.js'
export default {
    name: 'questionnaire',
    data() {
      return {
      type: {
      typeId: "",
      typeName: "",
      showPrefs: true,
      isClicked: false
    },
      aProfilePreference: {
        userName: "",
        typeId: "",
        typeName: "",
        preferenceId: ""
      },
    top20: [],
    selectedLikeIds: [],
    selectedDislikeIds: [],
    savedPreferences: [],
    allCuisines: [],
    isHidden: true,
    cityId: ""
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
      addToPreferences(id, value, name){
        this.aProfilePreference.userName = this.$store.state.user.username;
        this.aProfilePreference.preferenceId = value;
        this.aProfilePreference.typeId = id;
        this.aProfilePreference.typeName = name;
        appServices.addPreference(this.aProfilePreference).then(()=> {this.aProfilePreference = "";})
      },
      
},
created() {
      appServices.getTop20Types().then((response) => {
      this.top20 = response.data;
      });

      appServices.getNonTop20Types().then((response)=>{
      this.allCuisines = response.data;

      this.allCuisines.forEach((cuisine)=> {
        cuisine.isClicked = false;
      })
      })


      appServices.getPreferencesByUsername(this.$store.state.user.username).then((response) =>{
        response.data.forEach((preference) =>{
          this.savedPreferences.push(preference);
      });
    })

  
      // appServices.getProfileByUsername(this.$store.state.user.username).then((response)=>{
      //   this.profile = response.data;

      // zomatoServices.getCityInfo(this.profile.city).then((response) =>{
      //   this.cityId = response.data.location_suggestions[0].city_id;

      //   zomatoServices.getAllCuisines(this.cityId).then((response) =>{ 
      //     response.data.cuisines.forEach((cuisine)=> {
      //       this.type.typeId = cuisine.cuisine.cuisine_id
      //       this.type.typeName = cuisine.cuisine.cuisine_name
      //       this.allCuisines.push(this.type);
      //       this.type= {};
      //     })
      //     })
      //   })
      // })
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Bad+Script&display=swap');

body{
  display: grid;
  grid-template-columns: .25fr 1fr 1fr 1fr .25fr;
  grid-template-areas: 
  "head head    head    head    head"
  ".    toppref toppref toppref ."
  ".    allpref allpref allpref ."
  "foot foot    foot    foot    foot"

}
.preferences {
  grid-area: toppref;
  background-color: blanchedalmond;
  border-radius: 2%;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  align-items: center;
  text-align: center;
  display: flex;
  flex-direction: column;
  
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
  align-content: center;
  color: #FF5864;
  font-family: 'Bad Script', cursive;
}

.scroll {
  grid-area: allpref;
  display: grid;
  text-align: center;
  align-items: stretch; 
  justify-items: center;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
  overflow-x: hidden;
  overflow-y:auto;
  width: 93%;
  height: 368px;
  margin: 50px;
  background: url("../img/gradient-blob.png") no-repeat;
  background-size: contain;
  border-radius: 2%;
 
}

 button {
   margin: 1px;
   font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
 }
button.submit {
  display: grid;
  text-align: center;
  padding: 10px 20px;
  align-content: center;
  justify-content: center;
} 
button.pref1 {
  background-color: transparent; 
  border-radius: 50%;
  border: none; 
  width: 30%;
}

img {
  height: auto;
  width: 65%;
  transform: scale(0.98);
  box-shadow: 3px 2px 22px 1px rgba(0, 0, 0, 0.24); 
  border-radius: 80%;
  background-size: auto;
  cursor: pointer;
}

/* .food-name .pref1:clicked + img {
  opacity: 0.6;
} */

 img:active {
  transform: scale(0.98);
  box-shadow: 6px 4px 34px 2px rgba(0, 0, 0, 0.48); 
  border-radius: 80%
 }



@media screen and (max-width: 800px) {
  .preferences {
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    display: flex;
  }
  .main {
    height: 50em;
  }
  .button-container {
    display:flex;
    grid-template-columns: 1fr;
  }
  body {
    grid-template-columns: 1fr;
    display: flex;
    flex-direction:column;
  }
  img {
    background-size: auto;
    }
    .favorites {
      display: flex;
    }
  .scroll {
    display: grid;
    grid-template-columns: 1fr;
    background: none;
  }

}

@media screen and (max-width: 450px) {
  .preferences {
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    display: flex;
    flex-direction: column;
  }
  
  .main {
    height: 50em;
  }
  .button-container {
    display:flex;
    grid-template-columns: 1fr;
    flex-wrap: wrap;
  }
  body {
    grid-template-columns: 1fr;
    display: flex;
    flex-direction:column;
    
  }
  img {
    background-image:none;
}
  .scroll {
    display: grid;
    grid-template-columns: 1fr;
     background: none;
     
  }
 .favorites {
      display: flex;
      align-content:right;
      justify-content: space-evenly;
    }
    h2 {
      text-align: center;
    }
}
</style>