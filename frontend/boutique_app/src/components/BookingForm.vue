<template>
  <ion-button class="booking-button" expand="block" @click="setOpen(true)" :disabled="bookingEnabled" >Open</ion-button>

  <ion-modal :is-open="isOpen" @willDismiss="setOpen(false)">
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-button @click="setOpen(false)">Cancel</ion-button>
        </ion-buttons>
        <ion-title>Create your booking</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content class="ion-padding">
      <ion-item>
        <ion-input
            label="Enter your first name"
            label-placement="stacked"
            ref="first"
            type="text"
            placeholder="Your first name"
        ></ion-input>
      </ion-item>
      <ion-item>
        <ion-input
            label="Enter your last name"
            label-placement="stacked"
            ref="last"
            type="text"
            placeholder="Your last name"
        ></ion-input>
      </ion-item>
      <ion-item>
        <ion-input
            label="Enter your email"
            label-placement="stacked"
            ref="email"
            type="email"
            placeholder="Your email"
        ></ion-input>
      </ion-item>
      <ion-item>
        <ion-input
            label="Enter your phone number"
            label-placement="stacked"
            ref="phone"
            type="tel"
            placeholder="Your phone number"
        ></ion-input>
      </ion-item>
      <ion-item>
        <ion-input
            label="Enter your birth date"
            label-placement="stacked"
            ref="birth"
            type="date"
            placeholder="Your birth date"
        ></ion-input>
      </ion-item>
      <ion-item>
        <ion-input
            label="Enter the number of guests"
            label-placement="stacked"
            ref="guests"
            type="number"
            placeholder="Number of guests"
        ></ion-input>
      </ion-item>
      <ion-item>
        <ion-label>Breakfast</ion-label>
        <ion-checkbox slot="end" ref="breakfast"></ion-checkbox>
      </ion-item>
      <br>
      <ion-button @click="bookRoom">
        Confirm
      </ion-button>
    </ion-content>
  </ion-modal>

</template>

<script lang="ts" setup>
import {IonButtons, IonButton, IonModal, IonHeader, IonToolbar, IonContent, IonTitle} from '@ionic/vue';
import {ref} from 'vue';
import {useDateStore} from "@/stores/dateStore";
import axios from "axios";

const props = defineProps(['roomId', 'buttonEnabled']);

let bookingEnabled = props.buttonEnabled;

const isOpen = ref(false);

const setOpen = (open: boolean) => (isOpen.value = open);

const first = ref();
const last = ref();
const email = ref();
const phone = ref();
const birth = ref();
const guests = ref();
const breakfast = ref();


const bookRoom = async () => {
  const dateStore = useDateStore();
  await axios.post(import.meta.env.VITE_API_BASE_URL + "/bookings", {
    roomIds: [props.roomId],
    startDate: dateStore.start,
    endDate: dateStore.end,
    customer: {
      firstName: first.value.value,
      lastName: last.value.value,
      email: email.value.value,
      phoneNumber: phone.value.value,
      birthDate: birth.value.value,
    },
    numberOfGuests: guests.value.value,
    breakfast: breakfast.value.checked,
  });
}
</script>

<style>
.booking-button {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  color: white;
  border-radius: 0;
  z-index: 1000;
}
</style>