<template>
  <div class="dashboard">
    <Sidebar/> 
    <div class="content">
      <router-view/>
    </div>
  </div>
</template>

    <!-- <decks-list/> -->
    


<script>
import Sidebar from '../components/Sidebar'
// import decksList from "../components/DecksList.vue";

export default {
  name: "Home",
 components: {
   Sidebar
 }

  }

   

</script>

<style scoped>
.dashboard{
  display: grid;
  grid-template-columns: 1fr 5fr;
  background-color: #13e6db;
  height: 100vh;
  width: 100vw;
}

.content{
  background-color: white;
  border-radius: 20px;
  margin: 6px 6px 6px 0px;
}
</style>