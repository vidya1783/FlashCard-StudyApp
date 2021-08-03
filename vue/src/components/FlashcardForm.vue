<template>
 <form v-on:submit.prevent="submitForm" class="flashcard-form">
  <div class="flashcard-form">
     <label for="creatorID">CreatorID:</label>
      <input id="creatorID" type="number" class="form-control" v-model="card.creatorID" autocomplete="off" />
    </div>
    <div class="form-group">
      <label for="question">Question:</label>
      <textarea id="question" class="form-control" v-model="card.question"></textarea>
    </div>
     <div class="form-group">
      <label for="answer">Answer:</label>
      <textarea id="answer" class="form-control" v-model="card.answer"></textarea>
    </div>
   <button class="btn btn-submit">Submit</button>
  </form>
</template>
<script>

import FlashcardService from  '@/services/FlashcardService';

export default {
name: 'flashcard-form',
props:{ 
    cardID: {
        type: Number,
        default: 0}
},
data(){
  return {
      flashcard: {
       creatorID: " ",
        question: " ",
        answer: " ",
      }
    }
  },
methods: {
    submitForm() {
      const newFlashcard = {
        creatorID: this.flashcard.creatorID,
        question: this.flashcard.question,
        answer: this.flashcard.answer,
      };
     
        if(this.cardID === 0){
        FlashcardService
          .addFlashcard(newFlashcard)
          .then((response) => 
          {if (response.status == 200) 
            {this.$router.push('/')}
          });
        }
      }
    }
  }
</script>
<style>
</style>