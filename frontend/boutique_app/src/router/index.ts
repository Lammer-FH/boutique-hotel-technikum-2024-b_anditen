import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import AboutPage from '../views/AboutPage.vue';
import ImprintPage from '../views/ImprintPage.vue';
import RoomPage from '@/views/RoomPage.vue';

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
    component: AboutPage
  },
  {
    path: '/imprint',
    component: ImprintPage
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
