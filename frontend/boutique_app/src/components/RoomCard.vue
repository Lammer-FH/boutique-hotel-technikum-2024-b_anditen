<template>
    <ion-card color="light">
        <ion-img :src='room.imageUrl' :alt="room.name"></ion-img>
        <ion-card-header>
            <ion-card-title>{{ room.name }}</ion-card-title>
        </ion-card-header>

        <ion-card-content :fullwidth="true" class="container">
            <ion-label style="float:left">{{ room.description }}</ion-label>
            <ion-button :router-link=getDetailString() slot="end" style="float:right">Ab {{ room.pricePerNight }}€</ion-button>
        </ion-card-content>
    </ion-card>

</template>

<script lang="ts">
import { IonButton, 
    IonCard, 
    IonCardContent,
    IonCardHeader, 
    IonCardTitle,
    IonImg,
    IonLabel,
     } from '@ionic/vue';
import { defineComponent } from 'vue';
import Room from '../models/room';

export default defineComponent({
  name: "RoomCard",
  props: {
    room: {
      type: Room,
      required: true,
    },
  },
  methods: {
    getDetailString() {
      return "/room/" + this.room.id;
    },
  },
  components: {
    IonButton,
    IonCard,
    IonCardContent,
    IonCardHeader,
    IonCardTitle,
    IonImg,
    IonLabel,
  },
});

</script>

<style scoped>
.container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
}

ion-card {
  display: auto;
  align-self: center;
  width: 80%; 
  margin-top: 8px;
  margin-bottom: 8px;
}

ion-img {
  height: 10%;
  object-fit: cover;
}
</style>