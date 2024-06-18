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
        <ion-input label="Enter your first name" label-placement="stacked" v-model="first" type="text"
          placeholder="Your first name"></ion-input>
      </ion-item>
      <ion-item>
        <ion-input label="Enter your last name" label-placement="stacked" v-model="last" type="text"
          placeholder="Your last name"></ion-input>
      </ion-item>
      <ion-item>
        <ion-input label="Enter your email" label-placement="stacked" v-model="email" type="email"
          placeholder="Your email"></ion-input>
      </ion-item>
      <ion-item>
        <ion-input label="Confirm your email" label-placement="stacked" v-model="confirmEmail" type="email"
          placeholder="Confirm your email"></ion-input>
      </ion-item>
      <ion-item>
        <ion-input label="Enter your phone number" label-placement="stacked" v-model="phone" type="tel"
          placeholder="Your phone number"></ion-input>
      </ion-item>
      <ion-item>
        <ion-input label="Enter your birth date" label-placement="stacked" v-model="birth" type="date"
          placeholder="Your birth date"></ion-input>
      </ion-item>
      <ion-item>
        <ion-input label="Enter your address" label-placement="stacked" v-model="address" type="text"
          placeholder="Address"></ion-input>
      </ion-item>
      <ion-item>
        <ion-input label="Enter the number of guests" label-placement="stacked" v-model="guests" type="number"
          placeholder="Number of guests"></ion-input>
      </ion-item>
      <ion-item>
        <ion-label>Breakfast</ion-label>
        <ion-checkbox slot="end" v-model="breakfast"></ion-checkbox>
      </ion-item>
      <br>
      <ion-button @click="bookRoom">
        Confirm
      </ion-button>
    </ion-content>
  </ion-modal>
</template>

<script lang="ts" setup>
import Customer from "@/models/customer";
import { useBookingStore } from "@/stores/bookingStore";
import { useDateStore } from "@/stores/dateStore";
import { useRoomStore } from "@/stores/roomsStore";
import { IonButton, IonButtons, IonContent, IonHeader, IonModal, IonTitle, IonToolbar, IonCheckbox, IonLabel, IonItem, IonInput } from '@ionic/vue';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const props = defineProps(['roomId']);
const router = useRouter();
const bookingStore = useBookingStore();
const roomStore = useRoomStore();
const dateStore = useDateStore();

const isOpen = ref(false);

const setOpen = (open: boolean) => (isOpen.value = open);

onMounted(() => {
  dateStore.$subscribe(async (mutation, state) => {
    await roomStore.fetchRooms(state.start, state.end);
  });
  roomStore.$subscribe(async (mutation, state) => {
    isAvailable.value = state.rooms.find(room => room.id == props.roomId)?.available ?? false;
  });
  isAvailable.value = roomStore.rooms.find(room => room.id == props.roomId)?.available ?? false;
});

const isAvailable = ref(false);

const first = ref('');
const last = ref('');
const email = ref('');
const confirmEmail = ref('');
const phone = ref('');
const birth = ref('');
const address = ref('');
const guests = ref(0);
const breakfast = ref(false);

function fieldsValidation(): boolean {
  if (!first.value || first.value === '') {
    return false;
  }
  if (!last.value || last.value === '') {
    return false;
  }
  if (!email.value || email.value === '') {
    return false;
  }
  if (email.value !== confirmEmail.value) {
    return false;
  }
  if (!phone.value || phone.value === '') {
    return false;
  }
  if (!birth.value || birth.value === '') {
    return false;
  }
  if (!address.value || address.value === '') {
    return false;
  }
  if (guests.value === 0) {
    return false;
  }
  return true;
}

const bookRoom = async () => {
  if (!fieldsValidation()) {
    alert("Bitte füllen sie alle Felder korrekt aus!")
    return;
  }

  const customer = new Customer(first.value, last.value, email.value, phone.value, birth.value, address.value);
  bookingStore.numberOfGuests = guests.value;
  bookingStore.breakfast = breakfast.value;

  const res = await bookingStore.bookRoom(customer, props.roomId, guests.value, breakfast.value, dateStore.start, dateStore.end);

  if (res.status === 200) {
    setOpen(false);
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
