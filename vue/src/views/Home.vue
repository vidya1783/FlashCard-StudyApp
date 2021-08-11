<template>
<div>
  <div class="welcome" >
    <h1>Welcome Back</h1>
    <!--<router-link :to="{ name: 'test-deck-details-2' }">My Decks</router-link>|
    <router-link :to="{ name: 'test-flashcard-details2' }">My Flashcards</router-link>-->

   <!-- <p>This is the home page</p>-->
   <div class="picture">
   <img src=https://www.nbcot.org/-/media/NBCOT/Aspire/aspire_flashcards_icon.ashx/>
   </div> 
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
  background:linear-gradient(to right, #E9F8FF, #90B2D8, #C1E3FF);
  height: 700px;
  height:800px;
  font-size: 40px;
  display:flex;
  flex-direction: column;
  
  padding-top:20px;
}
.welcome{
  font-size:25px;
  background-color: #263472;
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