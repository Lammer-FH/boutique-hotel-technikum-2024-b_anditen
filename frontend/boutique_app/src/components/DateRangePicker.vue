<template>
  <ion-row>
    <div>
      <h2>From:</h2>
      <ion-datetime presentation="date" :prefer-wheel="true" :min="new Date().toISOString()" max="2027"
                    :value="start" @ionChange="setStart"></ion-datetime>
    </div>
    <div>
      <h2>To:</h2>
      <ion-datetime presentation="date" :prefer-wheel="true" :min="start ?? new Date().toISOString()" max="2027"
                    :value="end" @ionChange="setEnd"></ion-datetime>
    </div>
  </ion-row>
</template>

<script setup lang="ts">
import {DatetimeChangeEventDetail, IonDatetime} from '@ionic/vue';
import {onMounted, ref} from 'vue';
import {useDateStore} from "@/stores/dateStrore";
import {IonDatetimeCustomEvent} from "@ionic/core";

const store = useDateStore()

const start = ref<string | undefined>(store.start)
const end = ref<string | undefined>(store.end)

onMounted(() => {
  start.value = store.start
  end.value = store.end
})

function setStart(event: IonDatetimeCustomEvent<DatetimeChangeEventDetail>) {
  const v = event.detail.value as string
  const date = v.split('T')[0]

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