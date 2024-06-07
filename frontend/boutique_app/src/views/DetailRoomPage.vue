<template>
  <ion-page v-if="room">
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-menu-button></ion-menu-button>
        </ion-buttons>
        <ion-title>{{ room.title }}</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <ion-card-content :fullwidth="true" :fullheight="true">
        <ion-img :src='room.getLocalImage()' :alt="room.title"></ion-img>

        <ion-label>{{ room.title }}</ion-label> <br>
        <ion-label>{{ room.description }}</ion-label>
        <!-- <h1> {{ room.title }}</h1>
        <h1>{{ room.description }}</h1> -->
        <!-- <h2 v-for="extra in room.extras">{{ extra }}</h2> -->
        <ion-list>
          <ion-item v-for="extras in room.extras">
            <ion-label>{{ extras }}</ion-label>
          </ion-item>
        </ion-list>
        <h2>Available: {{ room.available }}</h2>

        
      </ion-card-content>
      <div style="display: grid; grid-auto-flow: column; grid-gap: 0px;">
        <ion-button>
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
        </ion-buttons>>
        <ion-title>Loading...</ion-title>
        <ion-progress-bar type="indeterminate"></ion-progress-bar>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <p>Loading...</p>
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
