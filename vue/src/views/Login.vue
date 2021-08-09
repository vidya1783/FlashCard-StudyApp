<template>
<div id="login" >
 <v-spacer></v-spacer>
<div>
  <v-spacer></v-spacer>
</div>
 <div class="box">
   <h1 class="h3 mb-3 font-weight-normal" align="center">In A Flash</h1>
  
 <v-card elevation="2" shaped >
    
  
   
  <v-form class="form-signin" @submit.prevent="login" >
     <h3 class="h3 mb-3 font-weight-normal" align="center">Please Sign In</h3>
     
    <v-container>
      
      <v-row>
        <v-col
          cols="12"
          sm="6"
        >
          <v-text-field
          class="form-control"
          id="username"
            v-model="user.username"
            label="Username"
            filled
            rounded
            required
            autofocus
          ></v-text-field>
        </v-col>

        <v-col
          cols="12"
          sm="6"
        >
          <v-text-field
            class="form-control"
            id="password"
            type="password"
            v-model="user.password"
            label="Password"
            filled
            rounded
            required
            autofocus
          ></v-text-field>
          
        </v-col>
      </v-row>
      
      <div id="submit">
      <v-btn type="submit" >Sign in</v-btn></div>
      <div id="buttons">
       <!-- <v-row align="center" justify="space-around">-->
      <router-link :to="{ name: 'register' }" tag="v-btn" >Need an account?</router-link></div>
      
      
    </v-container>
  </v-form>
  </v-card>
  </div>
<div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>

</div>
  
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
 
<style>
.box {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: teal ;
  padding: 150 px;
    background-color:rgb(190, 219, 219);



 
}

.box div {
  width: auto;
  height: auto;
  
}
h1{
  
  font-size: 50px;
   padding-bottom: 50px;
}
h3{
  padding-bottom: 40px;
  font-size: 40px;
}
#submit{
  padding-bottom: 40px;
}

</style>
      