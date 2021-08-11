<template>
<div>
  <div class="welcome" >
    <h1>Welcome Back</h1>
    <!--<router-link :to="{ name: 'test-deck-details-2' }">My Decks</router-link>|
    <router-link :to="{ name: 'test-flashcard-details2' }">My Flashcards</router-link>-->

   <!-- <p>This is the home page</p>-->
    <!-- <decks-list/> -->
  </div>
 
   <div class="home" >
  <div id="cf">
  <img class="bottom" src="./image1.jpg"/>
  <img class="top" src="./image2.jpg"/>
</div></div>
    <!--<decks-list/>-->
</div>
</template>

<script>
// import decksList from "../components/DecksList.vue";

export default {
  name: "home",
  components: {
    // decksList 
  }
};
</script>

<style scoped >
.home{
  background:linear-gradient(to right, rgb(236, 233, 233), #f2f5f8, white);
  height: 700px;
  height:800px;
  font-size: 40px;
  display:flex;
  flex-direction: column;
  
  padding-top:20px;
}
.welcome{
  font-size:25px;
   background:linear-gradient(45deg,black, white);
  color:white;
}
#cf {
  position:relative;
  height:181px;
  width:350px;
  
}

#cf img {
  position:absolute;
  left:10;
  -webkit-transition: opacity 2s ease-in-out;
  -moz-transition: opacity 2s ease-in-out;
  -o-transition: opacity 2s ease-in-out;
  transition: opacity 2s ease-in-out;
  padding-left: 70px;
}

#cf img.top:hover {
  opacity:0;
}

</style>