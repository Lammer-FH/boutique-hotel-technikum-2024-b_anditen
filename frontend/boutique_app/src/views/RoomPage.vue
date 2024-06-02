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
                        <ion-datetime 
                            ref="datepicker"
                            presentation="date" 
                            min=minDateString
                            max=maxDateString
                            :multiple="true" 
                            :value=dateRange
                            @ionChange="handleDateChange"
                        />
                    </ion-content>
                </ion-modal>
                

                <RoomCard 
                v-for="room in rooms"
                :key="room.id"
                :room="room"
                />
            </div>
        </ion-content>
    </ion-page>
</template>

<script lang="ts" setup>
// Definiere ein Interface für das CustomEvent
interface DatetimeChangeEvent extends CustomEvent {
  detail: {
    value: string[];
  };
}

import { 
    IonButtons, 
    IonButton, 
    IonModal, 
    IonHeader, 
    IonToolbar, 
    IonContent, 
    IonTitle,
    IonPage 
} from '@ionic/vue';

import RoomCard from '../components/RoomCard.vue';
import Room from '../models/room';
import { ref } from 'vue';

const showPicker = ref(false);
const setOpen = (open: boolean) => (showPicker.value = open);

const today = new Date();
const minDate = new Date(today);
minDate.setDate(today.getDate() + 1);
const minDateString = minDate.toISOString()

const maxDate = new Date(today);
maxDate.setMonth(today.getMonth() + 6);
const maxDateString = maxDate.toISOString()

console.log(minDate + " " + maxDate);

const datepicker = ref(null);
const dateRange = ref<string[]>([]);

const handleDateChange = (event: DatetimeChangeEvent) => {
    const value = event.detail
    if (value.value.length > 2) {
        value.value = value.value.slice(0, 2);
    }
};

const rooms = ref([
    new Room(
        1,
        'Zimmer 1',
        'Doppelbettzimmer',
        134,
        ["Wlan"],
        'hotel_room_beachfront.jpg',
        true
    ),
    new Room(
        2,
        'Zimmer 2',
        'Doppelbettzimmer',
        102,
        ["Wlan"],
        'hotel_room_forest.jpg',
        true
    ),
    new Room(
        3,
        'Zimmer 3',
        'Doppelbettzimmer',
        97,
        ["Wlan"],
        'hotel_room_mountain.jpg',
        true
    ),
    new Room(
        4,
        'Zimmer 4',
        'Doppelbettzimmer',
        111,
        ["Wlan"],
        'hotel_room_sea.jpg',
        true
    ),
] as Room[]);
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