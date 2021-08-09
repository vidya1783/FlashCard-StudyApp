<template>
<div>
    <h1>
        AddCardsToDeck
    </h1>
    <p>{{flashcard.answer_text}}</p>
    
    </div></template>

<script>

import deckService from '../services/DeckService.js'


export default {
    name:"add-card-to-deck1",
    props:["flashcard", 
            "deck_id"],

   methods:{
       addCardToDeck(){
           deckService.attachCardToDeck(this.deck_id, this.flashcard.flashcard_id);
            
        
       }
   } 



}
</script>

<style>

</style>