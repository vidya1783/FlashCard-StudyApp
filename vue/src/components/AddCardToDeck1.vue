<template>
    <div>
        <div id="attachcard" class="card" v-if="!clicked">
  
            <div><span class="questionspan" > Question:</span> {{flashcard.question_text}} </div>
            <div><span class="answerspan"> Answer:</span> {{flashcard.answer_text}}</div>
    
            <button class='attachbutton' v-on:click="addCardToDeck">attach</button>
        </div>
    
    </div>
</template>

<script>

import deckService from '../services/DeckService.js';


export default {
    name:"add-card-to-deck1",
    props:["flashcard", 
            "deck_id"],
    displayFlashcard: {

    },
    data(){
        return {
        clicked: false
        }
    },
    

   methods:{
    
       addCardToDeck(){
           deckService.attachCardToDeck(this.deck_id, this.flashcard.flashcard_id);    
           this.clicked = true;   
           this.$router.push({name:'deck-display', params: {id: this.deck_id}});    
       }
   } 



}
</script>

<style scoped>

 #attachcard{
     background-color:white;
     width: 200px;
     border: 2px;
     border-style: solid;
     padding: 2px;
 }
 .attachbutton{
     background-color: blue;
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