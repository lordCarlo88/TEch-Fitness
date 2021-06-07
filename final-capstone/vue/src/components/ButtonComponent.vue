<template>
  <div>
    <button v-on:click="mealHistory()" class="btn mealHistory">
      <span> Meal History </span>
    </button>
    <div class="divider" />
    <button v-on:click="viewProfile()" class="btn updateProfile">
      <span>View Profile</span>
    </button>

    <div class="divider" />
    <button v-on:click="createProfile()" class="btn createProfile">
      <span>Create Profile</span>
    </button>

    <div class="divider" />
    <div>
      <button v-on:click="logBreakfast()" class="btn logBreakfast">
        <span>Log Breakfast</span>
      </button>
      <div class="divider" />
      <button v-on:click="logLunch()" class="btn logLunch">
        <span>Log Lunch</span>
      </button>
      <div class="divider" />
      <button v-on:click="logDinner()" class="btn logDinner">
        <span>Log Dinner</span>
      </button>
      <div class="divider" />
      <button v-on:click="logSnacks()" class="btn logSnacks">
        <span>Log Snacks</span>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    viewProfile() {
      this.$router.push({ name: "profile-page" });
    },
    createProfile() {
      this.$router.push({ name: "create-profile" });
    },
    mealHistory() {
      this.$router.push({ name: "meal-history" });
    },
    editProfile() {
      this.$router.push({ name: "edit-profile" });
    },
    logBreakfast() {
      this.$router.push({ name: "log-breakfast" });
    },
    logLunch() {
      this.$router.push({ name: "log-lunch" });
    },
    logDinner() {
      this.$router.push({ name: "log-dinner" });
    },
    logSnacks() {
      this.$router.push({ name: "log-snacks" });
    },
  },
};
</script>

<style>
h1 {
  font-size: 75px;
}
.createProfile {
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

.createProfile span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.createProfile span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

.createProfile:hover span {
  padding-left: 25px;
}

.createProfile:hover span:after {
  opacity: 1;
  left: 0;
}
.updateProfile {
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

.updateProfile span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.updateProfile span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

.updateProfile:hover span {
  padding-left: 25px;
}

.updateProfile:hover span:after {
  opacity: 1;
  left: 0;
}
.logBreakfast {
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

.logBreakfast span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.logBreakfast span:after {
  content: "\00AB";
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.logBreakfast:hover span {
  padding-right: 25px;
}

.logBreakfast:hover span:after {
  opacity: 1;
  right: 0;
}
.logLunch {
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

.logLunch span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.logLunch span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

.logLunch:hover span {
  padding-left: 25px;
}

.logLunch:hover span:after {
  opacity: 1;
  left: 0;
}
.logDinner {
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

.logDinner span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.logDinner span:after {
  content: "\00AB";
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.logDinner:hover span {
  padding-right: 25px;
}

.logDinner:hover span:after {
  opacity: 1;
  right: 0;
}
.logSnacks {
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

.logSnacks span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.logSnacks span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

.logSnacks:hover span {
  padding-left: 25px;
}

.logSnacks:hover span:after {
  opacity: 1;
  left: 0;
}
.mealHistory {
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

.mealHistory span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.mealHistory span:after {
  content: "\00AB";
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.mealHistory:hover span {
  padding-right: 25px;
}

.mealHistory:hover span:after {
  opacity: 1;
  right: 0;
}
.divider {
  width: 1px;
  height: auto;
  display: inline-block;
}
</style>


