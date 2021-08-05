<template>
<div >  <h1>We are now inside the component</h1>
{{"Deck id: " + deckData.deck_id}}
<b>{{alertText}}</b>
   <v-form>
       <v-container>
           <v-layout column=true>
               <v-flex xs12 md4>
                   <v-text-field v-model="deckData.deck_name"  :counter="70" label="Deck Name" required
                   v-on:blur="determineCreateOrUpdate">
                   </v-text-field>
               </v-flex>
               <p>Test</p>
               <v-flex xs12 md4>
                   <v-text-field v-model="deckData.deck_description" :counter="70" label="Deck Description"
                    required v-on:blur="determineCreateOrUpdate">
                   </v-text-field>
               </v-flex>
               <v-flex xs12 md4>
                   <v-text-field ></v-text-field>
               </v-flex>
           </v-layout>
       </v-container>
   </v-form>

</div>

</template>

<script>
import deckService from '@/services/DeckService';

export default {
  name: 'deck-details',
  props: ['deck'],
  data(){
    return {
      deckData: {deck_id: -1, deck_name: "defaultName", deck_description: "default deck description" },
      alertText: ""
    }
  },
  created() {
    if (this.deck)
    {
    this.deckData.deck_id = this.deck.deck_id;
    this.deckData.deck_name = this.deck.deck_name;
    this.deckData.deck_description = this.deck.deck_description;
    }
  },
  methods: {
    testMethod()
    {
      this.alertMe("Jeepers!");
    },
    determineCreateOrUpdate()
    {
      if (this.deckData.deck_id==-1) {this.createCard();}
      else {this.updateCard();}
    },
    updateCard() {
      this.alertMe("Update card!");
    },
    // createCard() {},
    // successfulCreate() {},
    // failedCreate() {}
    createCard() {
      this.alertMe("Create card!");
      deckService.createDeck(this.deckData)
      .then( response =>
      {
        if (deckService.acceptableCodes.includes(response.status))
        {
          this.deckData.deck_id = response.data.deck_id;
          this.successfulCreate();
        }
      }
      ).catch(this.failedCreate());},
    successfulCreate() {this.alertMe("We created a CARD!!!");},
    failedCreate() {this.alertMe("Card not created - error.");},
    alertMe(text) {
      this.alertText = text;
    }

    }

  }

// }
</script>

<style>

</style>
