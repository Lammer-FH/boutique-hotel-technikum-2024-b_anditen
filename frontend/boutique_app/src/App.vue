<template>
  <ion-app>
    <Menu />

    <ion-router-outlet />
  </ion-app>
</template>

<script setup lang="ts">
import Menu from "@/components/Menu.vue";
import { IonApp, IonRouterOutlet } from '@ionic/vue';

</script>
