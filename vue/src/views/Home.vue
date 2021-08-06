<template>
  <div class="home">
    <h1>Home</h1>
    <router-link :to="{ name: 'test-deck-details-2' }">My Decks</router-link>|
    <router-link :to="{ name: 'test-flashcard-details2' }">My Flashcards</router-link>

   <!-- <p>This is the home page</p>-->
   
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