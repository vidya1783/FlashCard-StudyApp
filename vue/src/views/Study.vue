<template>
  <study-session v-bind:cardList="cardsToTest"/>
</template>

<script>
import studySession from '../components/StudySession';
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
        "answer_text": "static data, you bum"}]
    }
  },
  created() {

  }
}
</script>

<style>

</style>