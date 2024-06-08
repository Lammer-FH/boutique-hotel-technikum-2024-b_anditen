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
          <ion-item v-for="extras in room.extras">
            <ion-label>• {{ extras.name }}</ion-label>
          </ion-item>
        </ion-list>
      </div>
      <ion-button expand="block" class="booking-button">
        Buchen um {{ room.pricePerNight }}€
      </ion-button>
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
  IonButton,
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
} from '@ionic/vue';
import {ref, onMounted} from 'vue';
import {useRoute} from 'vue-router';
import {useRoomStore} from '@/stores/roomsStore';

export default {
  name: 'DetailRoomPage',
  props: {
    id: {
      type: String,
      required: true
    }
  },
  components:{
    IonButtons,
    IonButton,
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
    const room = ref(null);

    const fetchRoom = () => {
      const roomId = parseInt(route.params.id, 10);
      room.value = roomStore.getRoom(roomId);
      if (!room.value) {
        console.error(`Room with ID ${roomId} not found`);
      }
    };

    onMounted(() => {
      fetchRoom();
    });

    return {
      room
    };
  }
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
  background-color: #20c997;
  color: white;
  border-radius: 0; /* Damit der Button über die gesamte Breite geht */
  z-index: 1000; /* Damit der Button immer oben bleibt */
}

ion-list{
  margin-bottom: 4%;
}
</style>
