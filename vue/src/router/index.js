import Vue from "vue";
import VueRouter from "vue-router";
import App from "../App.vue";
import Home from "../views/Home.vue";
import About from "../views/About.vue";
import Callback from "../views/Callback.vue";
import Login from "../views/Login.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "",
    name: "App",
    component: App,
    children: [
      {
        path: "/",
        name: "Home",
        component: Home,
      },
      {
        path: "/about",
        name: "About",
        component: About,
      },
    ],
  },

  {
    path: "/callback",
    name: "Callback",
    component: Callback,
    meta: {
      notRequireLogin: true,
    },
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    meta: {
      notRequireLogin: true,
    },
  },
];

const router = new VueRouter({
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.matched.some((record) => record.meta.notRequireLogin)) {
    next();
    return;
  }

  if (localStorage.getItem("fakejwt") == null) {
    next("/login");
    // location.href =
    //   "https://github.com/login/oauth/authorize?client_id=2262130954437574af16";
  } else {
    next();
  }
});

export default router;
