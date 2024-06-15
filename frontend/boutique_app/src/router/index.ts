import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import DetailRoomPage from '../views/DetailRoomPage.vue';
import RoomPage from '@/views/RoomPage.vue';
import ConfirmationView from "@/views/ConfirmationView.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    name: 'Home',
    path: '/home',
    component: HomePage
  },
  {
    name: 'RoomPage',
    path: '/rooms',
    component: RoomPage
  },
  {
    name: 'DetailRoomPage',
    path: '/room/:id',
    component: DetailRoomPage,
    props: true
  },
  {
    name: 'About',
    path: '/about',
    component: () => import('../views/AboutPage.vue')
  },
  {
    name: 'Imprint',
    path: '/imprint',
    component: () => import('../views/ImprintPage.vue')
  },
    {
        path: '/confirmation',
        component: ConfirmationView
    },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
