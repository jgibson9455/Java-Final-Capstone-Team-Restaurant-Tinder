<template>
    <div>
        <div id="card">
            <h2 id="card-name">{{this.restaurant.restaurantName}}</h2>
            <h3 id="card-type">{{this.restaurant.typeName}}</h3>
            <img class="company-logo" v-bind:src="this.restaurant.imageLink"/>
        </div>

        <div class="details">
            <button class="details-btn" v-on:click="showDetails = true">
                Click for details
            </button>

            <transition name="fade2" appear>
                <div class="overlay" v-if="showDetails" v-on:click="showDetails = false"></div>
            </transition>
            <transition name="slide2" appear>
                <div class="details-popup" v-if="showDetails">
                    <img class="details-pic" v-bind:src="this.restaurant.imageLink"/>
                    <h1 class="rest-name">{{this.restaurant.restaurantName}}</h1>
                    <h1 class="rest-type">{{this.restaurant.typeName}}</h1>
                    <h2 class="rest-phone">{{this.restaurant.phoneNumber}}</h2>
                    <h3 class="rest-address">{{this.restaurant.address}} {{this.restaurant.city}} {{this.restaurant.zipCode}}</h3>
                    <p class="rest-description">{{this.restaurant.restaurantDescrip}}</p>
                    <button class="close-btn" v-on:click="showDetails = false">
                        Close
                    </button>
                </div>
            </transition>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            showDetails: false
        }
    },
    props: ["restaurant"],
}
</script>
<style scoped>
#card {
    padding-right: 50px;
}
#card-name {
    margin-bottom: -20px;
}
img.company-logo {
    background-color: rgb(209, 209, 209);
    border-radius: 5%;
    height: 150px;
    width: 200px;
    border: black solid 2px;
}

.details {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 98;
    background-color: rgba(0, 0, 0, 0.3);
}

.details-popup {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 99;

    width: 100%;
    max-width: 400px;
    background-color: #FFF;
    border-radius: 16px;
    padding: 25px;
}

.details-popup > h1 {
    color: #222;
    font-size: 32px;
    font-weight: 900;
    margin-bottom: 15px;
}

.details-popup > p {
    color: #666;
    font-size: 18px;
    font-weight: 400;
    margin-bottom: 15px;
}

.fade2-enter-active, .fade2-leave-active {
    transition: opacity .9s;
}

.fade2-enter, .fade2-leave-to {
    opacity: 0;
}

.slide2-enter-active, .slide2-leave-active {
    transition: transform .5s;
}

.slide2-enter, .slide2-leave-to {
    transform: translateY(-50%) translateX(100vw);
}

.details-pic {
    align-self: center;
    height: 300px;
    width: 360px;
}

.details-btn {
    color: blue;
    font-size: 20px;
    margin-top: auto;
    cursor: pointer;
    display: inline-block;
    border-radius: 8px;
    transition: 0.4s ease-out;
    margin-right: 50px;
}

.details-btn:hover {
    color: rgb(108, 108, 238);
    box-shadow: 2px 2px rgba(0, 0, 0, 0.6);
}
</style>