<template>
  <div class="home">
    <div id="body">
      <section id="left-panel">
        <h2 id="welcomething">Welcome, {{ $store.state.user.username }}!</h2>
        <div id="app">
          <Quote />
        </div>
        <p>
          Your recommended calorie target for the day is:
          {{ this.bmr }}
        </p>
        <div>
          <ButtonComponent />
        </div>
      </section>

      <section id="right-panel">
        <!-- <div class="col user-info">
          <div id="ember1698" class="ember-view __profile-photo__e2787">
            <div class="no-image">
              <p>No photo provided</p>
              <p>
                <a id="ember1699" href="/photos/edit" class="ember-view">
                  Upload photo
                </a>
              </p>
            </div>
          </div>
        </div><br> -->
        <div>
          <CalorieTable />
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import Quote from "@/components/Quote";
import ButtonComponent from "../components/ButtonComponent.vue";
import CalorieTable from "../components/CalorieTable";
import ProfileService from "../services/ProfileService";

export default {
  components: {
    Quote,
    CalorieTable,
    ButtonComponent,
  },
  data() {
    return {
      bmr: 0,
      profile: {
        gender: "",
        height: 0,
        dateOfBirth: "",
        activityLevel: "",
        currentWeight: 0,
        desiredWeight: 0,
      },
    };
  },
  methods: {
    bmrCalcMale(currentWeight, height, activityLevel) {
      const kgWeight = currentWeight / 2.20462;
      const cmHeight = height * 2.54;
      let age = this.getAge();
      let bmr = 0;
      bmr = 10 * kgWeight + 6.25 * cmHeight - (5 * age + 5);
      console.log("BASE BMR " + bmr);
      if (activityLevel == "Sedentary") {
        bmr = bmr * 1.0;
      } else if (activityLevel == "Lightly Active") {
        bmr = bmr * 1.2;
      } else if (activityLevel == "Moderately Active") {
        bmr = bmr * 1.375;
      } else if (activityLevel == "Very Active") {
        bmr = bmr * 1.55;
      } else if (activityLevel == "Athlete") {
        bmr = bmr * 1.725;
      }
      bmr = Math.round(bmr);
      this.$store.commit("SETDAILYCALORIES", bmr);
      this.bmr = bmr;
      console.log("THIS IS OUR BMR " + bmr);
      return bmr;
    },
    getAge() {
      const today = new Date();
      let currYear = today.getFullYear();
      let age = this.profile.dateOfBirth.substring(0, 4);
      console.log("getAge: " + currYear - age);
      return currYear - age;
    },
  },
  created() {
    ProfileService.getProfile(this.$store.state.user.id)
      .then((response) => {
        this.profile.gender = response.data.gender;
        this.profile.height = response.data.height;
        this.profile.dateOfBirth = response.data.dateOfBirth;
        this.profile.activityLevel = response.data.activityLevel;
        this.profile.currentWeight = response.data.currentWeight;
        this.profile.desiredWeight = response.data.desiredWeight;
        this.bmrCalcMale(
          this.profile.currentWeight,
          this.profile.height,
          this.profile.activityLevel
        );
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
<style>
#welcomething {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
#left-panel {
  text-align: center;
  justify-content: center;
}
</style>