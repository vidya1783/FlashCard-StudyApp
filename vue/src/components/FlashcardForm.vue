<template>

 <div>

 <form v-on:submit.prevent="submitForm" class="flashcardForm">
  <div class="form-group">
  <!--   <label for="creatorID">CreatorID:</label>
      <input id="creatorID" type="number" class="form-control" v-model="card.creatorID" autocomplete="off" />-->
    </div>
    <div class="form-group">
      <label for="tag">tag:</label>
      <input id="tag" class="form-control" v-model="flashcard.tag" />
    </div>
    <div class="form-group">
      <label for="question">Question:</label>
      <textarea id="question" class="form-control" v-model="flashcard.question"></textarea>
    </div>
     <div class="form-group">
      <label for="answer">Answer:</label>
      <textarea id="answer" class="form-control" v-model="flashcard.answer"></textarea>
    </div>
   <button class="btn btn-submit">Submit</button>
   <button class="btn btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>
  </form>

  </div>

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
       creatorID: "",
        question: "",
        answer: "",
        tag: []
      }
    }
  },
methods: {
   submitForm() {
      const newFlashcard = {
        //creatorID: this.flashcard.creatorID,
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
.flashcardForm {
  padding: 10px;
  margin-bottom: 10px;
}
.form-group {
  margin-bottom: 10px;
  margin-top: 10px;
}
label {
  display: inline-block;
  margin-bottom: 0.5rem;
}
.form-control {
  display: block;
  width: 80%;
  height: 30px;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}
textarea.form-control {
  height: 75px;
  font-family: Arial, Helvetica, sans-serif;
}
select.form-control {
  width: 20%;
  display: inline-block;
  margin: 10px 20px 10px 10px;
}
.btn-submit {
  color: #fff;
  background-color: #0062cc;
  border-color: #005cbf;
}
.btn-cancel {
  color: #fff;
  background-color: #dc3545;
  border-color: #dc3545;
}

</style>