<template>
  <div>
      <div>
    <h2>  The following cards belong to deck : {{$route.params.id}}</h2>

        <card-details v-for="flashcard in flashcards"
  v-bind:flashcard="flashcard" v-bind:key="flashcard.id" />
  <card-details />


  <router-link v-bind:to="{name:'study-session', params: {id:this.$route.params.id}}"> Study Session </router-link>
      </div>
      <div>
          <h2> The following cards do not belong to the deck. Do you want to assign them to this deck? </h2>
          <div v-for="flashcard in notindeck"
  v-bind:flashcard="flashcard" v-bind:key="flashcard.id">
  <p> Question text: {{flashcard.question_text}} </p>
<p> Answer text: {{flashcard.answer_text}} </p>
<p> Flashcard Id: {{ id = flashcard.flashcard_id}} </p>
<p> Creator Id: {{ flashcard.creator_id}}</p>

// button here

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
        cardDetails
    },
    methods: {
        attachToThisDeck(){
            alert("This is getting called")
          
        }
    }
    }




</script>


<style>

</style>
