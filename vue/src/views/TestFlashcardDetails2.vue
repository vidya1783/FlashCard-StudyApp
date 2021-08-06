
<template>
<div > 
    Create or update Flashcard
 
  <!--{{ flashcard.question_text }} {{ flashcard.answer_text}}-->
  <card-details v-for="flashcard in flashcards"
  v-bind:flashcard="flashcard" v-bind:key="flashcard.id" />
  <card-details />
  </div> 
</template>

<script>
import cardDetails from '../components/CardDetails.vue';
import flashcardService from '../services/FlashcardService.js'
export default {
    name: 'test-flashcard-details2',
 data(){
   return {
     flashcard: {
       flashcard_id: 1,
       question_text: 'What is USA capital?',
       answer_text: 'Washington DC',
       
     },
     flashcards:[]
   }
 },
 created() {
        flashcardService.getCards()
         .then((response) => { 
            this.flashcards = response.data;
        });
    },
 components: {
        cardDetails
    }

};
</script>

<style>

</style>