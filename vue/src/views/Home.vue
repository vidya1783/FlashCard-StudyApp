<template>
  <div class="home">
    <h1>Welcome Back</h1>
    <!--<router-link :to="{ name: 'test-deck-details-2' }">My Decks</router-link>|
    <router-link :to="{ name: 'test-flashcard-details2' }">My Flashcards</router-link>-->

   <!-- <p>This is the home page</p>-->
   <div class="picture">
   <img src=https://is4-ssl.mzstatic.com/image/thumb/Purple125/v4/18/04/f7/1804f74b-3d69-e896-090d-75587ad0c6de/source/512x512bb.jpg/>
   </div> 
    <decks-list/>
  </div>
</template>

<script>
import decksList from "../components/DecksList.vue";

export default {
  name: "home",
  components: {
    decksList 
  }
};
</script>

<style >

</style>