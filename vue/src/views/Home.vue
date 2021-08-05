<template>
  <div class="home">
    <h1>Home</h1>
    <p>This is the home page</p>
     <v-btn
  elevation="2"
></v-btn>
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