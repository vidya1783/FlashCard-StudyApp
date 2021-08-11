<template>
  <div>
    <div id="main">
      <h1>This is the deck display for deck: {{$route.params.id}}</h1>
    <div class="cards">
        <attached-card-details v-for="flashcard in flashcards"
          v-bind:flashcard="flashcard" v-bind:deck_id="deckId" @send="updateNotinDeckCards" v-bind:key="flashcard.id" />
       <div class="card" >
        <h2> The following cards do not belong to the deck. Do you want to assign them to this deck? </h2></div>
        <div class="cards">
          <add-card-to-deck1 v-for="element in notindeck"
          v-bind:flashcard="element" v-bind:deck_id="deckId"  @send="updateInDeckCards"  v-bind:key="element.id" />
        </div>
    </div>
   <div class="button"><router-link v-bind:to="{name:'study-session', params: {id:this.$route.params.id}}" tag="v-btn"> Study Session </router-link>
   
    <div class="button"><router-link to="/" tag="v-btn"> Home </router-link></div>
   
  </div>
  
  <div>
        



</div>
</div>
  </div>

</template>

<script>
import AttachedCardDetails from '../components/AttachedCardDetails.vue';
import deckService from '../services/DeckService.js';
import AddCardToDeck1 from '../components/AddCardToDeck1.vue';

export default {
    
    name: 'deck-display',
    data(){
        return {
            deckId: this.$route.params.id,
             //flashcards: [],
             //notindeck: []
        } 
    },
    computed: {
      flashcards() {
        return this.$store.state.activeFlashcards;
      },
      notindeck(){
        return this.$store.state.availableFlashcards;
      },

    },
    created(){
      this. updateInDeckCards();
      this.updateNotinDeckCards();
    },
    components: {
        AttachedCardDetails,
        AddCardToDeck1
    },
    methods: {
  
        updateCards(){
          this.updateInDeckCards();
          this.updateNotinDeckCards();
        },
        updateInDeckCards(){
          deckService.getFlashcardsByDeckId(this.$route.params.id)
         .then((response) => { 
           this.$store.commit('SET_ACTIVEFLASHCARDS', response.data)
        })},
        updateNotinDeckCards(){
          deckService.getFlashcardsNotinDeckId(this.$route.params.id)
         .then((response) => { 
                this.$store.commit('SET_AVAILABLEFLASHCARDS', response.data)
        })},
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
  padding-top: 30px;
}
.button{
  font-size:40px;
  
  
}
#main    {
     background-color: rgba(117, 167, 214, 0.692);
     height: 1000px;;
     width: 1500px;
    
     border: 1px solid teal /* width, style, colour */
     } 
     .card{
       padding-top:20px;
     }


</style>
