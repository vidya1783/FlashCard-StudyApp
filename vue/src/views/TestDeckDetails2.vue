<template>
<div > 
  <!--
  This before we call the deck-details element
  {{ deck.deck_name }} {{ deck.deck_description}}
  {{ decks }} -->

  <deck-details  v-for="deck in decks"
  v-bind:deck="deck" v-bind:key="deck.id" />
  <deck-details />
  </div> 
</template>

<script>
import deckDetails from '../components/DeckDetails.vue';
import deckService from '../services/DeckService.js'

export default {
    name: 'test-deck-details2',
 data(){
   return {
     deck: {
       deck_name: 'Carlos Deck',
       deck_description: 'the best deck ever',
       deck_id: 50
     },
     decks: []
   }
 },
     created() {
        deckService.getDecks()
         .then((response) => { 
            this.decks = response.data;
        });
    },
 components: {
        deckDetails
    }

};
</script>

<style>

</style>