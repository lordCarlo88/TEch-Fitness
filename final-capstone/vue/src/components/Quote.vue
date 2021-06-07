<template>
  <div>
    "{{ quote.text }}"
    <p>- {{ quote.author }}</p>
  </div>
</template>

<script>
export default {
  name: "Quote",
  data() {
    return {
      quote: {
        text: "",
        author: "",
      },
    };
  },
  created() {
    this.getQuote();
  },
  //return user.username
  methods: {
    getQuote() {
      const index = Math.floor(Math.random() * 1642 + 1);
      fetch("https://type.fit/api/quotes")
        .then((response) => {
          let json = response.json();
          return json;
        })
        .then((data) => {
          this.quote = data[index];
        });
    },
  },
};
</script>

<style>
</style>