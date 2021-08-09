<template>
<div>
    <div v-if="!clicked">
        <div>{{flashcard.flashcard_id}}</div>
   <div> {{flashcard.question_text}} </div>
    <div>{{flashcard.answer_text}}</div>
    <div>{{flashcard.creator_id}}</div>
     <button v-on:click="addCardToDeck">Attach</button>
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
       }
   } 



}
</script>

<style>

</style>