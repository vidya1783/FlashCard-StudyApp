
<template>
<div>
<div class="flash">
  <h1> My Flash Cards</h1>
  </div>

<div class="test-flashcard-details2" > 
  
  
  <!--{{ flashcard.question_text }} {{ flashcard.answer_text}}-->
  <card-details v-for="flashcard in flashcards"
  v-bind:flashcard="flashcard" v-bind:key="flashcard.id" />
  <div class="createcard"><card-details /></div>
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

<style scoped>

.test-flashcard-details2{
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    padding: 20px;
    height: 70%;
    width: 100%;
    font-size: 40px;
    background:linear-gradient(to right, rgb(236, 233, 233), #f2f5f8, white);
    font-weight: 600;
    text-align: center;
    height:700px;
}

.flash {
  display: flex;
  justify-content: center;
  color:white;
  font-size:25px;
  background:linear-gradient(45deg,black, white);
 

}

.createcard {
  
  background-color: red;

}
</style>