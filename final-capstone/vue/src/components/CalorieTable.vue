<template>
  <div>
    <div>
      <table id="totalTable">
        <th>Total Fats</th>
        <th>Total Proteins</th>
        <th>Total Carbs</th>
        <th>Total Cals</th>
        <tr>
          <td>
            {{
              breakfasttotalObj.totalFat +
              lunchtotalObj.totalFat +
              dinnertotalObj.totalFat +
              snacktotalObj.totalFat
            }}
            g's
          </td>
          <td>
            {{
              breakfasttotalObj.totalProtein +
              lunchtotalObj.totalProtein +
              dinnertotalObj.totalProtein +
              snacktotalObj.totalProtein
            }}
            g's
          </td>
          <td>
            {{
              breakfasttotalObj.totalCarbs +
              lunchtotalObj.totalCarbs +
              dinnertotalObj.totalCarbs +
              snacktotalObj.totalCarbs
            }}
            g's
          </td>
          <td>
            {{
              breakfasttotalObj.totalCals +
              lunchtotalObj.totalCals +
              dinnertotalObj.totalCals +
              snacktotalObj.totalCals
            }}
            Cals
          </td>
        </tr>
      </table>
    </div>
    <table id="BreakfastTable">
      <th>Breakfast</th>
      <th>QTY</th>
      <th>Fats (g)</th>
      <th>Proteins (g)</th>
      <th>Carbs (g)</th>
      <th>Cals</th>
      <tr v-for="x in breakfastObj.food" v-bind:key="x.id">
        <td>{{ x.name }}</td>
        <td>1</td>
        <td>{{ x.fats }}</td>
        <td>{{ x.proteins }}</td>
        <td>{{ x.carbs }}</td>
        <td>{{ x.cals }}</td>
      </tr>
    </table>
    <table id="LunchTable">
      <th>Lunch</th>
      <th>QTY</th>
      <th>Fats (g)</th>
      <th>Proteins (g)</th>
      <th>Carbs (g)</th>
      <th>Cals</th>
      <tr v-for="x in lunchObj.food" v-bind:key="x.id">
        <td>{{ x.name }}</td>
        <td>1</td>
        <td>{{ x.fats }}</td>
        <td>{{ x.proteins }}</td>
        <td>{{ x.carbs }}</td>
        <td>{{ x.cals }}</td>
      </tr>
    </table>
    <table id="DinnerTable">
      <th>Dinner</th>
      <th>QTY</th>
      <th>Fats (g)</th>
      <th>Proteins (g)</th>
      <th>Carbs (g)</th>
      <th>Cals</th>
      <tr v-for="x in dinnerObj.food" v-bind:key="x.id">
        <td>{{ x.name }}</td>
        <td>1</td>
        <td>{{ x.fats }}</td>
        <td>{{ x.proteins }}</td>
        <td>{{ x.carbs }}</td>
        <td>{{ x.cals }}</td>
      </tr>
    </table>
    <table id="SnackTable">
      <th>Snacks</th>
      <th>QTY</th>
      <th>Fats (g)</th>
      <th>Proteins (g)</th>
      <th>Carbs (g)</th>
      <th>Cals</th>
      <tr v-for="x in snacksObj.food" v-bind:key="x.id">
        <td>{{ x.name }}</td>
        <td>1</td>
        <td>{{ x.fats }}</td>
        <td>{{ x.proteins }}</td>
        <td>{{ x.carbs }}</td>
        <td>{{ x.cals }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
import TrackMealService from "../services/TrackMealService.js";

export default {
  components: {
    // LogBreakfast,
    // LogLunch,
    // LogDinner,
    // LogSancks
  },
  data() {
    return {
      user_id: "",
      meal_id: "",
      date: "",
      rows: [],
      //allObj:[],
      breakfastObj: {},
      lunchObj: {},
      dinnerObj: {},
      snacksObj: {},
      breakfasttotalObj: {
        totalFat: 0,
        totalProtein: 0,
        totalCarbs: 0,
        totalCals: 0,
      },
      lunchtotalObj: {
        totalFat: 0,
        totalProtein: 0,
        totalCarbs: 0,
        totalCals: 0,
      },
      dinnertotalObj: {
        totalFat: 0,
        totalProtein: 0,
        totalCarbs: 0,
        totalCals: 0,
      },
      snacktotalObj: {
        totalFat: 0,
        totalProtein: 0,
        totalCarbs: 0,
        totalCals: 0,
      },
    };
  },
  methods: {
    showBreakfast() {
      this.user_id = this.$store.state.user.id;
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
      this.date = dateFinal;
      TrackMealService.trackBreakfast(this.user_id, this.date).then(
        (response) => {
          if (response.status === 200) {
            this.breakfastObj = response.data;
            // this.allObj.push(this.breakfastObj)

            let totalFat = 0;
            let totalProtein = 0;
            let totalCarbs = 0;
            let totalCals = 0;

            for (let i = 0; i < response.data.food.length; i++) {
              totalFat += parseInt(response.data.food[i].fats);
              totalProtein += parseInt(response.data.food[i].proteins);
              totalCarbs += parseInt(response.data.food[i].carbs);
              totalCals += parseInt(response.data.food[i].cals);
            }
            this.breakfasttotalObj.totalFat = totalFat;
            this.breakfasttotalObj.totalProtein = totalProtein;
            this.breakfasttotalObj.totalCarbs = totalCarbs;
            this.breakfasttotalObj.totalCals = totalCals;
            console.log(this.breakfasttotalObj);
          }
        }
      );
    },
    showLunch() {
      this.user_id = this.$store.state.user.id;
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
      this.date = dateFinal;
      console.log(this.date + " " + this.user_id);
      TrackMealService.trackLunch(this.user_id, this.date).then((response) => {
        if (response.status === 200) {
          this.lunchObj = response.data;
          // this.allObj.push(this.lunchObj)

          let totalFat = 0;
          let totalProtein = 0;
          let totalCarbs = 0;
          let totalCals = 0;
          for (let i = 0; i < response.data.food.length; i++) {
            totalFat += parseInt(response.data.food[i].fats);
            totalProtein += parseInt(response.data.food[i].proteins);
            totalCarbs += parseInt(response.data.food[i].carbs);
            totalCals += parseInt(response.data.food[i].cals);
          }
          this.lunchtotalObj.totalFat = totalFat;
          this.lunchtotalObj.totalProtein = totalProtein;
          this.lunchtotalObj.totalCarbs = totalCarbs;
          this.lunchtotalObj.totalCals = totalCals;
          console.log(this.lunchtotalObj);
        }
      });
    },
    showDinner() {
      this.user_id = this.$store.state.user.id;
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
      this.date = dateFinal;
      TrackMealService.trackDinner(this.user_id, this.date).then((response) => {
        if (response.status === 200) {
          this.dinnerObj = response.data;
          // this.allObj.push(this.dinnerObj)

          let totalFat = 0;
          let totalProtein = 0;
          let totalCarbs = 0;
          let totalCals = 0;
          for (let i = 0; i < response.data.food.length; i++) {
            totalFat += parseInt(response.data.food[i].fats);
            totalProtein += parseInt(response.data.food[i].proteins);
            totalCarbs += parseInt(response.data.food[i].carbs);
            totalCals += parseInt(response.data.food[i].cals);
          }
          this.dinnertotalObj.totalFat = totalFat;
          this.dinnertotalObj.totalProtein = totalProtein;
          this.dinnertotalObj.totalCarbs = totalCarbs;
          this.dinnertotalObj.totalCals = totalCals;
          console.log(this.dinnertotalObj);
        }
      });
    },
    showSnacks() {
      this.user_id = this.$store.state.user.id;
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
      this.date = dateFinal;
      TrackMealService.trackSnacks(this.user_id, this.date).then((response) => {
        if (response.status === 200) {
          this.snacksObj = response.data;
          // this.allObj.push(this.snacksObj)

          let totalFat = 0;
          let totalProtein = 0;
          let totalCarbs = 0;
          let totalCals = 0;

          for (let i = 0; i < response.data.food.length; i++) {
            totalFat += parseInt(response.data.food[i].fats);
            totalProtein += parseInt(response.data.food[i].proteins);
            totalCarbs += parseInt(response.data.food[i].carbs);
            totalCals += parseInt(response.data.food[i].cals);
          }

          this.snacktotalObj.totalFat = totalFat;
          this.snacktotalObj.totalProtein = totalProtein;
          this.snacktotalObj.totalCarbs = totalCarbs;
          this.snacktotalObj.totalCals = totalCals;
          console.log(this.snacktotalObj);
        }
      });
    },
    getTotal() {},
  },
  created() {
    this.showBreakfast();
    this.showLunch();
    this.showDinner();
    this.showSnacks();
  },
};
</script>

<style>
table {
  font-family: "Open Sans", sans-serif;
  width: 750px;
  border-collapse: collapse;
  border: 3px solid #636261;
  margin: 10px 10px 0 10px;
  border: 1px solid black;
}

table th {
  text-transform: uppercase;
  text-align: left;
  background: #636261;
  color: rgb(252, 252, 252);
  padding: 8px;
  min-width: 30px;
}

table td {
  text-align: left;
  padding: 8px;
  border-right: 2px solid #7d82a8;
}
table td:last-child {
  border-right: none;
}
table tbody tr:nth-child(2n) td {
  background: #d4d8f9;
}
.no-image {
  text-align: center;
  padding: 10px;
  width: 120px;
  height: 120px;
  background-color: red;
}
</style>