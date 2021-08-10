<template>
  <div>
<div id="main">
      <h1>This is the deck display for deck: {{$route.params.id}}</h1>
<div class="cards">
        <card-details v-for="flashcard in flashcards"
  v-bind:flashcard="flashcard" v-bind:key="flashcard.id" />
  <card-details />
   <h2> The following cards do not belong to the deck. Do you want to assign them to this deck? </h2>
 <div class="cards">
          <add-card-to-deck1 v-for="element in notindeck"
  v-bind:flashcard="element" v-bind:deck_id="deckId" v-bind:key="element.id" />
    </div>
 </div>
   <div class="button"><router-link v-bind:to="{name:'study-session', params: {id:this.$route.params.id}}" tag="v-btn"> Study Session </router-link>
   
    <div class="button"><router-link to="/" tag="v-btn"> Home </router-link></div>
   
  </div>
  
  <div>
        
 <!-- <p> Question text: {{flashcard.question_text}} </p>
<p> Answer text: {{flashcard.answer_text}} </p>
<p> Flashcard Id: {{ id = flashcard.flashcard_id}} </p>
<p> Creator Id: {{ flashcard.creator_id}}</p>-->



</div>
</div>
  </div>

</template>

<script>
import cardDetails from '../components/CardDetails.vue';
import deckService from '../services/DeckService.js';
import AddCardToDeck1 from '../components/AddCardToDeck1.vue';

export default {
    
    name: 'deck-display',
    data(){
        return {
            deckId: this.$route.params.id,
             flashcards: [],
             notindeck: []
      

        }  
    },
    created(){
        
        deckService.getFlashcardsByDeckId(this.$route.params.id)
         .then((response) => { 
            this.flashcards = response.data;
        });
          deckService.getFlashcardsNotinDeckId(this.$route.params.id)
         .then((response) => { 
            this.notindeck = response.data;
        });
    },
    components: {
        cardDetails,
        AddCardToDeck1
    },
    methods: {
        attachToThisDeck(){
            alert("This is getting called")
          
        }
    }
    }




</script>


<style scoped>
h1{
  font-size:50 px;
}
.cards{
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
justify-content: center;
  align-items: space-between;
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
