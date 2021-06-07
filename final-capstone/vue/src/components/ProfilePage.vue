<template>
  <div>
    <div class="col user-info"></div>
    <div id="body">
      <section id="left-panel">
        <h1>{{ $store.state.user.username }}</h1>
        <h2>{{ profile.firstName }} {{ profile.lastName }}</h2>
        <p>{{ profile.username }}</p>
        <div class="profileEditBtns">
          <router-link to="/editLogin" id="editLoginBtn" class="btn editLogin"
            ><span>Edit Login</span></router-link
          >
          <router-link
            to="/editProfile"
            id="editProfileBtn"
            class="btn editProfile"
            ><span>Edit Profile</span></router-link
          >
        </div>
      </section>
      <section id="right-panel">
        <table id="profileTable">
          <tr>
            <td>
              <h3>Gender:</h3>
            </td>
            <td>{{ profile.gender }}</td>
          </tr>
          <tr>
            <td>
              <h3>Height:</h3>
            </td>
            <td>{{ profile.height }}</td>
          </tr>
          <tr>
            <td>
              <h3>Date of Birth:</h3>
            </td>
            <td>{{ profile.dateOfBirth }}</td>
          </tr>
          <tr>
            <td>
              <h3>Activity Level:</h3>
            </td>
            <td>{{ profile.activityLevel }}</td>
          </tr>
          <tr>
            <td>
              <h3>Current Weight (lbs):</h3>
            </td>
            <td>{{ profile.currentWeight }}</td>
          </tr>
          <tr>
            <td>
              <h3 class="secondary-title">Desired Weight (lbs):</h3>
            </td>
            <td>{{ profile.desiredWeight }}</td>
          </tr>
        </table>
      </section>
    </div>
    <!-- <div v-for="profile in profiles" v-bind:key="profile"> -->
    <!-- <div>
      <h2 class="secondary-title first">Gender</h2>
      <h3>{{ profile.gender }}</h3>
      <h2 class="secondary-title">Height</h2>
      <h3>{{ profile.height }}</h3>
      <h2 class="secondary-title">Date of Birth</h2>
      <h3>{{ profile.dateOfBirth }}</h3>
      <h2 class="secondary-title">Activity Level</h2>
      <h3>{{ profile.activityLevel }}</h3>
      <h2 class="secondary-title">Current Weight (lbs)</h2>
      <h3>{{ profile.currentWeight }}</h3>
      <h2 class="secondary-title">Desired Weight (lbs)</h2>
      <h3>{{ profile.desiredWeight }}</h3>
    </div> -->
  </div>
</template>

<script>
import ProfileService from "../services/ProfileService";

export default {
  name: "wrap",

  data() {
    return {
      profile: {
        firstName: "",
        lastName: "",
        gender: "",
        height: "",
        dateOfBirth: "",
        activityLevel: "",
        currentWeight: "",
        desiredWeight: "",
      },
    };
  },
  created() {
    ProfileService.getProfile(this.$store.state.user.id)
      .then((response) => {
        this.profile.firstName = response.data.firstName;
        this.profile.lastName = response.data.lastName;
        this.profile.gender = response.data.gender;
        this.profile.height = response.data.height;
        this.profile.dateOfBirth = response.data.dateOfBirth;
        this.profile.activityLevel = response.data.activityLevel;
        this.profile.currentWeight = response.data.currentWeight;
        this.profile.desiredWeight = response.data.desiredWeight;
      })
      .catch((error) => {
        console.log(error);
      });
    console.log(this.profile);
  },
};
</script>

<style>
.profileEditBtns {
  text-align: center;
}
#editProfileBtn {
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

#editProfileBtn span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

#editProfileBtn span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

#editProfileBtn:hover span {
  padding-left: 25px;
}

#editProfileBtn:hover span:after {
  opacity: 1;
  left: 0;
}
#editLoginBtn {
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

#editLoginBtn span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

#editLoginBtn span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  left: -20px;
  transition: 0.5s;
}

#editLoginBtn:hover span {
  padding-left: 25px;
}

#editLoginBtn:hover span:after {
  opacity: 1;
  left: 0;
}
</style>