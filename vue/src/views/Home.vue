<template>
  <div class="home">
    <h1>Home</h1>
    <p>Welcome Back</p>
    <div>
      <router-link :to="{ name: 'decks' }" tag="button">My Decks</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>
