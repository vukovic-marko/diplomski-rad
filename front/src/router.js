import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Fourth',

      component: () => import('./views/Fourth.vue')
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    },
    {
      path: '/range',
      name: 'third',

      component: () => import('./views/Third.vue')
    },
    {

      path: '/add',
          name: 'home',
        component: Home
    }
  ]
})
