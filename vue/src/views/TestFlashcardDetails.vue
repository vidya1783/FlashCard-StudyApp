
<template>
<div > 
    Create or update Flashcard
 
  <!--{{ flashcard.question_text }} {{ flashcard.answer_text}}-->
  <card-details  v-bind:flashcard="flashcard" />
  <card-details />
  </div> 
</template>

<script>
import cardDetails from '../components/CardDetails.vue';

export default {
    name: 'test-flashcard-details',
 data(){
   return {
     flashcard: {
       flashcard_id: 1,
       question_text: 'What is USA capital?',
       answer_text: 'Washington DC',
       
     }
   }
 },
 components: {
        cardDetails
    }

};
</script>

<style>

</style>