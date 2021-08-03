<template>
  <div>
      <h1>{{flashcard.creatorID}}</h1>
      <h2>{{flashcard.question}}</h2>
      <h2>{{flashcard.answer}}</h2>
      <router-link
      tag="button"
      :to="{name: 'EditFlashcard', params: {cardID: $route.params.cardID}}"
      class="btn editCard"
      >Edit Flashcard</router-link>


  </div>
</template>

<script>
import FlashCardService from "../services/FlashCardService";

export default {
    name: "flashcard-detail",
   
}
</script>

<style>

</style>