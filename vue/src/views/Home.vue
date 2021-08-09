<template>
  <div class="home">
    <h1>Welcome Back</h1>
    <!--<router-link :to="{ name: 'test-deck-details-2' }">My Decks</router-link>|
    <router-link :to="{ name: 'test-flashcard-details2' }">My Flashcards</router-link>-->

   <!-- <p>This is the home page</p>-->
   <img src=https://img.favpng.com/18/7/8/educational-flash-cards-nhl-19-video-games-archive-png-favpng-gg68jtcGjiKhEUhCF2NahSfjA.jpg /> 
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