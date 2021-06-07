<template>
  <div id="historyPage">
    <h1>{{ $store.state.user.username }}</h1>
    <div class="divider" />
    <div class="mealHistoryBtns">
      <button v-on:click="viewBreakfast()" class="viewBreakfast">
        <span>View Breakfast</span>
      </button>
      <div class="divider" />
      <button v-on:click="viewLunch()" class="viewLunch">
        <span>View Lunch</span>
      </button>
      <div class="divider" />
      <button v-on:click="viewDinner()" class="viewDinner">
        <span>View Dinner</span>
      </button>
      <div class="divider" />
      <button v-on:click="viewSnacks()" class="viewSnacks">
        <span>View Snacks</span>
      </button>
    </div>
  </div>
</template>

<script>
import TrackMealService from "../services/TrackMealService";
import store from "../store/index";

export default {
  data() {
    return {
      TrackMealService: {},
    };
  },
  methods: {
    viewBreakfast() {
      this.$router.push({ name: "view-breakfast" });
    },
    viewLunch() {
      this.$router.push({ name: "view-lunch" });
    },
    viewDinner() {
      this.$router.push({ name: "view-dinner" });
    },
    viewSnacks() {
      this.$router.push({ name: "view-snacks" });
    },
  },
  created() {
    TrackMealService.getMealHistory(store.state.user.id)
      .then((response) => {
        this.profile = response;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
<style>
.mealHistoryBtns {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.viewBreakfast {
  border-radius: 4px;
  background-color: #8c8c8c;
  border: none;
  border-color: #3285b6;
  color: #fff;
  text-align: center;
  font-size: 28px;
  padding: 20px;
  width: 255px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

.viewBreakfast span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.viewBreakfast span:after {
  content: "\00AB";
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.viewBreakfast:hover span {
  padding-right: 25px;
}

.viewBreakfast:hover span:after {
  opacity: 1;
  right: 0;
}
.viewLunch {
  border-radius: 4px;
  background-color: #8c8c8c;
  border: none;
  border-color: #3285b6;
  color: #fff;
  text-align: center;
  font-size: 28px;
  padding: 20px;
  width: 255px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

.viewLunch span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.viewLunch span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

.viewLunch:hover span {
  padding-left: 25px;
}

.viewLunch:hover span:after {
  opacity: 1;
  left: 0;
}
.viewDinner {
  border-radius: 4px;
  background-color: #8c8c8c;
  border: none;
  border-color: #3285b6;
  color: #fff;
  text-align: center;
  font-size: 28px;
  padding: 20px;
  width: 255px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

.viewDinner span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.viewDinner span:after {
  content: "\00AB";
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.viewDinner:hover span {
  padding-right: 25px;
}

.viewDinner:hover span:after {
  opacity: 1;
  right: 0;
}
.viewSnacks {
  border-radius: 4px;
  background-color: #8c8c8c;
  border: none;
  border-color: #3285b6;
  color: #fff;
  text-align: center;
  font-size: 28px;
  padding: 20px;
  width: 255px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

.viewSnacks span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.viewSnacks span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

.viewSnacks:hover span {
  padding-left: 25px;
}

.viewSnacks:hover span:after {
  opacity: 1;
  left: 0;
}
</style>