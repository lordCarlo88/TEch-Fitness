<template>
  <div>
      <div>
        <log-breakfast />
      </div>
      <div>
        <add-breakfast/>
      </div>
  </div>
</template>

<script>
import AddBreakfast from '../components/AddBreakfast.vue';
import LogBreakfast from "../components/LogBreakfast"


export default {
  components: {
    LogBreakfast,
    AddBreakfast,
  },
};
</script>

<style>
</style>