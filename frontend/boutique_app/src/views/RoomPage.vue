<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-menu-button></ion-menu-button>
        </ion-buttons>
        <ion-title>Zimmer</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content class="ion-padding centered-content" :fullscreen="true">
      <div class="centered-container">
        <DateRangePicker/>
        <div v-if="rooms.length">
          <ion-list>
            <ion-item v-for="room in rooms"
                      :key="room.id" lines="none">
              <RoomCard
                  :room="room"
              />
            </ion-item>
          </ion-list>

          <ion-infinite-scroll @ionInfinite="loadMoreRooms">
            <ion-infinite-scroll-content></ion-infinite-scroll-content>
          </ion-infinite-scroll>
        </div>
        <div v-else>
          <ion-list>
            <ion-item>
              <p>Loading...</p>
            </ion-item>
          </ion-list>
        </div>
      </div>
    </ion-content>
  </ion-page>
</template>

<script lang="ts" setup>
import {
  IonButtons,
  IonHeader,
  IonToolbar,
  IonContent,
  IonTitle,
  IonPage,
  IonMenuButton,
  IonList,
  IonItem,
  IonInfiniteScroll,
  IonInfiniteScrollContent,
} from '@ionic/vue';
import RoomCard from '../components/RoomCard.vue';
import Room from '../models/room';
import {useRoomStore} from '../stores/roomsStore';
import {onMounted, ref} from 'vue';
import DateRangePicker from "@/components/DateRangePicker.vue";
import {useDateStore} from "@/stores/dateStore";

const dateStore = useDateStore();

let shownRooms = 5;
const store = useRoomStore();
const rooms = ref<Room[]>([]);

const loadMoreRooms = async (event: CustomEvent<void>) => {
  shownRooms += 5;
  rooms.value = store.rooms.filter(room => room.availability || room.availability == null).slice(0, shownRooms);
  (event.target as HTMLIonInfiniteScrollElement).complete();
};

onMounted(async () => {
  await store.fetchRooms();
  rooms.value = store.rooms.filter(room => room.availability || room.availability == null).slice(0, shownRooms);
});

dateStore.$subscribe(async (mutation, state) => {
  console.log('DateStore changed', state.start, state.end);
  await store.fetchRooms(state.start, state.end);
  rooms.value = store.rooms.filter(room => room.availability || room.availability == null).slice(0, shownRooms);
  console.log('Rooms Page ', rooms.value);
});

</script>

<style scoped>
.centered-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  height: 100%;
}

.centered-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  align-self: center;
  width: 100%;
}

</style>
