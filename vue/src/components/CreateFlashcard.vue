<template>
   <v-form v-model="flashcard.valid">
       <v-container>
           <v-layout>
               <v-flex xs12 md4>
                   <v-text-field v-model="flashcard.question_text" :rules="flashcard.textRules"  :counter="70" label="Question" required outlined>

                   </v-text-field>
               </v-flex>
               <v-flex xs12 md4>
                   <v-text-field v-model="flashcard.answer_text" :rules="flashcard.textRules" :counter="70" label="Answer" required outlined> 

                   </v-text-field>
               </v-flex>
               <v-flex xs12 md4>
                   <v-text-field v-model="flashcard.creator_id" :rules="flashcard.idRules" label="Creator Id" outlined>

                   </v-text-field>
                   <div class="text-center">
                       <v-btn color="blue" elevation="12" >Submit
                       </v-btn>
                   </div>
               </v-flex>
           </v-layout>
       </v-container>
   </v-form>
</template>

<script>

import flashcardService from '../services/FlashcardService.js';

export default {
    name: 'create-flashcard',
    data: () => ({
        flashcard:{
        valid:false,
        question_text: '',
        answer_text: '',
        textRules: [
            v => !!v || 'Text is required',
            v => v.length <= 70 || 'Text must be less than 70'
        ],
        creator_id: 0,
        idRules: [
            v => !!v || 'Creator ID is required',
            v => /.+@.+/.test(v) || 'Creator ID must be valid'
        ]
    }
    }),

    created() {
        flashcardService.createFlashcard()
         .then((response) => { 
            this.flashcard = response.data;
        });
    } 
}
</script>

<style>

</style>