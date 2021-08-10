<template>
  <div>
    


<!-- Write UI code here, copying from the "debug/demo area below"                             --->
<div class="container text-center">
<h1>GOOD LUCK</h1>
</div>
<div class="cardgrid">


    <div class="one"><v-btn v-on:click.prevent="previousCard">Previous Card</v-btn>
 </div>
    <div class="two">       <v-card class="mx-auto" max-width=600>
      <v-card-text>
        {{displayText}}
        </v-card-text>
    </v-card>
    
    </div>
    <div class="three"> <v-btn v-on:click.prevent="nextCard">Next Card</v-btn> </div>
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
      <v-btn v-on:click.prevent="markCorrect" label="thumbs up"><img src="../components/thumbsup.png"></v-btn>
         <v-btn v-on:click.prevent="flipCard">Flip Card</v-btn>
         <v-btn v-on:click.prevent="markIncorrect"><img src="../components/thumbsdown.png" label="thumbs down"></v-btn>
    </div>  
    </div>
    <div class="three"> {{correctAnswers[currentPosition] ? "Marked Correct" : "Marked Incorrect"}}. </div>
    <div></div>
    <div class="two button"><router-link to="/" tag="v-btn"> Home </router-link></div>
        <div></div>
</div>
<!-- <div id="slider">
  <transition-group tag="div" :name="transitionName" class="slides-group">
    <div v-if="show" :key="current" class="slide" :class="slides[current].className">
      <v-card c >
       <v-card-text>
        {{displayText}} test
        </v-card-text>
        </v-card>
    </div>
  </transition-group>
  
  <div class="btn btn-prev" aria-label="Previous slide" @click="slide(-1)">
    &#10094;
  </div>
  <div class="btn btn-next" aria-label="Next slide" @click="slide(1)">
    &#10095;
  </div>
</div> -->
     






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
div{
  color: teal;
}
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

</style>