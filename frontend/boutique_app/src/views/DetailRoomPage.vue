<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-menu-button></ion-menu-button>
        </ion-buttons>
        <div v-if="room">
          <h1>{{ room.title }}</h1>
        </div>
        <div v-else>
          <p>Loading...</p>
        </div>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <div v-if="room">
        <ion-card-content :fullwidth="true" :fullheight="true">
          <h1> {{ room.title }}</h1>
          <h1>{{ room.description }}</h1>
          <h2 v-for="extra in room.extras">{{ extra }}</h2>
          <h2>Available: {{ room.available }}</h2>
          <ion-img :src='room.getLocalImage()' :alt="room.title"></ion-img>
          <ion-button>
            Buchen um {{ room.pricePerNight }}€
          </ion-button>
        </ion-card-content>
      </div>
      <div v-else>
        <p>Loading...</p>
      </div>
    </ion-content>
  </ion-page>

</template>

<script>
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
</style>
