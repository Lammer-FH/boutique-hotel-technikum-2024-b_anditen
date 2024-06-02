import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import AboutPage from '../views/AboutPage.vue';
import ImprintPage from '../views/ImprintPage.vue';
import RoomPage from '@/views/RoomPage.vue';
import GoogleMaps from "@/components/GoogleMaps.vue";

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
  },
  {
    path: '/route', // TODO remove and embed in other component
    component: GoogleMaps
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
