<template>
  <div class="field">
    <label for="title">You have totalCalsToEat for the day</label>
    <input type="text" name="title" v-model="message.title" />
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },

  computed: {
    bmrCalcMale() {
      let bmr = 0;
      const kgWeight = this.profile.currentweight / 2.20462;
      const cmHeight = this.profile.height * 2.54;
      const age = this.profile.dateofbirth;
      if (this.profile.gender.equals("Male")) {
        return (bmr = 10 * kgWeight + 6.25 * cmHeight - 5 * age + 5);
      }
      if (this.profile.activitylevel.equals("Sedentary")) {
        return (bmr = bmr * 1.0);
      } else if (this.profile.activitylevel.equals("Lightly Active")) {
        return (bmr = bmr * 1.2);
      } else if (this.profile.activitylevel.equals("Moderately Active")) {
        return (bmr = bmr * 1.375);
      } else if (this.profile.activitylevel.equals("Very Active")) {
        return (bmr = bmr * 1.55);
      } else if (this.profile.activitylevel.equals("Athlete")) {
        return (bmr = bmr * 1.725);
      }
      this.$store.commit("SETDAILYCALORIES", bmr);
      return bmr;
    },
    bmrCalcFemale() {
      let bmr = 0;
      const kgWeight = this.profile.currentweight / 2.20462;
      const cmHeight = this.profile.height * 2.54;
      const age = this.profile.dateofbirth;
      if (this.profile.gender.equals("Female")) {
        return (bmr = 10 * kgWeight + 6.25 * cmHeight - 5 * age - 161);
      }
      if (this.profile.activitylevel.equals("Sedentary")) {
        return (bmr = bmr * 1.0);
      } else if (this.profile.activitylevel.equals("Lightly Active")) {
        return (bmr = bmr * 1.2);
      } else if (this.profile.activitylevel.equals("Moderately Active")) {
        return (bmr = bmr * 1.375);
      } else if (this.profile.activitylevel.equals("Very Active")) {
        return (bmr = bmr * 1.55);
      } else if (this.profile.activitylevel.equals("Athlete")) {
        return (bmr = bmr * 1.725);
      }
      this.$store.commit("SETDAILYCALORIES", bmr);
      return bmr;
    },
  },
};
</script>

<style>
</style>

