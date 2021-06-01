<template>
  <div class="home">
    <h1>Hello {{ name }}</h1>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from "@/components/HelloWorld.vue";
import axios from "axios";

export default {
  name: "Home",
  data() {
    return {
      name: "",
    };
  },
  components: {},
  mounted() {
    axios
      .get("/username")
      .then((response) => {
        this.name = response.data.name;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
