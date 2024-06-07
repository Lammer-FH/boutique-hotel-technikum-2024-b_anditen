<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-menu-button></ion-menu-button>
        </ion-buttons>
        <ion-title>Start</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">

      <div class="confirmation-container">
        <h1>Vielen Dank für Ihre Buchung!</h1>
        <p>Ihre Reservierung ist bestätigt.</p>
        <p>Check-in: 2021-12-24</p>
        <p>Check-out: 2021-12-26</p>
        <p>Zimmer: Zimmer Single</p>
        <p>Preis: 100 EUR</p>

        <RouteMap/>
      </div>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {defineComponent} from 'vue';
import RouteMap from "@/components/RouteMap.vue";
import {IonButtons, IonContent, IonHeader, IonMenuButton, IonPage, IonTitle, IonToolbar} from "@ionic/vue";

export default defineComponent({
  name: "ConfirmationView",
  components: {
    IonContent,
    IonToolbar, IonHeader, IonMenuButton, IonTitle, IonButtons,
    IonPage,
    RouteMap,
  }
});
</script>

<style scoped>
.confirmation-container {
  padding: 2rem;
}

</style>
