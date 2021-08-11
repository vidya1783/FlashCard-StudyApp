<template>
<div>
  <div class="decks">
  <h1>My Decks</h1>
  </div>
  <div class="test-deck-details2">

 <deck-details  v-for="deck in decks"
  v-bind:deck="deck" v-bind:key="deck.id" class="deck"/>

  <deck-details />
  
  </div>
  </div>
  
  <!--
  This before we call the deck-details element
  {{ deck.deck_name }} {{ deck.deck_description}}
  {{ decks }} -->

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

<style scoped>
.test-deck-details2{
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    padding: 20px;
    height: 70%;
    width: 100%;
    font-size: 40px;
   background:linear-gradient(to right, #E9F8FF, #90B2D8, #C1E3FF);
    font-weight: 600;
    text-align: center;
    height:700px;
    align-items:flex-start;}

.decks {
  display: flex;
  justify-content: center;
   color: white;
  font-size:25px;
  background-color:  #263472;
   
}


</style>