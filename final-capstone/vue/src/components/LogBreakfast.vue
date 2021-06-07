<template>
  <div>
    <div id="body">
      <section id="left-panel">
        <h2>{{ $store.state.user.username }}'s Breakfast Log</h2>
        <button-component />

        <div class="nutritionalInfo" v-for="food in foods" v-bind:key="food.id">
          <button v-on:click="displayFood(food.fdcId)">
            {{ food.description }} -- In -- {{ food.foodCategory }} -------
            {{ food.fdcId }}
          </button>
          <br />
        </div>
      </section>
      <section id="right-panel">
        <div>
          <table id="BreakfastTable">
            <th>name</th>
            <th>QTY</th>
            <th>Fats (g)</th>
            <th>Proteins (g)</th>
            <th>Carbs (g)</th>
            <th>Cals</th>
            <tr v-for="food in foodArr" v-bind:key="food.name">
              <td>{{ food.name }}</td>
              <td>1</td>
              <td>{{ food.fats }}</td>
              <td>{{ food.proteins }}</td>
              <td>{{ food.carbs }}</td>
              <td>{{ food.cals }}</td>
            </tr>
          </table>
          <div>
            <table id="totalTable">
              <th>Total Fats</th>
              <th>Total Proteins</th>
              <th>Total Carbs</th>
              <th>Total Cals</th>
              <tr>
                <td>{{ total.fats }} g's</td>
                <td>{{ total.proteins }} g's</td>
                <td>{{ total.carbs }} g's</td>
                <td>{{ total.cals }} Cals</td>
              </tr>
            </table>
            <div class="mealbuttons">
              <button id="addMeal" v-on:click="addMeal()">
                <span>Add Meal</span>
              </button>
              <div id="search" v-if="searchDisplay">
                <input v-model="searchTerm2" />
                <button id="searchbutton" v-on:click="searchFood()">
                  <span>Search USDA</span>
                </button>
              </div>

              <button id="Submit" v-on:click="submitFunction()">
                <span>Submit Meal</span>
              </button>
            </div>
            <br />
          </div>
        </div>
      </section>
    </div>
  </div>
</template>
<script>
import TrackMealService from "../services/TrackMealService";
import store from "../store/index";
import USDAService from "../services/USDAService";
import ButtonComponent from "../components/ButtonComponent.vue";
export default {
  components: { ButtonComponent },
  data() {
    return {
      searchDisplay: false,
      TrackMealService: {},
      searchTerm2: "",
      fdcId: "",
      foods: [],
      meal_id: "",
      mealObj: {
        meal_id: "",
        user_id: "",
        totalCals: "",
        totalProtein: "",
        totalCarbs: "",
        totalFats: "",
        date: "",
        typeOfMeal: "",
      },
      foodNutrients: [],
      foodObj: {
        meal_id: "",
        name: "",
        serving: "",
        fats: "",
        proteins: "",
        carbs: "",
        cals: "",
      },
      foodArr: [],
      total: {
        fats: "",
        proteins: "",
        carbs: "",
        cals: "",
      },
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

          this.addFood();

          console.log(this.foodObj);
          this.foodArr.push(this.foodObj);
          console.log(this.foodArr[0]);

          this.foodObj = {
            meal_id: "",
            name: "",
            fats: "",
            proteins: "",
            carbs: "",
            cals: "",
          };
        }
        this.getTotal();
      });
    },
    getTotal() {
      let totalFats = 0;
      let totalProtiens = 0;
      let totalCarbs = 0;
      let totalCals = 0;

      for (let i = 0; i < this.foodArr.length; i++) {
        totalCals += this.foodArr[i].cals;
        totalProtiens += this.foodArr[i].proteins;
        totalFats += this.foodArr[i].fats;
        totalCarbs += this.foodArr[i].carbs;
        console.log(this.total);
      }
      this.total.fats = totalFats;
      this.total.proteins = totalProtiens;
      this.total.carbs = totalCarbs;
      this.total.cals = totalCals;
    },
    getMealId() {
      let id = this.$store.state.user.id;
      var typeOfMeal = "Breakfast";

      var date2 = new Date();
      var month = date2.getMonth() + 1;
      var day = date2.getDate();
      var year = date2.getFullYear();

      let monthStr = month.toString();
      monthStr = monthStr.length === 1 ? "0" + monthStr : monthStr;

      let dayStr = day.toString();
      dayStr = dayStr.length === 1 ? "0" + dayStr : dayStr;

      let yearStr = year.toString();

      let dateFinal = yearStr + "-" + monthStr + "-" + dayStr;
      console.log("something");
      console.log(dateFinal);

      console.log(
        "its working here. id:" +
          id +
          " typeOfMeal: " +
          typeOfMeal +
          "date: " +
          dateFinal
      );

      TrackMealService.getMealId(id, typeOfMeal, dateFinal).then((response) => {
        if (response.status === 200) {
          console.log("this is the latest meal id: " + response.data);
          this.meal_id = response.data;
          console.log(this.meal_id);
        }
      });
    },
    addFood() {
      console.log("Adding Food to Meal");
      this.getMealId();
      this.foodObj.meal_id = this.meal_id;
      this.foodObj.user_id = this.$store.state.user_id;
      this.foodObj.serving = 1;
      TrackMealService.addFood(this.foodObj);
      console.log(this.foodObj);
    },
    addMeal() {
      console.log("adding meal");

      this.mealObj.user_id = this.$store.state.user.id;
      this.getTotal();
      this.mealObj.totalCals = this.total.cals;
      this.mealObj.totalProtein = this.total.proteins;
      this.mealObj.totalCarbs = this.total.carbs;
      this.mealObj.totalFats = this.total.fats;
      var date2 = new Date();
      var month = date2.getMonth() + 1;
      var day = date2.getDate();
      var year = date2.getFullYear();

      let monthStr = month.toString();
      monthStr = monthStr.length === 1 ? "0" + monthStr : monthStr;

      let dayStr = day.toString();
      dayStr = dayStr.length === 1 ? "0" + dayStr : dayStr;

      let yearStr = year.toString();

      let dateFinal = yearStr + "-" + monthStr + "-" + dayStr;

      this.mealObj.date = dateFinal;
      this.mealObj.typeOfMeal = "Breakfast";
      console.log(this.mealObj);
      TrackMealService.addMeal(this.mealObj).then((response) => {
        console.log(response.data);
        (this.searchDisplay = true),
          // response.data is your meal_id.
          (this.meal_id = response.data);
      });
    },
    saveMeal() {
      console.log("saving meal");
      const newMeal = {
        user_id: this.$store.state.user.id,
        food: this.foodArr,
        servings: 1,
        calories: this.total.cals,
        protein: this.total.proteins,
        carbs: this.total.carbs,
        fat: this.total.fat,
      };
      TrackMealService.addMeal(newMeal).then((response) => {
        if (response.status === 201) {
          alert("meal added!");
        }
      });
    },
    submitFunction() {
      alert("Thanks for logging in your meal! ");
      this.$router.push("/");
    },
    created() {
      TrackMealService.getLogDinner(store.state.user.id)
        .then((response) => {
          this.profile = response;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
<style>
.mealbuttons {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

#Submit {
  border-radius: 4px;
  background-color: #8c8c8c;
  border: none;
  border-color: #3285b6;
  color: #fff;
  text-align: center;
  font-size: 15px;
  padding: 7px;
  width: 150px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

#Submit span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

#Submit span:after {
  content: "\00AB";
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

#Submit:hover span {
  padding-right: 25px;
}

#Submit:hover span:after {
  opacity: 1;
  right: 0;
}
#searchbutton {
  border-radius: 4px;
  background-color: #8c8c8c;
  border: none;
  border-color: #3285b6;
  color: #fff;
  text-align: center;
  font-size: 15px;
  padding: 8px;
  width: 150px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

#searchbutton span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

#searchbutton span:after {
  content: "\00AB";
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

#searchbutton:hover span {
  padding-right: 25px;
}

#searchbutton:hover span:after {
  opacity: 1;
  right: 0;
}

#addMeal {
  border-radius: 4px;
  background-color: #8c8c8c;
  border: none;
  border-color: #3285b6;
  color: #fff;
  text-align: center;
  font-size: 15px;
  padding: 8px;
  width: 150px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

#addMeal span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

#addMeal span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

#addMeal:hover span {
  padding-left: 25px;
}

#addMeal:hover span:after {
  opacity: 1;
  left: 0;
}

.nav {
  padding: 20px;
  justify-content: center;
  border-spacing: 20px;
}
#BreakfastTable {
  background-color: #d5ebed;
}
#totalTable {
  background-color: #d5ebed;
}
</style>