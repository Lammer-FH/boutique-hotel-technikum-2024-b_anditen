<template>
  <ion-menu side="start" content-id="menu-content">
    <ion-header>
      <ion-toolbar>
        <ion-title>Menu</ion-title>
        <ion-buttons slot="start">
          <ion-menu-button></ion-menu-button>
        </ion-buttons>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <ion-list>
        <ion-menu-toggle auto-hide>
          <ion-item router-link="/home">Start</ion-item>
        </ion-menu-toggle>
        <ion-menu-toggle auto-hide>
          <ion-item router-link="/about">Über uns</ion-item>
        </ion-menu-toggle>
        <ion-menu-toggle auto-hide>
          <ion-item router-link="/imprint">Impressum</ion-item>
        </ion-menu-toggle>
      </ion-list>
    </ion-content>
  </ion-menu>

  <ion-router-outlet id="menu-content"></ion-router-outlet>
</template>

<script>
import { defineComponent } from 'vue';
import {
  IonList,
  IonMenu,
  IonContent,
  IonButtons,
  IonItem,
  IonHeader,
  IonToolbar,
  IonMenuButton,
  IonTitle,
  IonRouterOutlet,
  IonMenuToggle
} from '@ionic/vue';

export default defineComponent({
  name: 'Menu',
  components: {
    IonRouterOutlet,
    IonList,
    IonMenu,
    IonContent,
    IonButtons,
    IonItem,
    IonHeader,
    IonToolbar,
    IonMenuButton,
    IonTitle,
    IonMenuToggle
  }
});
</script>

<style scoped>
ion-item {
  cursor: pointer;
}
</style>
