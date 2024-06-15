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
        <p>Check-in: {{ dateStore.checkInDate }}</p>
        <p>Check-out: {{ dateStore.checkOutDate }}</p>
        <p>Zimmer: {{ room?.name ?? 'Could not load' }}</p>
        <p>Anzahl der Nächte: {{ numberOfNights }}</p>
        <p>Preis: {{ (room?.pricePerNight ?? 0) * numberOfNights }}</p>

        <ion-segment v-model="travelMode">
          <ion-segment-button value="car">
            <ion-label>Car</ion-label>
          </ion-segment-button>
          <ion-segment-button value="train">
            <ion-label>Train</ion-label>
          </ion-segment-button>
        </ion-segment>

        <CarRouteMap v-if="travelMode === 'car'" :start-address="startAddress" />
        <TrainRouteMap v-else :start-address="startAddress" />
      </div>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { ref, computed } from "vue";
import TrainRouteMap from "@/components/TrainRouteMap.vue";
import { IonButtons, IonContent, IonHeader, IonMenuButton, IonPage, IonTitle, IonToolbar, IonSegment, IonSegmentButton, IonLabel } from "@ionic/vue";
import { useRoomStore } from "@/stores/roomsStore";
import { useDateStore } from "@/stores/dateStore";
import CarRouteMap from "@/components/CarRouteMap.vue";

export default {
  name: "ConfirmationView",
  components: {
    CarRouteMap,
    TrainRouteMap,
    IonContent,
    IonToolbar,
    IonHeader,
    IonMenuButton,
    IonTitle,
    IonButtons,
    IonPage,
    IonSegment,
    IonSegmentButton,
    IonLabel,
  },
  setup() {
    const startAddress = ref('Albert-Schweitzer-Gasse 6, 1140 Wien');

    const roomStore = useRoomStore();
    const dateStore = useDateStore();
    const room = ref(roomStore.getRoom(1));
    const travelMode = ref('car'); // default to car

    const numberOfNights = computed(() => {
      if (!dateStore.checkInDate || !dateStore.checkOutDate) {
        return -200;
      }
      const checkIn = new Date(dateStore.checkInDate);
      const checkOut = new Date(dateStore.checkOutDate);
      const timeDifference = checkOut.getTime() - checkIn.getTime();
      return timeDifference / (1000 * 3600 * 24);
    });

    return {
      room,
      dateStore,
      numberOfNights,
      startAddress,
      travelMode,
    };
  },
};
</script>

<style scoped>
.confirmation-container {
  padding: 2rem;
}
</style>
