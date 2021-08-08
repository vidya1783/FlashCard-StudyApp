<template>
  <study-session v-bind:cardList="flashcards"/>
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
        flashcards:[]
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

</style>