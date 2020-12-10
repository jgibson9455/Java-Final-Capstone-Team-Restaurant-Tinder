<template>
<body class="flex-container">
  
    <div class="main">
      <div class="heading"> 
      <h2><u id="pref-line">Preferences</u></h2>
      </div>
      <form class="preferences">
        <div class="favorites">
          <h2>Popular Food Preferences: </h2>
          <div class="button-container" v-for="type in top20" v-bind:key="type.typeId"> 
            <h5>{{type.typeName}}</h5>
              <button class v-bind:id='type.typeId' v-on:click.prevent="preferenceLike(true)" v-if="preferenceLike(true)">remove like</button>
              <button class v-bind:id='type.typeId' v-on:click.prevent="preferenceLike(false)" v-if="! preferenceLike(true)">like</button>

              <button class v-bind:id='type.typeId' v-on:click.prevent="preferenceDislike(true)" v-if="preferenceDislike(true)">remove dislike</button>
              <button class v-bind:id='type.typeId' v-on:click.prevent="preferenceDislike(false)" v-if="! preferenceDislike(true)">dislike</button>

            </div>
            

        </div>

      <div class="margin"></div>
        <router-link v-bind:to="{name: 'home'}"><input class="button" type="submit"/></router-link>
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
    top20: [],
      }
    },
    methods: {
      preferenceLike(value) {
        if(value == true) {
            this.$store.commit('SET_LIKE_STATUS', {type: this.type, value: value}); 
        }
        },
      preferenceDislike(value) {
        this.$store.commit('SET_DISLIKE_STATUS', {type: this.type, value: value});
      }
      
      },
    created() {
      appServices.getAllRestaurantTypes().then((response) => {
      this.top20 = response.data;
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