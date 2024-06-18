<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-menu-button></ion-menu-button>
        </ion-buttons>
        <ion-title>Über uns</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content class="ion-padding">
      <div class="about-container">
        <h1 class="title-item">Über uns</h1>
        <p class="justified-text">Wir sind drei junge & aufstrebende Hoteliers, die Großes für das Hotelwesen in der
          Stadt Wien beitragen
          wollen. Gehobene Hotelzimmer zu erschwinglichen Preisen ist unsere Devise. Wir freuen uns auf Ihren
          Besuch!</p>
      </div>

      <h2 class="title-item">Unser Team</h2>
      <ion-list>
        <ion-item>
          <ion-avatar slot="start">
            <ion-icon name="person-circle" class="avatar-icon" alt="Thomas Grem"></ion-icon>
          </ion-avatar>
          <ion-label>
            <h2>Thomas Grem</h2>
          </ion-label>
        </ion-item>
        <ion-item>
          <ion-avatar slot="start">
            <ion-icon name="person-circle" class="avatar-icon" alt="Dominik Rauch"></ion-icon>
          </ion-avatar>
          <ion-label>
            <h2>Dominik Rauch</h2>
          </ion-label>
        </ion-item>
        <ion-item>
          <ion-avatar slot="start">
            <ion-icon name="person-circle" class="avatar-icon" alt="Lukas Figuli-Mock"></ion-icon>
          </ion-avatar>
          <ion-label>
            <h2>Lukas Figuli-Mock</h2>
          </ion-label>
        </ion-item>
      </ion-list>

    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonAvatar,
  IonButtons,
  IonContent,
  IonHeader,
  IonIcon,
  IonItem,
  IonLabel,
  IonList,
  IonMenuButton,
  IonPage,
  IonTitle,
  IonToolbar
} from '@ionic/vue';

import { addIcons } from 'ionicons';
import { personCircle } from 'ionicons/icons';

addIcons({
  'person-circle': personCircle
});

</script>

<style scoped>
.justified-text {
  text-align: justify;
}

.avatar-icon {
  font-size: 3rem;
  color: var(--ion-color-primary);
}

.about-container {
  padding: 1.1rem;
}

.title-item {
  max-width: fit-content;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 1.1rem;
}
</style>
