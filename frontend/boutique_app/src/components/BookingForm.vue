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
import { useUserStore } from '@/stores/userStore';
import User from '@/models/user';
import Customer from '@/models/customer';

const userStore = useUserStore();
const user = userStore.user;

const props = defineProps(['roomId', 'buttonEnabled']);

let bookingEnabled = props.buttonEnabled;

const isOpen = ref(false);

const setOpen = (open: boolean) => (isOpen.value = open);

const first = ref(user?.customer.firstName);
const last = ref(user?.customer.lastName);
const email = ref(user?.customer.email);
const phone = ref(user?.customer.phoneNumber);
const birth = ref(user?.customer.birthDate);
const guests = ref(user?.numberOfGuests);
const breakfast = ref(user?.breakfast);

function fieldsValidation() : boolean {
  if (!first.value.value || first.value === '') {
    console.log("firstname is not used");
    return false;
  }
  if (!last.value.value || last.value === '') {
    console.log("lastName is not used");
    return false;
  }
  if (!email.value.value || email.value === '') {
    console.log("email is not used");
    return false;
  }
  if (!phone.value.value || phone.value === '') {
    console.log("phone is not used");
    return false;
  }
  if (!birth.value.value || birth.value === '') {
    console.log("birth is not used");
    return false;
  }
  if (!guests.value.value || guests.value === 0) {
    console.log("guests is not used");
    return false;
  }
  return true;
}

const bookRoom = () => {
  if (!fieldsValidation()) {
    alert("Bitte füllen sie alle felder vorher aus!")
    return;
  }

  const dateStore = useDateStore();

  userStore.sendBooking(
    new User(
      new Customer(
        first.value.value || '',
        last.value.value || '',
        email.value.value || '',
        phone.value.value || '',
        birth.value.value || ''
      ),
      guests.value.value || 1,
      breakfast.value.checked || false
    ),
    props.roomId,
    dateStore.start,
    dateStore.end
  )

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