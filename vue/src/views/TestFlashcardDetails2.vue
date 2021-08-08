
<template>
<div>
<div class="flash">
  <h3> My Flash Cards</h3>
  </div>

<div class="test-flashcard-details2" > 
  
  
  <!--{{ flashcard.question_text }} {{ flashcard.answer_text}}-->
  <card-details v-for="flashcard in flashcards"
  v-bind:flashcard="flashcard" v-bind:key="flashcard.id" />
  <card-details />
  </div> 
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
.test-flashcard-details2{
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    padding: 20px;
    height: 70%;
    width: 100%;
    font-size: 40px;
    color: rgb(167, 167, 167);
    font-weight: 600;
    text-align: center;
}

.flash {
  display: flex;
  justify-content: center;
  color: teal ;

}
</style>