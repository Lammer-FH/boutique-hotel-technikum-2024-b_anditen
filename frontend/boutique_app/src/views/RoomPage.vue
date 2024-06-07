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
                        <div class="centered-container">
                            <DatePicker
                                id="datetimepicker"
                                min="2023-01-01T00:00:00"
                                :start="startDate"
                                :end="endDate"
                                @update:start="(d) => (startDate = d)"
                                @update:end="(d) => (endDate = d)"
                            />
                            <!-- <ion-datetime 
                                ref="datepicker"
                                presentation="date" 
                                min="minDateString"
                                max="maxDateString"
                                :multiple="true" 
                                :value=dateRange
                                @ionChange="handleDateChange"
                            /> -->
                        </div>
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
import { 
    IonButtons, 
    IonButton, 
    IonModal, 
    IonHeader, 
    IonToolbar, 
    IonContent, 
    IonTitle,
    IonPage,
    // IonDatetime,
    IonMenuButton 
} from '@ionic/vue';
import DatePicker from '@/components/DatePicker.vue';
// import { format, parseISO } from 'date-fns';
import RoomCard from '../components/RoomCard.vue';
import Room from '../models/room';
import { useRoomStore } from '@/stores/roomsStore';
import { ref } from 'vue';
// import { useRouter } from 'vue-router';
// const router = useRouter();

const store = useRoomStore();
const rooms = store.rooms as Room[];

const showPicker = ref(false);
const setOpen = (open: boolean) => (showPicker.value = open);

const startDate = ref<string>('2023-10-01');
const endDate = ref<string>('2023-10-23');

// const today = new Date();
// const minDate = new Date(today);
// minDate.setDate(today.getDate() + 1);
// const minDateString = minDate.toISOString();

// const maxDate = new Date(today);
// maxDate.setMonth(today.getMonth() + 6);
// const maxDateString = maxDate.toISOString();

// console.log(minDate + " " + maxDate);

// const datepicker = ref(null);
// const dateRange = ref<string[]>([]);

// const handleDateChange = (event: DatetimeChangeEvent) => {
//     const value = event.detail
//     if (value.value.length > 2) {
//         value.value = value.value.slice(0, 2);
//     }
// };
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