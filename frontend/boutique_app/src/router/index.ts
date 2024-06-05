import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import RoomPage from '../views/RoomPage.vue';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    component: HomePage
  },
  {
    path: '/rooms',
    component: RoomPage
  },
  {
    path: '/about',
    component: () => import('../views/AboutPage.vue')
  },
  {
    path: '/imprint',
    component: () => import('../views/ImprintPage.vue')
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
