<template>
  <div class="flashcard">
      <h1>Create Flashcard</h1>
      <p>You can create a Flashcard here</p>
      <create-flashcard/>
      <card-list/>
     
  </div>
</template>

<script>

import CardList from '../components/CardList.vue';
import createFlashcard from '../components/CreateFlashcard.vue';





export default {
    name: 'flashcard',
    components: {
        createFlashcard,
        CardList
               
    }

};
</script>

<style>

</style>