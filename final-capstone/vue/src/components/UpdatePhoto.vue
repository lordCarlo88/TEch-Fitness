<template>
  <div>
    <div id="photo">
      <div v-for="item in items" v-bind:key="item">
        <div v-if="!item.image">
          <input type="file" @change="onFileChange(item, $event)" />
        </div>
        <div v-else>
          <img :src="item.image" />
          <button class="btnbtn" @click="removeImage(item)">
            Choose a different Image
          </button>
          <button class="btnbtn" @click="showAlert()">
            Set as profile picture
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          image: false,
        },
      ],
    };
  },

  methods: {
    onFileChange(item, e) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
      this.createImage(item, files[0]);
    },
    createImage(item, file) {
      var reader = new FileReader();

      reader.onload = (e) => {
        item.image = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    removeImage: function (item) {
      item.image = false;
    },
    showAlert() {
      alert("Sorry, Could not Upload Photo");
    },
  },
};
</script>

<style>
img {
  width: 20%;

  display: block;
  margin-bottom: 10px;
}
.btnbtn {
  color: #fff;
  background-color: #8c8c8c;
  border-color: #3285b6;
  margin-bottom: 10px;
}
</style>