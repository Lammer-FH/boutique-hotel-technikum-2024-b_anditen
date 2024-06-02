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

        <ion-content class="scroll-content">
            <ion-button id="open-modal">
                Pick the Date
            </ion-button>

            <ion-modal ref="modal" trigger="open-modal" @willDismiss="onWillDismiss" >
                <ion-header>
                    <ion-toolbar>
                    <ion-buttons slot="start">
                        <ion-button @click="cancel()">Abbrechen</ion-button>
                    </ion-buttons>
                    <ion-title>Bitte Datum wählen</ion-title>
                    <ion-buttons slot="end">
                        <ion-button :strong="true" @click="confirm()">Bestätigen</ion-button>
                    </ion-buttons>
                    </ion-toolbar>
                </ion-header>
                <ion-content class="ion-padding">
                    <ion-item>
                        <!-- <DatePicker /> -->
                        <!-- <ion-input
                            label="Enter your name"
                            label-placement="stacked"
                            ref="input"
                            type="text"
                            placeholder="Your name"
                        ></ion-input> -->
                        <ion-datetime 
                            presentation="date" 
                            min="1989-06-04"
                            max="2024-08-23"
                            :multiple="true" 
                            :value="['2024-06-03', '2022-06-29']"
                            >
                        </ion-datetime>
                    </ion-item>
                </ion-content>
            </ion-modal>

            <RoomCard 
            v-for="room in rooms"
            :key="room.id"
            :room="room"
            />

        </ion-content>
    </ion-page>
</template>

<script lang="ts" setup>
import {IonPage, 
    IonContent,
    IonButtons, 
    IonButton, 
    IonItem, 
    IonModal, 
    IonTitle,
    IonHeader,
    IonToolbar,
    IonDatetime } from '@ionic/vue'; // 
import RoomCard from '../components/RoomCard.vue';
import Room from '../models/room';
import DatePicker from '../components/DatePicker.vue';
import { OverlayEventDetail } from '@ionic/core/components';
import { ref } from 'vue';

const modal = ref();

const cancel = () => modal.value.$el.dismiss(null, 'cancel');
const confirm = () => {
    modal.value.$el.onDidDismiss().then(data => {
        if (data.role === 'confirm') {
            console.log(data.data);
        } else {
            console.log('cancelled');
        }
    });
};
const onWillDismiss = (ev: CustomEvent<OverlayEventDetail>) => {
    if (ev.detail.role === 'confirm') {
       //TODO: set value on dismiss
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
ion-button {
    align-self: center;
    width: 80%;
    margin-top: 1%;
    margin-bottom: 1%;
}

/* .scroll-content {
  height: 100%;
  width: 100%;
} */
</style>