<template>
  <div>

<!-- Write UI code here, copying from the "debug/demo area below"                             --->













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
    </div>
  </div>
</template>

<script>
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
      correctAnswers: [false],
      currentPosition: 0,
      useLightningRound: false, // not going to implement, but included for completeness
      shuffleTheCards: false,
      displayQuestionSide: true,
      thisAnswerCorrect: false,
      toggleText: "Mark Correct",
      currentScore: 0,
      correctPresses: 0,
      debug: true
    }
  },
  methods:
  {
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
      return this.currentCard.answer_text;
    },
    questionText() {
      return this.currentCard.question_text;
    },
    currentCard() {
      return this.testList[this.currentPosition];
    },
    testList() {
      function getRandomInt(max)
      {
        let random = Math.random();
        return Math.floor(random*(max));
      }
      
      if (!this.shuffleCards) {
        return this.cardList;
        }

      let allUsed = false;
      let shuffledCards = [];
      let cardListPositions = [];
      while (!allUsed) {
        let randomPosition = getRandomInt(this.cardList.length);
        if (cardListPositions.includes(randomPosition)) { continue; }
        else cardListPositions.push(randomPosition);
        shuffledCards.push(this.cardList[randomPosition]);
        if (cardListPositions.length==this.cardList.length) {allUsed=true;}

      }
      return shuffledCards;
    }
  },
  mounted() {
  },
  updated() {
  }
}
</script>

<style>

</style>