<template>
  <div>
    <h1>{{ $store.state.user.username }}'s Lunch History</h1>
    <div class="viewLun">
      <span>Select Date:</span>
      <VueEnglishdatepicker
        placeholder="YYYY-MM-DD"
        format="YYYY-MM-DD"
        value="2020-10-10"
        :yearSelect="true"
        :monthSelect="true"
        v-model="date"
      />
      <br /><br />
      <button id="confirmDate" v-on:click="getViewLunch">
        <span>Confirm Date</span>
      </button>
    </div>
    <section id="right-panel">
      <table id="profileTable">
        <tr>
          <td>
            <h3>Name:</h3>
          </td>
          <td v-for="food in ViewMealService.food" v-bind:key="food.id">
            {{ food.name }}
          </td>
        </tr>
        <tr>
          <td>
            <h3>Serving:</h3>
          </td>
          <td v-for="food in ViewMealService.food" v-bind:key="food.id">
            {{ food.serving }}
          </td>
        </tr>
        <tr>
          <td>
            <h3>Fat:</h3>
          </td>
          <td v-for="food in ViewMealService.food" v-bind:key="food.id">
            {{ food.fats }}
          </td>
        </tr>
        <tr>
          <td>
            <h3>Protein:</h3>
          </td>
          <td v-for="food in ViewMealService.food" v-bind:key="food.id">
            {{ food.proteins }}
          </td>
        </tr>
        <tr>
          <td>
            <h3>Carbs:</h3>
          </td>
          <td v-for="food in ViewMealService.food" v-bind:key="food.id">
            {{ food.carbs }}
          </td>
        </tr>
        <tr>
          <td>
            <h3 class="secondary-title">Cals:</h3>
          </td>
          <td v-for="food in ViewMealService.food" v-bind:key="food.id">
            {{ food.cals }}
          </td>
        </tr>
      </table>
    </section>
  </div>
</template>

<script>
import ViewMealService from "../services/ViewMealService";
import VueEnglishdatepicker from "vue-englishdatepicker";

export default {
  components: {
    VueEnglishdatepicker,
  },
  data() {
    return {
      date: "",
      ViewMealService: {
        name: "",
        serving: "",
        fats: "",
        carbs: "",
        protein: "",
        cals: "",
        date: "",
        typeofmeal: "",
      },
    };
  },
  methods: {
    getViewLunch() {
      ViewMealService.getViewLunch(this.$store.state.user.id, this.date)
        .then((response) => {
          console.log(response.data);
          this.ViewMealService = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style>
.viewLun {
  text-align: center;
}
#confirmDate {
  border-radius: 4px;
  background-color: #8c8c8c;
  border: none;
  border-color: #3285b6;
  color: #fff;
  text-align: center;
  font-size: 18px;
  padding: 10px;
  width: 180px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

#confirmDate span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

#confirmDate span:after {
  content: "\00AB";
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

#confirmDate:hover span {
  padding-right: 25px;
}

#confirmDate:hover span:after {
  opacity: 1;
  right: 0;
}
</style>