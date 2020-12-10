<template>
    <div id="card">
        <h2>{{this.restaurant.restaurantName}}</h2>
        <img class="company-logo" v-bind:src="this.restaurant.imageLink"/>
    </div>
</template>
<script>
export default {
    props: ["restaurant"], 
}
</script>
<style scoped>
img.company-logo {
    background-color: rgb(209, 209, 209);
    border-radius: 5%;
    width: 200px;
}
</style>