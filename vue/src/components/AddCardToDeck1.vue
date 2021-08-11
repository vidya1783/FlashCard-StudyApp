<template>
    <div>
        <div class="boxes">
            <div id="attachcard" class="card" v-if="!clicked">
        
                <div><span class="questionspan" > Question:</span> {{flashcard.question_text}} </div>
                <div><span class="answerspan"> Answer:</span> {{flashcard.answer_text}}</div>
    
                <button class='attachbutton' v-on:click="addCardToDeck">Add</button>
            </div>
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
                deckService.attachCardToDeck(this.deck_id, this.flashcard.flashcard_id)
                    .then(response => {
                        if(response)
                        {
                            this.$store.commit("ADD_CARD_TO_ACTIVEFLASHCARDS", this.flashcard);
                        }
                    }
                );
                this.clicked = true;   
            }
        }  
 
 }
 </script>


<style scoped>

 #attachcard{
     display:flex;
    flex-direction: column;
    justify-content:space-evenly;
     background-color:white;
     width: 200px;
     height: 200px;
     border: 2px;
     border-style: solid;
     background-color:white;
     text-align:center;
     
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
       text-align:center;
    margin: 30px; 
    

 }
 .questionspan {
     color: rgb(173, 27, 51);
     font-weight: bold;
     
 }
 .answerspan {
     color: rgb(173, 27, 51);
     font-weight: bold;
 }
.boxes{
    display:flex;
    justify-content: space-evenly;
}

</style>