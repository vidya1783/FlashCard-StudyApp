<template>
<div >

<v-container align="center" >
 
<v-card max-width="344" class="d-flex justify-center py-3">
<v-card-title >
<v-textarea v-model="deckData.deck_name"   label="Deck Name" required
v-on:blur="determineCreateOrUpdate" solo autogrow:true 
>
</v-textarea>
</v-card-title>

<!--<v-card-text>
<v-textarea v-model="deckData.deck_description"  label="Deck Description"
required v-on:blur="determineCreateOrUpdate" solo autogrow:true rows="2">
</v-textarea>
</v-card-text>-->
<div class="cards">
<router-link v-bind:to="{name:'deck-display', params: {id: deckData.deck_id}}"  tag="v-btn"> Display Cards </router-link></div>
<!-- <router-link to="/display-deck/{deck.deck_id}"> Display Cards </router-link> -->

<!--
{{deckData.deck_id}}
Deck name passed: {{deck.deck_name}}
Deck id passed: {{deck.deck_id}}
{{testText}} -->
<!-- deckData.deck_description: {{deckData.deck_description}} -->
<b>{{alertText}}</b>

      <!-- THIS CODE IS NOT WORKING <v-snackbar
        v-model="snackbar"}
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

</v-container>
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
      alertText: "",
      testText: ""
     // ,
     // snackbar: false
    }
  },


  created() {
    /*
    if (this.deck)
    {
    this.testText = "This.deck truthy";
    this.deckData.deck_id = this.deck.deck_id;
    this.deckData.deck_name = this.deck.deck_name;
    this.deckData.deck_description = this.deck.deck_description;
    } */
  },
  mounted() { this.fillInText(); },
  updated() {  },
  methods: {
    fillInText() {
      this.deckData.deck_id = this.deck.deck_id;
      this.deckData.deck_name = this.deck.deck_name;
      this.deckData.deck_description = this.deck.deck_description;      
    },
    testMethod()
    {
      this.alertMe("Jeepers!");
    },
    determineCreateOrUpdate()
    {
      this.alertMe("function called...");
      if (this.deckData.deck_id==-1) {this.createDeck();}
      else {this.updateDeck();}
    },
    updateDeck() {
      deckService.updateDeck(this.deckData)
      .then( response =>
      {
        if (deckService.acceptableCodes.includes(response.status)&&response.data.deck_id!=-1)
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
    createDeck() {
      this.alertMe("Create deck!");
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
    successfulCreate() {this.alertMe("We created a Deck!!!");},
    failedCreate() {this.alertMe("Deck not created - error.");},
    alertMe(text) {
      this.alertText = text;
      // this.snackbar = true;
    }

    }

}
</script>

<style scoped>

/* .deck{
	background-color:rosybrown;
 box-sizing: border-box;
 display: flex;
  flex-wrap: wrap;
 justify-content: space-between;
 flex-direction: row;

} */
/* .deck{
	display: flex;
	flex-direction: column;
	flex-wrap: wrap;
	justify-content: space-around;
	align-items: stretch;
	align-content: flex-end;
} */
.cards{
  font-size: 20px;
}


</style>
