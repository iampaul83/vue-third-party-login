<template>
  <div class="callback">
    <h1>please wait...</h1>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Callback",
  created() {
    const code = getParameterByName("code");
    axios
      .post("/access_token", {
        code: code,
      })
      .then((response) => {
        console.log(response.data);
        localStorage.setItem("fakejwt", "123");
        // this.$router.push("/");

        // to clear ?code=xxxxxxxxx
        window.location.href = "/";
      })
      .catch((error) => {
        console.log(error);
      });
  },
};

function getParameterByName(name, url = window.location.href) {
  name = name.replace(/[[\]]/g, "\\$&");
  var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
    results = regex.exec(url);
  if (!results) return null;
  if (!results[2]) return "";
  return decodeURIComponent(results[2].replace(/\+/g, " "));
}
</script>

<style>
.callback {
  text-align: center;
  margin-top: 30px;
}
</style>
