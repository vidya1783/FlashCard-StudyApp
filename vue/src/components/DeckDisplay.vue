<template>
  <div>
     <h1> This will display all flashcards in this deck -- </h1>
     <div v-for="flashcard in flashcards" v-bind:key="flashcard.id"></div>
     
  </div>
</template>

<script>
import deckService from '../services/DeckService'
export default {
    data(){
        return {
            flashcards: []
        }

    },
    props: ['deckId'],
    created(){
        this.flashcards = deckService.getFlashcardsByDeckId(deckId);

    }

}

</script>

<style>

</style>