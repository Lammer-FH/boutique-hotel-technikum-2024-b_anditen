<template>
  <ion-row>
    <div>
      <h2>From:</h2>
      <ion-datetime presentation="date" :prefer-wheel="true" :min="new Date().toISOString()" max="2050" :value="start"
        @ionChange="setStart"></ion-datetime>
    </div>
    <div>
      <h2>To:</h2>
      <ion-datetime presentation="date" :prefer-wheel="true" :min="endMin" max="2050" :value="end"
        @ionChange="setEnd"></ion-datetime>
    </div>
  </ion-row>
</template>

<script setup lang="ts">
import { useDateStore } from "@/stores/dateStore";
import { IonDatetimeCustomEvent } from "@ionic/core";
import { DatetimeChangeEventDetail, IonDatetime } from '@ionic/vue';
import { onMounted, ref } from 'vue';

const store = useDateStore()

const tomorrow = () => {
  const d = new Date()
  d.setDate(d.getDate() + 1)
  return d.toISOString()
}

const start = ref<string>(store.start ?? new Date().toISOString())
const end = ref<string>(store.end ?? tomorrow())

const endMin = ref<string>(tomorrow())

onMounted(() => {
  if (!store.start) {
    store.start = new Date().toISOString().split('T')[0]
  }
  if (!store.end) {
    store.end = tomorrow().split('T')[0]
  }
})

function setStart(event: IonDatetimeCustomEvent<DatetimeChangeEventDetail>) {
  const v = event.detail.value as string
  const date = v.split('T')[0]

  const d = new Date(date)
  d.setDate(d.getDate() + 1)
  endMin.value = d.toISOString()

  start.value = date
  store.start = date
}

function setEnd(event: IonDatetimeCustomEvent<DatetimeChangeEventDetail>) {
  const v = event.detail.value as string
  const date = v.split('T')[0]

  end.value = date
  store.end = date
}
</script>