<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-menu-button></ion-menu-button>
        </ion-buttons>
        <ion-title>Impressum</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content class="ion-padding">
      <div class="imprint-container">
        <h1 class="title-item">Impressum</h1>
        <div class="info-item">
          <strong>Firmenname</strong> Hotel GmbH
        </div>
        <div class="info-item">
          <strong>Unternehmensgegenstand</strong> Hotelwesen
        </div>
        <div class="info-item">
          <strong>UID-Nummer</strong> ATU12345678
        </div>
        <div class="info-item">
          <strong>Firmenbuch</strong> 123467a
        </div>
        <div class="info-item">
          <strong>Firmenbuchgericht</strong> Wien
        </div>
        <div class="info-item">
          <strong>Firmensitz</strong> Hotelstraße 12B, 1020 Wien
        </div>
        <div class="info-item">
          <strong>Email</strong> valide-email@boutique-hotel.at
        </div>
        <div class="info-item">
          <strong>Telefon</strong> +43 321 1234567
        </div>
      </div>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonButtons, IonContent, IonHeader, IonMenuButton, IonPage, IonTitle, IonToolbar } from '@ionic/vue';
</script>

<style scoped>
.title-item {
  max-width: fit-content;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 1.1rem;
}

.imprint-container {
  padding: 1.1rem;
}

.info-item {
  margin-bottom: 1.1rem;
}

.info-item strong {
  display: block;
}

ion-title {
  font-size: 1.2rem;
}
</style>
