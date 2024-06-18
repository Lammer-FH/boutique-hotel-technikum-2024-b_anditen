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
        <p>Check-in: {{ dateStore.start }}</p>
        <p>Check-out: {{ dateStore.end }}</p>
        <p>Zimmer: {{ room?.name ?? 'Could not load' }}</p>
        <p>Anzahl der Gäste: {{ bookingStore.numberOfGuests }}</p>
        <p>Anzahl der Nächte: {{ numberOfNights }}</p>
        <p>Preis pro Nacht: {{ room?.pricePerNight ?? 0 }}</p>
        <p>Gesamtpreis: {{ (room?.pricePerNight ?? 0) * numberOfNights }}</p>

        <h2>Ihre Daten</h2>
        <p>Name: {{ customer?.firstName }} {{ customer?.lastName }}</p>
        <p>Email: {{ customer?.email }}</p>
        <p>Telefon: {{ customer?.phoneNumber }}</p>
        <p>Geburtsdatum: {{ customer?.birthDate }}</p>
        <p>Adresse: {{ customer?.address }}</p>

        <h2>Ihr gewähltes Zimmer</h2>
        <RoomCard :room="room" v-if="room" />

        <h2>Wegbeschreibung</h2>
        <ion-segment v-model="travelMode" class="travel-mode-segment">
          <ion-segment-button value="car">
            <ion-label>Auto</ion-label>
          </ion-segment-button>
          <ion-segment-button value="train">
            <ion-label>Öffentlich</ion-label>
          </ion-segment-button>
        </ion-segment>

        <div v-if="travelMode === 'car'">
          <RouteMap travel-mode="car" :start-address="startAddress" />
        </div>
        <div v-else>
          <RouteMap travel-mode="train" :start-address="startAddress" />
        </div>
      </div>
    </ion-content>
  </ion-page>
</template>

<script lang="ts" setup>
import RoomCard from "@/components/RoomCard.vue";
import RouteMap from "@/components/RouteMap.vue";
import Room from "@/models/room";
import { useBookingStore } from "@/stores/bookingStore";
import { useDateStore } from "@/stores/dateStore";
import { useRoomStore } from "@/stores/roomsStore";
import { IonButtons, IonContent, IonHeader, IonLabel, IonMenuButton, IonPage, IonSegment, IonSegmentButton, IonTitle, IonToolbar } from "@ionic/vue";
import { computed, ref } from "vue";

const roomStore = useRoomStore();
const dateStore = useDateStore();
const bookingStore = useBookingStore();

const startAddress = ref('Albert-Schweitzer-Gasse 6, 1140 Wien');
const travelMode = ref('car');
const room = ref<Room>();
const roomId = bookingStore.roomId;
let room1 = roomStore.getRoom(Number(roomId));
room.value = room1;

const customer = ref(bookingStore.customer);

const numberOfNights = computed(() => {
  if (!dateStore.start || !dateStore.end) {
    return "N/A";
  }
  const checkIn = new Date(dateStore.start);
  const checkOut = new Date(dateStore.end);
  const timeDifference = checkOut.getTime() - checkIn.getTime();
  return timeDifference / (1000 * 3600 * 24);
});

</script>

<style scoped>
.confirmation-container {
  padding: 2rem;
}

.travel-mode-segment {
  margin-top: 1.5rem;
}
</style>
