<template>
<div > 
  This before we call the deck-details element
  {{ deck.deck_name }} {{ deck.deck_description}}
  <deck-details  v-bind:deck="deck" />
  </div> 
</template>

<script>
import deckDetails from '../components/DeckDetails.vue';

export default {
    name: 'test-deck-details',
 data(){
   return {
     deck: {
       deck_name: 'Carlos Deck',
       deck_description: 'the best deck ever'
     }
   }
 },
 components: {
        deckDetails
    }

};
</script>

<style>

</style>