<template>
  <div class="login">
    <button @click="login">Login</button>
  </div>
</template>

<script>
export default {
  name: "Login",
  methods: {
    login() {
      location.href =
        "https://github.com/login/oauth/authorize?client_id=2262130954437574af16";
    },
  },
};
</script>

<style>
.login {
  text-align: center;
  margin-top: 30px;
}
</style>
