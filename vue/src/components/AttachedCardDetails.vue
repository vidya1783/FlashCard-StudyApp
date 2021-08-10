<template>
<div>
  <div id="cardindeck" class="card" v-if="!clicked" >
            <div><span class="questionspan" > Question:</span> {{flashcard.question_text}} </div>
            <div><span class="answerspan"> Answer:</span> {{flashcard.answer_text}}</div>
    
            <button class='deletebutton' v-on:click="deleteCardFromDeck">remove</button>
</div>
</div>
</template>
<script>
 import deckService from '@/services/DeckService';
export default {
  name: 'attached-card-details',
  props: ['flashcard', 'deck_id'],
  data(){
    return {
   clicked: false
        }
  },
  created() {
   
     },
  methods: {
        deleteCardFromDeck(){ 
           deckService.detachCardFromDeck(this.deck_id, this.flashcard.flashcard_id);
            this.clicked = true;   
           
          
        
       }
 
    }
}
</script>
<style scoped>

#cardindeck{
     background-color:white;
     width: 200px;
     height: 200px;
     border: 2px;
     border-style: solid;
     padding: 2px;
 }
 .deletebutton{
     background-color: red;
     width: 40%;
     text-align: center;
     color: white;
     font-weight: bold;
      border: 2px;
     border-style: solid;
     border-color: lightblue;

 }
 .questionspan {
     color: rgb(173, 27, 51);
     font-weight: bold;
 }
 .answerspan {
     color: rgb(173, 27, 51);
     font-weight: bold;
 }


</style>