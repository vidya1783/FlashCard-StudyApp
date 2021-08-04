<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" tag="button">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
button {
  width: auto;
  padding: 10px 18px;
  
}
</style>
