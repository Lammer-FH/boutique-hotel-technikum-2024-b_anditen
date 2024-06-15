<template>
  <ion-page v-if="room">
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-menu-button></ion-menu-button>
        </ion-buttons>
        <ion-title>{{ room.name }}</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <div class="room-container">
        <ion-img class="room-image" :src='room.imageUrl' :alt="room.name"/>
        <div class="ion-padding room-details">
          <h1>{{ room.name }}</h1>
          <p><strong>Beschreibung:</strong> {{ room.description }}</p>
          <p><strong>Bettentyp:</strong> {{ room.type }}</p>
          <p><strong>Inkl. Inhalte:</strong></p>
          <ion-list>
            <ion-item v-for="extra in room.extras" :key="extra.name">
              <ion-label>
                <span class="material-icons icon-size">{{ mapNameToIcon(extra.name) }}</span>
                - {{ extra.name }}
              </ion-label>
            </ion-item>
          </ion-list>

        </div>

        <DateRangePicker class="ion-padding date-picker"></DateRangePicker>
        <br>
        <BookingForm :room-id="id" :button-enabled="bookingEnabled" />

      </div>
    </ion-content>
  </ion-page>
  <ion-page v-else>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-menu-button></ion-menu-button>
        </ion-buttons>
        <ion-title>Loading...</ion-title>
        <ion-progress-bar type="indeterminate"></ion-progress-bar>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <p>Loading...</p>
    </ion-content>
  </ion-page>

</template>

<script lang="ts">
import {
  IonButtons,
  IonHeader,
  IonToolbar,
  IonContent,
  IonTitle,
  IonPage,
  IonMenuButton,
  IonProgressBar,
  IonImg,
  IonList,
  IonItem,
  IonLabel,
} from '@ionic/vue';
import {ref, onMounted} from 'vue';
import {useRoute} from 'vue-router';
import {useRoomStore} from '@/stores/roomsStore';
import Room from '@/models/room';
import DateRangePicker from "@/components/DateRangePicker.vue";
import BookingForm from "@/components/BookingForm.vue";

export default {
  name: 'DetailRoomPage',
  props: {
    id: {
      type: String,
      required: true
    }
  },
  components: {
    BookingForm,
    DateRangePicker,
    IonButtons,
    IonHeader,
    IonToolbar,
    IonContent,
    IonTitle,
    IonPage,
    IonMenuButton,
    IonProgressBar,
    IonImg,
    IonList,
    IonItem,
    IonLabel
  },
  setup() {
    const route = useRoute();
    const roomStore = useRoomStore();
    const room = ref<Room | null>(null);
    let bookingEnabled = ref(false);

    const fetchRoom = () => {
      const roomId = parseInt(route.params.id[0], 10);
      room.value = roomStore.getRoom(roomId) ?? null;
    };

    onMounted(async () => {
      await roomStore.fetchRooms();
      fetchRoom();
    });

    return {
      room, bookingEnabled
    };
  },
  methods: {
    mapNameToIcon(name: string): string {
      switch (name) {
        case 'Balcony':
          return "balcony"
        case 'Sea view':
          return "waves"
        case 'Mountain view':
          return "filter_hdr"
        case 'City view':
          return "location_city"
        case 'Garden view':
          return "yard"
        case 'Pool view':
          return "pool"
        case 'Terrace':
          return "deck"
        case 'Patio':
          return "deck"
        case 'Kitchen':
          return "kitchen"
        case 'Living room':
          return "weekend"
        case 'Dining room':
          return "dinner_dining"
        case 'Fireplace':
          return "fireplace"
        case 'Hot tub':
          return "hot_tub"
        case 'Air conditioning':
          return "air"
        case 'WIFI':
          return "wifi"
        case 'Extra large toilet':
          return "wc"
        default:
          return "home"
      }
    },
  },
};
</script>

<style scoped>

.room-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.room-image {
  width: 100%;
  height: auto;
}

.room-details {
  text-align: left;
  align-self: center;
  width: 85%;
  margin: 16px 0;
}

.room-details h1 {
  font-size: 24px;
  margin: 24px 0;
}

.room-details p {
  margin: 16px 0;
}

.room-details ul {
  list-style-type: none;
  padding: 0;
}

.room-details ul li {
  margin: 8px 0;
  display: flex;
  align-items: center;
}

.booking-button {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  color: white;
  border-radius: 0;
  z-index: 1000;
}

ion-list {
  margin-bottom: 4%;
}

ion-label {
  margin-top: 8px;
  margin-bottom: 8px;
}

.icon-size {
  material-icons-font-size: 24px;
  vertical-align: middle;
}

.date-picker{
  padding-bottom: 10%;
}
</style>
