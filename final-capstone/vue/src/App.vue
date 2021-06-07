<template>
  <div id="app">
    <div id="nav">
      <header>
        <div id="nav-buttons">
          <div>
            <router-link
              class="home-buttons"
              tag="button"
              v-bind:to="{ name: 'home' }"
              ><span>Home</span></router-link
            >
          </div>
          <div>
            <router-link
              class="logout-buttons"
              tag="button"
              v-bind:to="{ name: 'logout' }"
              v-if="$store.state.token != ''"
              ><span>Logout</span></router-link
            >
          </div>
        </div>
        <h1>TEch-Fitness</h1>
      </header>
    </div>
    <router-view />
  </div>
</template>
<style scoped>
h1 {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
#nav-buttons {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.home-buttons {
  border-radius: 4px;
  background-color: #8c8c8c;
  border: none;
  border-color: #3285b6;
  color: #fff;
  text-align: center;
  font-size: 28px;
  padding: 20px;
  width: 240px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

.home-buttons span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.home-buttons span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.home-buttons:hover span {
  padding-right: 25px;
}

.home-buttons:hover span:after {
  opacity: 1;
  right: 0;
}

.logout-buttons {
  border-radius: 4px;
  background-color: #8c8c8c;
  border: none;
  border-color: #3285b6;
  color: #fff;
  text-align: center;
  font-size: 28px;
  padding: 20px;
  width: 240px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

.logout-buttons span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.logout-buttons span:after {
  content: "\00AB";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

.logout-buttons:hover span {
  padding-left: 25px;
}

.logout-buttons:hover span:after {
  opacity: 1;
  left: 0;
}
</style>
