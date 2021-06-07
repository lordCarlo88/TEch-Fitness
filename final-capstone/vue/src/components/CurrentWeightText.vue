<template>
  <div>
    <div id="body">
      <section id="left-panel">
        <div>
          FirstName:
          <input id="profile-firstname" v-model="profile.firstname" />
          <br /><br />
          LastName: <input id="profile-lastname" v-model="profile.lastname" />
          <br /><br />
          <span>Date Of Birth:</span>
          <VueEnglishdatepicker
            placeholder="YYYY-MM-DD"
            format="YYYY-MM-DD"
            value="2020-10-10"
            :yearSelect="true"
            :monthSelect="true"
            v-model="profile.dateofbirth"
          />
          <br /><br />
          <span>Gender: </span>
          <select id="profile-gender" v-model="profile.gender">
            <option disabled value="">Please select an option</option>
            <option id="male">Male</option>
            <option id="female">Female</option>
          </select>
          <br /><br />
          <span>Activity Level: </span>
          <select id="profile-al" v-model="profile.activitylevel">
            <option disabled value="">Please select an option</option>
            <option id="sedentary">Sedentary</option>
            <option id="lightactive">Lightly Active</option>
            <option id="moderateactive">Moderately Active</option>
            <option id="veryactive">Very Active</option>
            <option id="athlete">Athlete</option>
          </select>
          <br />
          <br />
          Current Weight:
          <input
            id="profile-currentweight"
            v-model="profile.currentweight"
            placeholder="Enter weight in pounds"
          />
          <br /><br />
          Desired Weight:
          <input
            id="profile-desiredweight"
            v-model="profile.desiredweight"
            placeholder="Enter weight in pounds"
          />
          <br /><br />
          Height:
          <input
            id="profile-height"
            v-model="profile.height"
            placeholder="Enter height in inches"
          />
          <br /><br />
          <button v-on:click="saveProfile" id="save-add-btn">
            Save Profile!
          </button>
        </div>
      </section>
      <section id="right-panel">
        <table id="profileTable">
          <tr>
            <td>
              <h3 class="secondary-title first">Gender:</h3>
            </td>
            <td></td>
          </tr>
          <tr>
            <td>
              <h3 class="secondary-title">Height:</h3>
            </td>
            <td></td>
          </tr>
          <tr>
            <td>
              <h3 class="secondary-title">Date of Birth:</h3>
            </td>
            <td></td>
          </tr>
          <tr>
            <td>
              <h3 class="secondary-title">Activity Level:</h3>
            </td>
            <td></td>
          </tr>
          <tr>
            <td>
              <h3 class="secondary-title">Current Weight (lbs):</h3>
            </td>
            <td></td>
          </tr>
          <tr>
            <td>
              <h3 class="secondary-title">Desired Weight (lbs):</h3>
            </td>
            <td></td>
          </tr>
        </table>
      </section>
    </div>
  </div>
</template>
<script>
import VueEnglishdatepicker from "vue-englishdatepicker";
import ProfileService from "../services/ProfileService";

export default {
  components: {
    VueEnglishdatepicker,
  },
  data() {
    return {
      checkProfile: {},
      profile: {
        gender: "",
        height: "",
        dateofbirth: "",
        activityLevel: "",
        currentWeight: "",
        desiredWeight: "",
        firstName: "",
        lastName: "",
        profilePicture: "",
      },
      profileImage: "",
    };
  },
  methods: {
    findIfUserExists() {},
    saveProfile() {
      // check if user exists:

      console.log("saving profile");
      const newProfile = {
        user_id: this.$store.state.user.id,
        height: this.profile.height,
        dateOfBirth: this.profile.dateofbirth,
        gender: this.profile.gender,
        activityLevel: this.profile.activitylevel,
        currentWeight: this.profile.currentweight,
        desiredWeight: this.profile.desiredweight,
        firstName: this.profile.firstname,
        lastName: this.profile.lastname,
        profilePicture: this.profile.profilepicture,
      };
      //let existedProfile = this.findIfUserExists();

      if (newProfile.gender == "Female") {
        this.bmrCalcFemale();
      } else {
        this.bmrCalcMale();
      }

      ProfileService.create(newProfile)
        .then((response) => {
          if (response.status === 200) {
            alert("profile added!");
            this.$router.push({ name: "profile" });
          }
        })
        .catch((err) => {
          window.alert("profile already exists");
          console.error(err + " problem adding profile!");
        })
        .catch((err) => {
          console.error(err + " no picture!");
        });
    },
    bmrCalcMale() {
      let bmr = 0;
      const kgWeight = this.profile.currentweight / 2.20462;
      const cmHeight = this.profile.height * 2.54;
      let age = this.getAge;
      if (this.profile.gender == "Male") {
        bmr = 10 * kgWeight + 6.25 * cmHeight - 5 * age + 5;
      }
      if (this.profile.activitylevel == "Sedentary") {
        bmr = bmr * 1.0;
        this.$store.commit("SETDAILYCALORIES", bmr);
        return bmr.toFixed(1);
      } else if (this.profile.activitylevel == "Lightly Active") {
        bmr = bmr * (1.2).toFixed(1);
        this.$store.commit("SETDAILYCALORIES", bmr);
        return bmr;
      } else if (this.profile.activitylevel == "Moderately Active") {
        bmr = bmr * (1.375).toFixed(0);
        this.$store.commit("SETDAILYCALORIES", bmr);
        return bmr;
      } else if (this.profile.activitylevel == "Very Active") {
        bmr = bmr * (1.55).toFixed(0);
        this.$store.commit("SETDAILYCALORIES", bmr);
        return bmr;
      } else if (this.profile.activitylevel == "Athlete") {
        bmr = bmr * (1.725).toFixed(0);
        this.$store.commit("SETDAILYCALORIES", bmr);
        return bmr;
      }
      console.log(bmr).toFixed(0);

      return bmr;
    },
    bmrCalcFemale() {
      let bmr = 0;
      const kgWeight = this.profile.currentweight / 2.20462;
      const cmHeight = this.profile.height * 2.54;
      let age = this.getAge;
      if (this.profile.gender == "Female") {
        return (bmr = 10 * kgWeight + 6.25 * cmHeight - 5 * age - 161);
      }
      if (this.profile.activitylevel == "Sedentary") {
        bmr = bmr * 1.0;
        this.$store.commit("SETDAILYCALORIES", bmr);
        return bmr;
      } else if (this.profile.activitylevel == "Lightly Active") {
        bmr = bmr * 1.2;
        this.$store.commit("SETDAILYCALORIES", bmr);
        return bmr;
      } else if (this.profile.activitylevel == "Moderately Active") {
        bmr = bmr * 1.375;
        this.$store.commit("SETDAILYCALORIES", bmr);
        return bmr;
      } else if (this.profile.activitylevel == "Very Active") {
        bmr = bmr * 1.55;
        this.$store.commit("SETDAILYCALORIES", bmr);
        return bmr;
      } else if (this.profile.activitylevel == "Athlete") {
        bmr = bmr * 1.725;
        this.$store.commit("SETDAILYCALORIES", bmr);
        return bmr;
      }
      console.log(bmr);

      return bmr;
    },
  },
  computed: {
    getAge() {
      const today = new Date();
      let currYear = today.getFullYear();
      let age = this.profile.dateofbirth.substring(0, 4);
      console.log(currYear - age);
      return currYear - age;
    },
  },
};
</script>
<style>
#profileTable {
  background-color: #d5ebed;
}
</style>