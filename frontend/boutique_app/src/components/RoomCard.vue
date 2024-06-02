<template>
    <ion-card color="light">
        <ion-img :src='room.getLocalImage()' :alt="room.title"></ion-img>
        <ion-card-header>
            <ion-card-title>{{ room.title }}</ion-card-title>
            <!-- <ion-card-subtitle> {{ room.descritpion }}</ion-card-subtitle> -->
        </ion-card-header>

        <ion-card-content>
            <ion-label>{{ room.descritpion }}</ion-label>
            <!-- <ion-badge slot="end">{{ room.price }}</ion-badge> -->
            <!-- <ion-button fill="clear" slot="end">Ab {{ room.price }}€</ion-button> -->
            <ion-button fill="clear" slot="end">Ab {{ room.price }}€</ion-button>
        </ion-card-content>
        
        <!-- <ion-button fill="clear" slot="end">Ab {{ room.price }}€</ion-button> -->

    </ion-card>
</template>

<script lang="ts">
import { IonButton, 
    IonCard, 
    IonCardContent,
    IonCardHeader, 
    //  IonCardSubtitle, 
    IonCardTitle,
    IonImg,
    IonLabel,
     } from '@ionic/vue';
import { defineComponent } from 'vue';
import Room from '../models/room';

export default defineComponent({
    name: 'RoomCard',
    props: {
        room: {
            type: Room,
            required: true,
        },
    },
    // methods: {
    //     imagePath(img) {
    //         return require(`@/assets/${img}`);
    //     }
    // },
    components: { IonButton, IonCard, IonCardContent, IonCardHeader, 
        // IonCardSubtitle, 
        IonCardTitle,
        IonImg,
        IonLabel
    },
});
</script>

<style scoped>
ion-card {
display: auto;
  align-self: center;
  width: 80%; 
  margin-top: 8px;
  margin-bottom: 8px;
}
ion-img {
  height: 100px;
  object-fit: cover;
}
/* ion-badge {
  font-size: 1rem;
  padding: 0.5rem;
} */
</style>