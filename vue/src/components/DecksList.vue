<template>
  <div class="decks-list">
      <ul>
      <li v-for="deck in decks" v-bind:key="deck.id">
     <!-- {{deck.deck_name}} 
      {{deck.deck_description}}-->
      </li>
      </ul>
  </div>
</template>

<script>

import deckService from '../services/DeckService.js';

export default {
name: 'decks-list',
data()   {
    return{
    decks: []
    }
}, 

    created() {
        deckService.getDecks()
         .then((response) => { 
            this.decks = response.data;
        });
    } 

}
</script>

<style>

</style>