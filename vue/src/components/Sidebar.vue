<template>
  <div class="sidebar">
      <div class="title">
          In a Flash
      </div>
      <div class="menu-items">
          <router-link to="/" active-class="active" exact tag="v-btn" class="side-btn">
            <div class="link-container">
              Home
              </div>
        </router-link>
         <router-link to="/test-flashcard-details2" active-class="active" exact tag="v-btn" class="side-btn">
          <div class="link-container">
              My Cards
              </div>
        </router-link>
         <router-link to="/test-deck-details2" active-class="active" exact tag="v-btn" class="side-btn">
          <div class="link-container">
              My Decks
              </div> 
              </router-link>
               <!-- <router-link to="/study" active-class="active" exact tag="v-btn" class="side-btn">
          <div class="link-container">
              Study Session
              </div>
        </router-link> -->
         <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" active-class="active" exact tag="v-btn" class="side-btn">
          <div class="link-container">
              Log Out
              </div>
        </router-link>
        <!-- <router-link :to="{ name: 'register' }"  active-class="active" exact tag="v-btn" class="side-btn">
          <div class="link-container">
              Need An Account?
              </div>
        </router-link> -->
      </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.title{
    color:white;
    font-size: 45px;
    margin-top: 10px;
}

.menu-items{
    display:flex;
    flex-direction: column;
    margin-top: 40px;
   
}

.menu-items> *{
    margin-top: 60px;
}

.side-btn:focus{
    outline: none;
}

.side-btn .active{
position: relative;
background-color: white;
color: #1b308d;
font-weight: 500;
 margin-left: 10px;
border-radius: 30px 0 0 30px;
}

/* .side-btn.active::before{
    position: absolute;
    content: "";
    top: -40;
    right: 0;
    height: 30px;
    width: 30px;
    background-color: transparent;
}

.side-btn.active::after{
    position: absolute;
    content: "";
    bottom: -40px;
    right: 0;
    height: 30px;
    width: 30px;
    background-color: transparent;
} */

.side-btn{
    border: none;
    padding: 16px 0px;
    cursor: pointer;
    font-size: 16px;
    color:black;
    background-color: transparent;
}

.side-btn.active .link-container::before{
    position: absolute;
    top: -60px;
    right: 0;
    content: "";
    height: 60px;
    width: 60px;
    border-radius: 50%;
    background-color: transparent;
}

.side-btn.active .link-container::after{
    position: absolute;
    bottom: -60px;
    right: 0;
    content: "";
    height: 60px;
    width: 60px;
    border-radius: 50%;
    background-color: transparent;
    z-index: 99;
}
</style>