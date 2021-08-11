<template>
  <div>
     <div id="main">
    <div class="buttons"><div class="button"><router-link v-bind:to="{name:'study-session', params: {id:this.$route.params.id}}" tag="v-btn"> Study Session </router-link>
   </div>
    <div class="two button">
      <router-link to="/" tag="v-btn"> 
      <i class="material-icons">home</i>
      </router-link>
      </div>
   </div>
  
    
      <h1>This is the deck display for deck: {{$route.params.id}}</h1>
    <div class="cards">
        <attached-card-details v-for="flashcard in flashcards"
          v-bind:flashcard="flashcard" v-bind:deck_id="deckId" v-bind:key="flashcard.id" />
       </div>
        <h2> The following cards do not belong to the deck. Do you want to assign them to this deck? </h2>
        <div class="add-cards">
          <add-card-to-deck1 v-for="element in notindeck"
          v-bind:flashcard="element" v-bind:deck_id="deckId"  v-bind:key="element.id" />
        </div>
        
        <!-- DATA BINDING ARRAY MEMEBERS AND USING COMPUTEDS WITH THEM -->
        
          <!-- 
            <h4>{{this.junkPOS2}}</h4>
            <div>
              <p v-for="obj in junkArray" :key="obj.id">{{obj}}</p>
            </div>
            <button type="button" v-on:click="addJunk">Add Another Piece of Junk</button>
            <br/>
            <button type="button" v-on:click="popJunk2">Pop Index 2</button> 
          -->
        <!---------------------- END ------------------------------------->
  
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
            junk: ["one", "two", "three","four","five"],
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

      junkArray(){
        return this.junk;
      },

      junkPOS2(){
        return this.junk[2];
      }

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
  
        addJunk(){
          this.junk.push("trash");
        },

        popJunk2(){
          this.junk.splice(2,1);
        },

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
justify-content: space-evenly;
padding-top:20px;

  
}
.button{
  font-size:40px;
  padding-right:10px;
  
  
}
#main    {
     background:linear-gradient(to right, rgb(236, 233, 233), #f2f5f8, white);
     height: 1000px;;
     width: 1500px;
    
     border: 1px solid teal /* width, style, colour */
     } 
     .card{
       padding-top:20px;
     }
.add-cards{
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
justify-content: space-evenly;
padding-top:20px;
  
}
.buttons{
  display:flex;
  justify-content: center;
  margin:10px;
}

</style>
