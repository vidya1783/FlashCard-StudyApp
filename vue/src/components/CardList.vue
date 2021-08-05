<template>
  <div class="card-details">
      <ul>
      <li v-for="flashcard in flashcards" v-bind:key="flashcard.id">
      {{flashcard.question_text}} 
      {{flashcard.answer_text}}
      </li>
      </ul>
  </div>
</template>

<script>

import flashcardService from '../services/FlashcardService.js';

export default {
name: 'card-details',
data()   {
    return{
    flashcards: []
    }
}, 

    created() {
        flashcardService.getCards()
         .then((response) => { 
            this.flashcards = response.data;
        });
    } 

}
</script>

<style>

</style>