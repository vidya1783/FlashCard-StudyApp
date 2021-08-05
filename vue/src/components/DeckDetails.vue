<template>
<div >  <h1>We are now inside the component</h1>
{{ deck.deck_name }}
this components' own data 
{{detailsData.deck_description}}
   <v-form>
       <v-container>
           <v-layout>
               <v-flex xs12 md4>
                   <v-text-field v-model="detailsData.deck_name"  :counter="70" label="Deck Name" required>
                   </v-text-field>
               </v-flex>
               <v-flex xs12 md4>
                   <v-text-field v-model="detailsData.deck_description" :counter="70" label="Deck Description" required>
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


export default {
  name: 'deck-details',
  props: ['deck'],
  data(){
    return {
      detailsData: {deck_name: "defaultName", deck_description: "default deck description" } 
    }
  },
  created() {
    this.detailsData.deck_name = this.deck.deck_name;
    this.detailsData.deck_description = this.deck.deck_description;
  },

}
</script>

<style>

</style>
