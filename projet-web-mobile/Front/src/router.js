import Vue from 'vue'
import Router from 'vue-router'
import DashboardLayout from '@/layout/DashboardLayout'
import AuthLayout from '@/layout/AuthLayout'
Vue.use(Router);

export default new Router({
  mode: 'history',
  hash: false,
  linkExactActiveClass: 'active',
  routes: [
    {
      path: '/',
      redirect: 'login',
      component: AuthLayout,
      children: [
        {
          path: '/login',
          name: 'login',
          component: () => import('./views/Login.vue')
        },
        {
          path: '/register',
          name: 'register',
          component: () => import('./views/Register.vue')
        }
      ]
    },
    {
      path: '/',
      redirect: 'dashboard',
      component: DashboardLayout,
      children: [
        {
          path: '/dashboard',
          name: 'dashboard',
          component: () => import( './views/Dashboard.vue')
        },
        {
          path: '/sondage',
          name: 'sondage',
          component: () => import('./views/Sondage.vue')
        },
        {
          path: '/vote',
          name: 'vote',
          component: () => import('./views/Vote.vue')
        },
        {
          path: '/profile',
          name: 'profile',
          component: () => import('./views/UserProfile.vue')
        },
      ]
    },
  ]
})
