<template>
  <div>
<div id="main">
      <h1>This is the deck display for deck: {{$route.params.id}}</h1>
<div class="cards">
        <card-details v-for="flashcard in flashcards"
  v-bind:flashcard="flashcard" v-bind:key="flashcard.id" />
  <card-details />
 </div>
   <div class="button"><router-link v-bind:to="{name:'study-session', params: {id:this.$route.params.id}}" tag="v-btn"> Study Session </router-link>
   </div>
  </div>
  </div>
</template>

<script>
import cardDetails from '../components/CardDetails.vue';
import deckService from '../services/DeckService.js'

export default {
    
    name: 'deck-display',
    data(){
        return {
             flashcards: []
        }  
    },
    created(){
        
        deckService.getFlashcardsByDeckId(this.$route.params.id)
         .then((response) => { 
            this.flashcards = response.data;
        });
    },
    components: {
        cardDetails
    }
    }




</script>


<style>
h1{
  font-size:50 px;
}
.cards{
  display: flex;
  
justify-content: center;
  align-items: center;
  padding-bottom: 30px;
}
.button{
  font-size:40px;
  
  
}
#main    {
     background-color:rgb(197, 226, 226);
     height: 1000px;;
     width: 1500px;
     margin: 50px auto;
     border: 1px solid teal /* width, style, colour */
     } 


</style>