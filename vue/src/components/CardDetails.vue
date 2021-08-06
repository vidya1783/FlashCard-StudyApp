<template>
<div>
<v-card max-width="344">
<v-card-title>
<v-textarea v-model="flashcardData.question_text"  :counter="70" label="Question Text" required
v-on:blur="determineCreateOrUpdate" solo autogrow:true rows="2">
</v-textarea>
</v-card-title>

<v-card-text>
<v-textarea v-model="flashcardData.answer_text" :counter="70" label="Answer Text"
required v-on:blur="determineCreateOrUpdate" solo autogrow:true rows="2">
</v-textarea>
</v-card-text>
{{flashcardData.flashcard_id}}
<b>{{alertText}}</b>

      <!-- THIS CODE IS NOT WORKING <v-snackbar
        v-model="snackbar"
      >
        {{ alertText }}
  
        <template v-slot:action="{ attrs }">
          <v-btn
            color="pink"
            text
            v-bind="attrs"
            @click="snackbar = false"
          >
            Close
          </v-btn>
      </template>
      </v-snackbar> -->



</v-card>

</div>

</template>

<script>
import flashcardService from '@/services/FlashcardService';

export default {
  name: 'card-details',
  props: ['flashcard'],
  data(){
    return {
      flashcardData:{
        flashcard_id:'',
        question_text: '',
        answer_text: '',
        }
    }
  },
  created() {
    if (this.flashcard)
    {
    this.flashcardData.flashcard_id = this.flashcard.flashcard_id;
    this.flashcardData.question_text = this.flashcard.question_text;
    this.flashcardData.answer_text = this.flashcard.answer_text;
    }
  },
  methods: {
    testMethod()
    {
      this.alertMe("Jeepers!");
    },
    determineCreateOrUpdate()
    {
      this.alertMe("function called...");
      if (this.flashcardData.flashcard_id==-1) {this.createFlashcard();}
      else {this.updateCard();}
    },
    updateCard() {
      flashcardService.updateCard(this.flashcardData)
      .then( response =>
      {
        if (flashcardService.acceptableCodes.includes(response.status)&&response.data.flashcard_id!=-1)
        {
          this.successfulUpdate();
        }
      }
      ).catch(this.failedUpdate());},
    successfulUpdate()
    {
      this.alertMe("Update successful!");
    },
    failedUpdate() {
      this.alertMe("Failed to update!");
    },
    createFlashcard() {
      this.alertMe("Create card!");
      flashcardService.createFlashcard(this.flashcardData)
      .then( response =>
      {
        if (flashcardService.acceptableCodes.includes(response.status))
        {
          this.flashcardData.flashcard_id = response.data.flashcard_id;
          this.successfulCreate();
        }
      }
      ).catch(this.failedCreate());},
    successfulCreate() {this.alertMe("We created a Card!!!");},
    failedCreate() {this.alertMe("Card not created - error.");},
    alertMe(text) {
      this.alertText = text;
      // this.snackbar = true;
    }

    }

}
</script>

<style>

</style>
