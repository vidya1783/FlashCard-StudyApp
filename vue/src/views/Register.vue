<template>
<div>
 
         
    <form class="form-register" @submit.prevent="register">
     
   <h1 class="h3 mb-3 font-weight-normal"><img src="./image3.png"/> Create Account </h1>
      
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div>
      <label for="username" class="sr-only">Username : </label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
      
        required
        autofocus
      /></div>
      <div>
      <label for="password" class="sr-only">Password :  </label>
      <input
        type="text"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      /></div>
      <div>
        <label for="confirm password" class="sr-only">Confirm Password : </label>
      <input
        type="text"
        id="confirmPassword"
        class="form-control"
        placeholder= "Confirm Password"
        v-model="user.confirmPassword"
        required
      /></div>
      <div class="button">
       <v-btn  elevation="19"
  x-large type="submit" >Create Account</v-btn></div>
      <div><router-link :to="{ name: 'login' }" tag="v-btn">Have an account?</router-link>
      </div>
    
    </form>
      </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>


.form-register{
  color:black;
  display: flex;
  flex-direction: column;
  justify-content: center;
  height:670px;

}

div{
  font-size:18px;
  display:flex;
 padding-bottom: 10px;
  justify-content: center;
    padding: 25px;
    position: relative;
    background:linear-gradient(to right, rgb(202, 199, 199), #f2f5f8, white);

}
h1{
  padding-bottom: 20px;
  font-size: 45px;
  padding-top:5px;
}
input{
  border: 1px solid black;
  border-radius: 5px;
}

</style>