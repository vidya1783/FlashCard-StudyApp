<template>
<div id="body">
  <!-- This is a test -->
  <div class="check">
    <div class="box">
  <input type="checkbox" class="randomize-checkbox" v-model="randomize">
    </div>
  <label for="randomize" class="label">
      <strong><p>Randomize</p></strong> 
    </label>
  </div>
    
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
  background:linear-gradient(to right, rgb(202, 199, 199), #f2f5f8, white);
  height: 700px;
}

#randomize {
  display: none;
}

.label {
  font-size: 30px;
}

input.randomize-checkbox{
 width: 30px;
 height: 30px;
 padding-top: 50px;
}

div.check{
  display: flex;
  justify-content: center;
  position: relative;
  padding-top: 20px;
}

div.box{
  padding-top: 8px;
  padding-right: 5px;
}
</style>