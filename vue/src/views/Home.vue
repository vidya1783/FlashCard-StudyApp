<template>
<div>
  <div class="welcome" >
    <h1>Welcome Back</h1>
    <!--<router-link :to="{ name: 'test-deck-details-2' }">My Decks</router-link>|
    <router-link :to="{ name: 'test-flashcard-details2' }">My Flashcards</router-link>-->

   <!-- <p>This is the home page</p>-->
  </div>
 
   <div class="home" >
   <div class="picture">
   <img src="https://lh3.googleusercontent.com/proxy/mJKygWYwIxjBq-ZYh4gIqHSM8JCm1rf7CgDcSmrVXgsN-NUzpXyVIRkas1CBvjIagA1soALKA5GSLKPYxl0uIUqkDw"/>
   </div> </div>
    <!--<decks-list/>-->
</div>
</template>

<script>
//import decksList from "../components/DecksList.vue";

export default {
  name: "home",
  components: {
    //decksList 
  }
};
</script>

<style scoped >
.home{
  background-color: rgba(117, 167, 214, 0.692);
  height:800px;
  font-size: 40px;
  display:flex;
  flex-direction: column;
  
  padding-top:20px;
}
.welcome{
  font-size:25px;
  background-color:rgb(245, 245, 10);
}

</style>