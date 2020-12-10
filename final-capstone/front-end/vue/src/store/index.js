import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));
const currentProfile = JSON.parse(localStorage.getItem('profile'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    profile: currentProfile || {},
    favorites: [],
    dislikes: [],
    preferenceLikes: [],
    preferenceDislikes: []
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
      state.dislikes.length = 0;
    },

    UPDATE_PROFILE(state, profile) {
      state.profile = profile;
    },
    ADD_TO_FAVES(state, restaurant) {
      state.favorites.push(restaurant);
    },
    ADD_TO_DISLIKE(state, restaurant) {
      state.dislikes.push(restaurant);
    },
    SET_PREFERENCE_LIKE_STATUS(state, profilePreference) {
      state.preferenceLikes.push(profilePreference);
    },
    SET_PREFERENCE_DISLIKE_STATUS(state, profilePreference) {
      state.preferenceDislikes.push(profilePreference);
    }
  }
})
