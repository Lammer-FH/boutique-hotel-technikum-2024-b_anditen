<template>
  <ion-page>
    <ion-header>
        <ion-toolbar>
            <ion-buttons slot="start">
                <ion-menu-button></ion-menu-button>
            </ion-buttons>
            <ion-title>{{ room.title }}</ion-title>
            <!-- <ion-title>Zimmer {{ id }}</ion-title> -->
        </ion-toolbar>
    </ion-header>
    <ion-content>
        <ion-card-content :fullwidth="true" :fullheight="true">
            <ion-img :src='room.getLocalImage()' :alt="room.title"></ion-img>

            <ion-button>
                Buchen um {{ room.pricePerNight }}€
            </ion-button>
        </ion-card-content>

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
    IonCardContent,
    IonImg,
    IonMenuButton
} from '@ionic/vue';
import { defineComponent } from 'vue';
import { useRoute } from 'vue-router';
import Room from '../models/room';
import { useRoomStore } from '../store/roomsStore';

export default defineComponent({
    name: 'DetailRoomPage',
    components: {
        IonButtons, 
        IonButton, 
        IonHeader, 
        IonToolbar, 
        IonContent, 
        IonTitle,
        IonPage,
        IonCardContent,
        IonImg,
        IonMenuButton
    },
    setup() {
        const route = useRoute();
        const { id } = route.params;
        const store = useRoomStore();
        const room = store.getRoom(id as unknown as number) as Room;
        console.log(room);
        return {
            room
        }
    }
});

</script>

<style>

</style>