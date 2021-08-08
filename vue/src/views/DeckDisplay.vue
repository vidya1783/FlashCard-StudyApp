<template>
  <div>
      This is the deck display for deck: {{$route.params.id}}

        <card-details v-for="flashcard in flashcards"
  v-bind:flashcard="flashcard" v-bind:key="flashcard.id" />
  <card-details />
  <button>Study Session</button>
  </div>
</template>

<script>
import cardDetails from '../components/CardDetails.vue';
import deckService from '../services/DeckService.js'

export default {
    
    name: 'deck-display',
    data(){
        return {
             flashcards: []
        }  
    },
    created(){
        console.log("greetings from created")
        deckService.getFlashcardsByDeckId(this.$route.params.id)
         .then((response) => { 
            this.flashcards = response.data;
        });
    },
    components: {
        cardDetails
    }
    }




</script>


<style>

</style>