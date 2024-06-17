<template>
  <ion-button v-if="isAvailable" class="booking-button" expand="block" @click="setOpen(true)">Book</ion-button>
  <ion-button v-else class="booking-button" expand="block" disabled>Unavailable</ion-button>
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
            label="Confirm your email"
            label-placement="stacked"
            ref="confirmEmail"
            type="email"
            placeholder="Confirm your email"
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
            label="Enter your address"
            label-placement="stacked"
            ref="address"
            type="text"
            placeholder="Address"
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
import {onMounted, ref} from 'vue';
import {useDateStore} from "@/stores/dateStore";
import {useRoomStore} from "@/stores/roomsStore";
import {useBookingStore} from "@/stores/bookingStore";
import { useRouter } from 'vue-router';
import Customer from "@/models/customer";

const props = defineProps(['roomId']);
const router = useRouter();
const bookingStore = useBookingStore();
const roomStore = useRoomStore();
const dateStore = useDateStore();

const isOpen = ref(false);

const setOpen = (open: boolean) => (isOpen.value = open);

onMounted(() => {
  dateStore.$subscribe(async (mutation, state) => {
    console.log('DateStore changed', state.start, state.end);
    await roomStore.fetchRooms(state.start, state.end);
  });
  roomStore.$subscribe(async (mutation, state) => {
    console.log('RoomStore changed', state.rooms);
    isAvailable.value = state.rooms.find(room => room.id == props.roomId)?.available ?? false;
    console.log(props.roomId)
    console.log('RoomStore changed', state.rooms.find(room => room.id == props.roomId));
  });
  isAvailable.value = roomStore.rooms.find(room => room.id == props.roomId)?.available ?? false;
});

const isAvailable = ref(false);

const first = ref();
const last = ref();
const email = ref();
const confirmEmail = ref();
const phone = ref();
const birth = ref();
const address = ref();
const guests = ref();
const breakfast = ref();

function fieldsValidation(): boolean {
  if (!first.value || first.value === '') {
    console.log("firstname is not used");
    return false;
  }
  if (!last.value || last.value === '') {
    console.log("lastName is not used");
    return false;
  }
  if (!email.value || email.value === '') {
    console.log("email is not used");
    return false;
  }
  if (email.value.value !== confirmEmail.value.value) {
    console.log("emails do not match");
    return false;
  }
  if (!phone.value || phone.value === '') {
    console.log("phone is not used");
    return false;
  }
  if (!birth.value || birth.value === '') {
    console.log("birth is not used");
    return false;
  }
  if (!address.value || address.value === '') {
    console.log("address is not used");
    return false;
  }
  if (!guests.value || guests.value === 0) {
    console.log("guests is not used");
    return false;
  }
  return true;
}

const bookRoom = async () => {
  if (!fieldsValidation()) {
    alert("Bitte füllen sie alle felder korrekt aus!")
    return;
  }

  const customer = new Customer(first.value.value, last.value.value, email.value.value, phone.value.value, birth.value.value, address.value.value);
  bookingStore.numberOfGuests = guests.value.value;
  bookingStore.breakfast = breakfast.value.checked;

  const res = await bookingStore.bookRoom(customer, props.roomId, guests.value.value, breakfast.value.checked, dateStore.start, dateStore.end);

  if (res.status === 200) {
    setOpen(false);
    console.log(res.data);
    router.push({ name: 'Confirmation' }); // Redirect to confirmation page
  } else if (res.status == 409) {
    alert("Room is already booked in this time period!");
  } else {
    alert("Booking failed!");
  }
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