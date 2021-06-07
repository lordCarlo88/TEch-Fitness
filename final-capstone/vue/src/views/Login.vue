<template>
  <div id="login" class="text-center">
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
        <h2>A better you starts TODAY!</h2>
        <p>
          The journey to a healthier existence starts with what you put in your
          body. This application is designed to optimize your nutritional
          choices and lead you to your weight loss goals.
        </p>
        <p>ITS FREE! register today!</p>

        <form class="form-signin" @submit.prevent="login">
          <h3 id="signin" class="h3 mb-3 font-weight-normal">Sign In Here</h3>
          <div
            class="alert alert-danger"
            role="alert"
            v-if="invalidCredentials"
          >
            Invalid username and password!
          </div>
          <div
            class="alert alert-success"
            role="alert"
            v-if="this.$route.query.registration"
          >
            Thank you for registering, please sign in.
          </div>
          <div class="fields">
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
            <router-link
              id="registerBtn"
              tag="button"
              :to="{ name: 'register' }"
              ><span>Need an account?</span></router-link
            >
            <button id="submitBtn" type="submit"><span>Log In</span></button>
          </div>
        </form>
      </section>
      <section id="right-panel"></section>
    </div>
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
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
            console.log("you are not failing!");
          }
        })
        .catch((error) => {
          const response = error.response;
          console.log("you are failing!");
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>
<style scoped>
#submitBtn {
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

#submitBtn span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

#submitBtn span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

#submitBtn:hover span {
  padding-left: 25px;
}

#submitBtn:hover span:after {
  opacity: 1;
  left: 0;
}
#registerBtn {
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

#registerBtn span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

#registerBtn span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

#registerBtn:hover span {
  padding-left: 25px;
}

#registerBtn:hover span:after {
  opacity: 1;
  left: 0;
}
#signin {
  text-align: center;
}
#body {
  margin: 0;
  padding: 0;
  min-height: 75vh;
  background: url(../img/TEch-fitnessLoginPage.jpg) left / cover no-repeat;
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

h1 {
  text-align: left;
}
h2 {
  font-family: "Kalam", cursive;
  margin: 0;
  text-align: center;
}
h3 {
  font-family: "Kalam", cursive;
  margin: 0;
  text-align: left;
}

h1 {
  font-size: 5rem;
}

h2 {
  font-size: 2rem;
  font-weight: 400;
  margin: 20px 0;
}

h3 {
  margin: 20px 0;
  padding: 0 20px;
}

header {
  background-color: #f0a35c;
}

p {
  font-family: "Oswald", sans-serif;
  font-weight: 300;
  font-size: 1.1rem;
  line-height: 2.1rem;
  padding: 0 20px;
}
.fields {
  font-family: "Oswald", sans-serif;
  font-weight: 300;
  font-size: 1.1rem;
  line-height: 2.1rem;
  padding: 0 20px;
  margin-bottom: 50 px;
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
  font-size: 4rem;
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

