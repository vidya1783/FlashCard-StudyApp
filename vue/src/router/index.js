import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from '../views/Dashboard'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Flashcard from '../views/Flashcard.vue'
import TestDeckDetails from '../views/TestDeckDetails.vue'
import TestDeckDetails2 from '../views/TestDeckDetails2.vue'
import TestFlashcardDetails from '../views/TestFlashcardDetails.vue'
import TestFlashcardDetails2 from '../views/TestFlashcardDetails2.vue'
import Study from '../views/Study.vue'
import DeckDisplay from '../views/DeckDisplay.vue'
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
      path: '/',
      name: 'Home',
      component: Dashboard,
      children:[
        {
          path: '',
          component: () => import('../views/Home')
        },
        {
          path: '/register',
          component: () => import('../views/Register')
        },
        {
          path: '/test-deck-details2',
          component: () => import('../views/TestDeckDetails2')
        },
        {
          path: '/test-flashcard-details2',
          component: () => import('../views/TestFlashcardDetails2')
        },
        {
          path: '/study',
          component: () => import('../views/Study')
        }
      ],
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
      path: "/flashcard",
      name: "flashcard",
      component: Flashcard,
      meta: {
        requiresAuth: true
      }
      },
    {
    path: "/test-deck-details",
    name: "test-deck-details",
    component: TestDeckDetails,
    meta: {
      requiresAuth: true
    }
    },
   {
    path: "/test-flashcard-details",
    name: "test-flashcard-details",
    component: TestFlashcardDetails,
    meta: {
      requiresAuth: true
    }
    },
    {
      path: "/test-deck-details2",
      name: "test-deck-details-2",
      component: TestDeckDetails2,
      meta: {
        requiresAuth: true
      }
      },
      {
        path: "/test-flashcard-details2",
        name: "test-flashcard-details2",
        component: TestFlashcardDetails2,
        meta: {
          requiresAuth: true
        }
        },
        {
        path: "/study",
        name: "study-session",
        component: Study,
        meta: {
          requiresAuth: true
        }
      },
      {
        path: `/deck-display/:id`,
        name: 'deck-display',
        component: DeckDisplay,
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
