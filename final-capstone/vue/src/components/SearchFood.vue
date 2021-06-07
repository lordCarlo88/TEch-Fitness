<template>
  <div>
    <div id="search">
      <input v-model="searchTerm2" />
      <button v-on:click="searchFood()">Serach USDA</button>
    </div>
    <div class="nutritionalInfo" v-for="food in foods" v-bind:key="food.id">
      <button id="foodButton" v-on:click="displayFood(food.fdcId)">
        {{ food.description }} -- In -- {{ food.foodCategory }} -------
        {{ food.fdcId }}
      </button>
    </div>
  </div>
</template>

<script>
import USDAService from "../services/USDAService";
export default {
  data() {
    return {
      TrackMealService: {},
      searchTerm2: "",
      fdcId: "",
      foods: [],
      foodNutrients: [],
      foodObj: {
        name: "",
        fats: "",
        proteins: "",
        carbs: "",
        cals: "",
      },
      foodArr: [],
    };
  },
  methods: {
    searchFood() {
      USDAService.searchFood(this.searchTerm2).then((response) => {
        if (response.status === 200) {
          this.foods = response.data.foods;
          console.log(response.data.foods);
        }
      });
    },
    getfdcId() {
      this.searchFood().then((response) => {
        if (response.status === 200) {
          this.fdcId = response.data.foods.fdcId;
          console.log(response.data.foods.fdcId);
        }
      });
    },
    displayFood(fdcId) {
      console.log("fdcId: " + fdcId);

      USDAService.searchFoodByfdcId(fdcId).then((response) => {
        if (response.status === 200) {
          this.foodNutrients = response.data.foodNutrients;

          const name = response.data.description;
          console.log("name: " + name);
          this.foodObj.name = name;

          let calorieObjectArr = this.foodNutrients.filter((x) => {
            return (
              x.nutrient.name === "Energy" && x.nutrient.unitName === "kcal"
            );
          });

          const calorie = calorieObjectArr[0].amount;
          console.log("Calories:  " + calorie);
          this.foodObj.cals = calorie;

          let proteinArr = this.foodNutrients.filter((a) => {
            return a.nutrient.name === "Protein";
          });
          const protein = proteinArr[0].amount;
          console.log("proteins:  " + protein);
          this.foodObj.proteins = protein;

          let carbohydratesArr = this.foodNutrients.filter((b) => {
            return b.nutrient.name === "Carbohydrate, by difference";
          });
          const carbohydrate = carbohydratesArr[0].amount;
          console.log("Carbs:  " + carbohydrate);
          this.foodObj.carbs = carbohydrate;

          let totalLipidsArr = this.foodNutrients.filter((c) => {
            return c.nutrient.name === "Total lipid (fat)";
          });
          const fats = totalLipidsArr[0].amount;
          console.log("fats:  " + fats);
          this.foodObj.fats = fats;

          console.log(this.foodObj);
          this.foodArr.push(this.foodObj);
          console.log(this.foodArr[0]);

          // build an object that contains the name, calories, and something...
          // put that object into an array that is defined in data.
          // your table will be a v-for against that array
        }
      });
    },
  },
};
</script>

<style scoped>
.nutritionalInfo {
   display: inline-block;
   padding: 0.3em 1.2em;
   margin: 0 0.3em 0.3em 0;
   border-radius: 2em;
   box-sizing: border-box;
   text-decoration: none;
   font-family: "Roboto", sans-serif;
   font-weight: 300;
   color: #ffffff;
   background-color: #4eb5f1;
   text-align: center;
   transition: all 0.2s;
}
.nutritionalInfo:hover:hover {
   background-color: #4095c6;
}
</style>