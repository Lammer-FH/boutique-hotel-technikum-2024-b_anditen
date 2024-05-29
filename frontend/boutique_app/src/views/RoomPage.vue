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

        <ion-button>
            Pick the Date
        </ion-button>

        <RoomCard 
        v-for="room in rooms"
        :key="room.id"
        :room="room"
        />
    </ion-page>
</template>

<script setup lang="ts">
import {IonPage, IonButton} from '@ionic/vue';
import RoomCard from '@/components/RoomCard.vue';
import Room from '../models/room';
import { ref } from 'vue'

const rooms = ref([
    new Room(
        1,
        'Zimmer 1',
        'Doppelbettzimmer',
        134,
        ["Wlan"],
        '../assets/hotel_room_beachfront.jpg',
        true
    ),
    new Room(
        2,
        'Zimmer 2',
        'Doppelbettzimmer',
        102,
        ["Wlan"],
        '../assets/hotel_room_forest.jpg',
        true
    ),

    new Room(
        3,
        'Zimmer 3',
        'Doppelbettzimmer',
        97,
        ["Wlan"],
        '../assets/hotel_room_mountain.jpg',
        true
    ),
] as Room[]);
</script>