<template>
<div id="body">
  <!-- This is a test -->
  <input type="checkbox" id="randomize-checkbox" v-model="randomize">
    <label for="randomize">
      Randomize 
    </label>
    <div id="randomize">
      {{randomize}}
    </div>
  <study-session v-bind:cardList="flashcards" v-bind:shuffleCards="randomize"/>
</div>
  
</template>

<script>
import studySession from '../components/StudySession';
import deckService from '../services/DeckService';
export default {
  name: 'study',
  components: {
    studySession },
  data() {
    return {
      cardsToTest: 
        [{
        "flashcard_id": 1002,
        "creator_id": 100,
        "question_text": "picasso",
        "answer_text": "static data, you bum"},
        {
        "flashcard_id": 1003,
        "creator_id": 100,
        "question_text": "rodin",
        "answer_text": "static data, you bum"},
        {
        "flashcard_id": 1004,
        "creator_id": 100,
        "question_text": "mnesicles",
        "answer_text": "static data, you bum"}],
        flashcards:[],
        randomize: false
    }
  },
  created() {
    deckService.getFlashcardsByDeckId(this.$route.params.id)
         .then((response) => { 
            this.flashcards = response.data;});
}
}
</script>

<style>
#body {
  background:linear-gradient(to right, rgb(236, 233, 233), #f2f5f8, white);
  height: 700px;
}

#randomize {
  display: none;
}
</style>