<template>
  <div id="register" class="text-center">
    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>TEch-Fitness</title>
      <link
        href="https://fonts.googleapis.com/css2?family=Kalam:wght@400;700&family=Oswald:wght@300;400;600&display=swap"
        rel="stylesheet"
      />
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
      />
    </head>
    <div id="body">
      <section id="left-panel">
        <h2>Create Account!</h2>
        <form class="form-register" @submit.prevent="register">
          <div
            class="alert alert-danger"
            role="alert"
            v-if="registrationErrors"
          >
            {{ registrationErrorMsg }}
          </div>
          <div id="fields">
            <label for="username" class="sr-only">Username</label>
            <input
              type="text"
              id="username"
              class="form-control"
              placeholder="Username"
              v-model="user.username"
              required
              autofocus
            /><br />

            <label for="password" class="sr-only">Password</label>
            <input
              type="password"
              id="password"
              class="form-control"
              placeholder="Password"
              v-model="user.password"
              required
            /><br />
            <input
              type="password"
              id="confirmPassword"
              class="form-control"
              placeholder="Confirm Password"
              v-model="user.confirmPassword"
              required
            /><br />

            <button
              id="createAccBtn"
              class="btn btn-lg btn-primary btn-block"
              type="submit"
            >
              <span> Create Account </span></button
            ><br />
            <router-link
              id="backToHomeBtn"
              type="button"
              :to="{ name: 'login' }"
              ><span>Have an account?</span></router-link
            >
          </div>
        </form>
      </section>
      <section id="right-pannel"></section>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
#createAccBtn {
  border-radius: 4px;
  background-color: #8c8c8c;
  border: none;
  border-color: #3285b6;
  color: #fff;
  text-align: center;
  font-size: 15px;
  padding: 7px;
  width: 165px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

#createAccBtn span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

#createAccBtn span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

#createAccBtn:hover span {
  padding-left: 25px;
}

#createAccBtn:hover span:after {
  opacity: 1;
  left: 0;
}
#body {
  margin: 0;
  padding: 0;
  min-height: 75vh;
  background: url(../img/TEch-fitnessLoginPage.jpg) left / cover;
  display: flex;
}

#left-panel {
  background-color: #b0ebeb;
  flex-basis: 50%;
  display: flex;
  flex-direction: column;
}

ul.social-icons {
  list-style-type: none;
  margin: 20px 20px;
  padding: 0;
  display: flex;
  justify-content: space-between;
}

nav {
  flex-grow: 1;
}

#right-panel {
  display: flex;
  justify-content: center;

  flex-grow: 1;
}
#fields {
  font-family: "Oswald", sans-serif;
  margin: 0;
  text-align: center;
  padding: 20px;
}

h1,
h2 {
  font-family: "Kalam", cursive;
  margin: 0;
  text-align: center;
}

h1 {
  font-size: 5rem;
}

h2 {
  font-size: 2rem;
  font-weight: 400;
  margin: 20px 0;
}

header {
  background-color: #66c7bd;
}

p {
  font-family: "Oswald", sans-serif;
  font-weight: 300;
  font-size: 1.1rem;
  line-height: 2.1rem;
  padding: 0 20px;
}
label {
  font-family: "Oswald", sans-serif;
  font-weight: 300;
  font-size: 1.1rem;
  line-height: 2.1rem;
  padding: 0 20px;
}

nav ul {
  list-style-type: none;
  margin: 0 20px 0 20px;
  padding: 0;
}

nav ul li {
  font-family: "Oswald", sans-serif;
  font-size: 2rem;
  font-weight: 400;
  text-transform: uppercase;
  border-bottom: 1px solid #707070;
  padding-top: 30px;
}

nav a,
nav a:visited {
  text-decoration: none;
  color: #1b1b1b;
}

nav a:hover {
  color: #f0a35c;
  padding-left: 5px;
}

.fab {
  color: #3a3b00;
}

.social-icons li a:hover .fab {
  color: #f0a35c;
}

#welcome {
  background-color: #f0a35c;
  font-family: "Kalam", cursive;
  text-align: center;
  font-size: 5rem;
  padding: 20px;
  border-radius: 10px;
}

@media (max-width: 1024px) {
  h1,
  #welcome {
    font-size: 3rem;
  }

  h2 {
    font-size: 1.4rem;
  }

  .fa-3x {
    font-size: 1.5rem;
  }
}

@media (max-width: 425px) {
  #left-panel {
    flex-basis: 100%;
  }

  nav ul li {
    font-size: 1rem;
  }

  ul.social-icons {
    margin-top: 40px;
  }

  #right-panel {
    display: none;
  }
}
</style>
