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
        <ion-content class="ion-padding content-centered" :fullscreen="true">
            <div class="centered-container">
                <ion-button id="open-modal" style="width: 80%;" @click="setOpen(true)">
                    Pick the Date
                </ion-button>

                <ion-modal :is-open="showPicker">

                    <ion-header>
                        <ion-toolbar>
                        <ion-title>Modal</ion-title>
                        <ion-buttons slot="end">
                            <ion-button @click="setOpen(false)">Close</ion-button>
                        </ion-buttons>
                        </ion-toolbar>
                    </ion-header>
                    <ion-content class="ion-padding">
                        <div class="centered-container">
                            <DatePicker
                                id="datetimepicker"
                                min="2024-06-01T00:00:00"
                                :start="startDate"
                                :end="endDate"
                                @update:start="(d) => (startDate = d)"
                                @update:end="(d) => (endDate = d)"
                            />
                        </div>
                    </ion-content>
                </ion-modal>

                <div v-if="rooms">
                    <RoomCard 
                    v-for="room in rooms"
                    :key="room.id"
                    :room="room"
                    />
                    <p>test</p>
                </div>
                <div v-else>
                    <p>Loading...</p>
                </div>
            </div>
        </ion-content>
    </ion-page>
</template>

<script lang="ts" setup>
import { 
    IonButtons, 
    IonButton, 
    IonModal, 
    IonHeader, 
    IonToolbar, 
    IonContent, 
    IonTitle,
    IonPage,
    IonMenuButton 
} from '@ionic/vue';
import DatePicker from '@/components/DatePicker.vue';
import RoomCard from '../components/RoomCard.vue';
import Room from '../models/room';
import { useRoomStore } from '../stores/roomsStore';
import { onMounted, ref } from 'vue';

const store = useRoomStore();
onMounted(() => {
    store.fetchRooms();
});

const rooms = store.rooms as Room[];
console.log(rooms);

const showPicker = ref(false);
const setOpen = (open: boolean) => (showPicker.value = open);

const startDate = ref<string>('2023-10-01');
const endDate = ref<string>('2023-10-23');
</script>

<style scoped>
.content-centered {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.centered-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  /* text-align: center; */
  width: 100%;
}
</style>