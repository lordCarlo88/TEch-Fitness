import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import ProfilePage from '../views/ProfilePage.vue'
import LogBreakfast from '../views/LogBreakfast.vue'
import LogLunch from '../views/LogLunch.vue'
import LogDinner from '../views/LogDinner.vue'
import LogSnacks from '../views/LogSnacks.vue'
import MealHistory from '../views/MealHistory.vue'
import PhotoEdit from '../views/UpdatePhoto.vue'
import EditProfile from '../views/EditProfile.vue'
import EditUserProfile from '../views/EditUserProfile.vue'
import ViewBreakfast from '../views/ViewBreakfast.vue'
import ViewLunch from '../views/ViewLunch.vue'
import ViewDinner from '../views/ViewDinner.vue'
import ViewSnack from '../views/ViewSnack.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/profilePage',
      name: 'profile-page',
      component: ProfilePage,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/createProfile',
      name: 'create-profile',
      component: EditProfile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/profile",
      name: "profile",
      component: ProfilePage,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/logBreakfast",
      name: "log-breakfast",
      component: LogBreakfast,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/logLunch",
      name: "log-lunch",
      component: LogLunch,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/logDinner",
      name: "log-dinner",
      component: LogDinner,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/logSnacks",
      name: "log-snacks",
      component: LogSnacks,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/mealHistory",
      name: "meal-history",
      component: MealHistory,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/photos/edit",
      name: "photo-edit",
      component: PhotoEdit,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/editProfile",
      name: "edit-profile",
      component: EditUserProfile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/viewBreakfast",
      name: "view-breakfast",
      component: ViewBreakfast,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/viewLunch",
      name: "view-lunch",
      component: ViewLunch,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/viewDinner",
      name: "view-dinner",
      component: ViewDinner,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/viewSnack",
      name: "view-snack",
      component: ViewSnack,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
