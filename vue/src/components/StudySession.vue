<template>
  <div id="body">
    <div id="popup">
    <!-- <v-dialog
      v-model="dialog"
      width="500"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="red lighten-2"
          dark
          v-bind="attrs"
          v-on="on"
        >
          FINISH
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          Privacy Policy
        </v-card-title>

        <v-card-text>
         <P>Great attempt! Keep practicing!</P>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="dialog = false"
          >
            I accept
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog> -->
  </div>


<!-- Write UI code here, copying from the "debug/demo area below"                             --->
<div class="container text-center">
<h1>GOOD LUCK</h1>
</div>
<div class="cardgrid">


    <div class="one">
      <v-btn v-on:click.prevent="previousCard">
        <span>&#8678;</span>
      </v-btn>
 </div>
    <div class="two">       
      <v-card class="mx-auto" width=400>
      <v-card-text>
        {{displayText}}
        </v-card-text>
      </v-card>
    
  </div>
    <div class="three"> 
      <v-btn v-on:click.prevent="nextCard">
       <span>&#8680;</span>
      </v-btn> 
    </div>
    <div class="one">
      <ul style="list-style-type:none;">
        <li> {{userCardNumber}} of 
    {{lengthOfTest}}. </li>
    <li>.           .      . </li>
    <li> Current score: {{currentScore}}. </li>
      </ul></div>
    <div class="two"> 
    <div>
      <!--
       <v-btn class="ma-2" text icon color="blue lighten-2">
          <v-icon>mdi-thumb-up</v-icon>
        </v-btn>
  
        <v-btn class="ma-2" text icon color="red lighten-2">
          <v-icon>mdi-thumb-down</v-icon>
        </v-btn> -->
      <v-btn id="correct" v-on:click.prevent="markCorrect" label="thumbs up">
        <span>&#10003;</span>
        <!-- <img src="../components/thumbsup.png"> -->
      </v-btn>
      <v-btn v-on:click.prevent="flipCard">
        <span>&#8634;</span>
      </v-btn>
      <v-btn id="incorrect" v-on:click.prevent="markIncorrect">
        <span >&#10008;</span>
          <!-- <img src="../components/thumbsdown.png" label="thumbs down"> -->
      </v-btn>
    </div>  
    </div>
  <div class="three"> <v-card>  {{correctAnswers[currentPosition] ? "Marked Correct" : "Marked Incorrect"}}. </v-card></div>
  
    <div></div>
    <div class="two button">
      <router-link to="/" tag="v-btn"> 
      <i class="material-icons">home</i>
      </router-link>
      </div>
        <div>
         
        </div>
</div>

<!-- Debug/Demo Area
  
  
                Please do not change data access code below this line.
                 The "ugly" debug code can be hidden by changing debug to equal true.

to create a UI, copy and paste code and ensure that it works:
1) It displays in the browser
2) No "red" errors appear in the Vue console
 -->
<div v-if="debug">
      We're in the study session.

    Card position {{currentPosition}}
    of {{lengthOfTest - 1}}.

    In user terms, this is {{userCardNumber}} of 
    {{lengthOfTest}}.

    <v-card class="mx-auto" max-width=600>
      <v-card-text>
        {{displayText}}
        </v-card-text>
    </v-card>
    <v-btn v-on:click.prevent="previousCard">Previous Card</v-btn>
    <v-btn v-on:click.prevent="nextCard">Next Card</v-btn>
    <v-btn v-on:click.prevent="flipCard">Flip Card</v-btn>
    <v-btn v-on:click.prevent="toggleCorrect">{{toggleText}}</v-btn>
    Correct from array: {{correctAnswers[currentPosition] ? true : false}}
    Current score: {{currentScore}}
    <div class="button"><router-link to="/" tag="v-btn"> Home </router-link></div>
    </div>
  </div>
</template>

<script>
import deckService from '../services/DeckService';


export default {
  name: 'study-session',
  props: {
    cardList: Array,
    lightningRound: Boolean,
    shuffleCards: Boolean
  },
  currentPosition: 0,
//  dialog: false,
 

  data() {
    return {
     
      flashcards: undefined,
      correctAnswers: [false],
      currentPosition: 0,
      useLightningRound: false, // not going to implement, but included for completeness
      displayQuestionSide: true,
      thisAnswerCorrect: false,
      toggleText: "Mark Correct",
      currentScore: 0,
      correctPresses: 0,
      debug: false
    }
  },
  methods:
  {
    resetDefaults() {
      this.correctAnswers = [false];
      this.currentPosition = 0;
      this.currentScore = 0;
      this.thisAnswerCorrect = false;
      this.toggleText = "Mark Correct";
    },
    setCardList(cardList) {
      this.flashcards = cardList;
    },
    initializeCardList() {
      this.flashcards = this.cardList;
      if (this.cardList===undefined||this.cardList.length==0) {this.pullDeckFromServer();}
    },
    pullDeckFromServer() {
      let deckId = this.$route.params.id;
      let instance = this;
      deckService.getFlashcardsByDeckId(deckId).then( (response) => {
        instance.setCardList(response.data);
        }).catch(error => console.log(error));
    },
    markCorrect() {
      if (this.toggleText=="Mark Correct") { this.toggleCorrect(); }
      else { return; }
    },
    markIncorrect() {
      if (this.toggleText=="Mark Incorrect") {this.toggleCorrect();}
      else { return; }
    },
    finishTest() {
      // implement

    },
    flipCard() {
      this.displayQuestionSide = !this.displayQuestionSide;
    },
    setToggleCorrectTextAccordingToExisting(position)
    {
      if (this.correctAnswers[position]==true)
      {this.toggleText="Mark Incorrect";}
      else
      {this.toggleText="Mark Correct";}
    },
    nextCard() { 
      let nextPosition = this.currentPosition + 1;
      if ((nextPosition)==
      this.testList.length)
      {this.finishTest();}
      else 
      { this.currentPosition = nextPosition;
      this.setToggleCorrectTextAccordingToExisting(nextPosition);
       }
      },
    previousCard() {
      if (this.currentPosition>0)
      {
        let nextPosition = this.currentPosition - 1;
        this.currentPosition = nextPosition;
        this.setToggleCorrectTextAccordingToExisting(nextPosition);
      }
    },
    toggleCorrect()
    {
      this.thisAnswerCorrect = !this.thisAnswerCorrect;
      this.correctAnswers[this.currentPosition] = 
      !this.correctAnswers[this.currentPosition];
      this.toggleText = (this.toggleText=="Mark Incorrect") ? 
      "Mark Correct" : "Mark Incorrect";
      this.correctPresses += 1;
    }
    },
  watch: {
     correctPresses: {
       handler() {
         let correctFiltered = this.correctAnswers.filter(x => x==true);
         let correctCount = correctFiltered.length;
         this.currentScore = correctCount;
       }
     },
     shuffleCards: {
       handler() {
         this.resetDefaults();
       }
     }

  },
  computed: 
  {
    lengthOfTest() {
      return this.testList.length;
    },
    userCardNumber() {
      return this.currentPosition + 1;
    },
    displayText() {
      if (this.displayQuestionSide) return this.questionText;
      return this.answerText;
    },
    answerText() {
      if (this.currentCard===undefined) {return "loading...";}
      return this.currentCard.answer_text;
    },
    questionText() {
      if (this.currentCard===undefined) {return "loading...";}
      return this.currentCard.question_text;
    },
    currentCard() {
     if (this.flashcards===undefined)
     { return {question_text:"Dummy question", answer_text: "dummy answer"}; }
      return this.testList[this.currentPosition];
    },
    testList() {
      let sourceCards = this.flashcards;

      function getRandomInt(max)
      {
        let random = Math.random();
        return Math.floor(random*(max));
      }
      
      if (!this.shuffleCards) {
        return sourceCards;
        }

      let allUsed = false;
      let shuffledCards = [];
      let cardListPositions = [];
      while (!allUsed) {
        let randomPosition = getRandomInt(sourceCards.length);
        if (cardListPositions.includes(randomPosition)) { continue; }
        else cardListPositions.push(randomPosition);
        shuffledCards.push(this.cardList[randomPosition]);
        if (cardListPositions.length==sourceCards.length) {allUsed=true;}

      }
      return shuffledCards;
    }
  },
  created() {
    this.initializeCardList();
  },
  mounted() {
  },
  updated() {
  },

  
}
</script>

<style scoped>

h1{
  font-size: 60px;

}
.container text-center{
  display: flex;
  flex-direction: column;
}

.cardgrid {
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  grid-template-rows: 1fr 1fr;
  row-gap: 50px;
  grid-gap:50px;
  justify-items: end center start;
  align-items: center center center;
  
}

.cardgrid .one {
  justify-self: end
}

.cardgrid .two {
  justify-self: center;
}

.cardgrid .three {
  justify-self: start
}

#incorrect {
  background-color: red;
}

#correct {
  background-color: green;
}
h1{
  font-size:50px;
  padding-bottom:40px;
}


</style>