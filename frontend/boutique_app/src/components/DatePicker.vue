<template>
  <ion-datetime class="clink-date-time-range" presentation="date" locale="en-US" multiple :value="currentUserSelection"
    v-on:ion-change="handleDateChange" :highlighted-dates="defineHighlightStyle" v-bind="$attrs" />
</template>
<script setup lang="ts">
import { DatetimeHighlightCallback, IonDatetimeCustomEvent } from '@ionic/core';
import { DatetimeChangeEventDetail, IonDatetime } from '@ionic/vue';
import { onMounted, ref } from 'vue';

interface ClinkDatetimeRange {
  start: string;
  end: string;
}

const props = defineProps<ClinkDatetimeRange>();
const emit = defineEmits<{
  (e: 'update:start' | 'update:end', date: string): void;
}>();

const currentUserSelection = ref<string[] | undefined>();
const previousUserSelection = ref<string[] | undefined>();

onMounted(() => {
  const initRange: string[] = [props.start, props.end];
  currentUserSelection.value = previousUserSelection.value = initRange;
});

const defineHighlightStyle: DatetimeHighlightCallback = (date: string) => {
  if (!currentUserSelection.value || currentUserSelection.value.length < 2)
    return;
  const startDateCurrentRange = new Date(currentUserSelection.value[0]);

  const endDateCurrentRange = new Date(currentUserSelection.value.at(-1) as string);

  const formattedDate = new Date(date);
  const isDateWhithinRange =
    formattedDate > startDateCurrentRange &&
    formattedDate < endDateCurrentRange;

  if (isDateWhithinRange)
    return {
      backgroundColor: '#4818f717',
    };
};

const handleDateChange = (
  e: IonDatetimeCustomEvent<DatetimeChangeEventDetail>
) => {
  currentUserSelection.value = e.detail.value as string[] | undefined;

  if (!currentUserSelection.value && !previousUserSelection.value) return;

  if (!currentUserSelection.value || currentUserSelection.value.length < 2)
    return;

  currentUserSelection.value = [...currentUserSelection.value].sort((a, b) =>
    a > b ? 1 : -1
  );

  if (previousUserSelection.value && previousUserSelection.value.length >= 2) {
    const isSelectionWhithinPreviousRange =
      currentUserSelection.value.length < previousUserSelection.value.length;

    if (isSelectionWhithinPreviousRange) {
      const selectedDateWhithinPreviousRange =
        previousUserSelection.value.filter(
          (date) => !(currentUserSelection.value as string[]).includes(date)
        );
      currentUserSelection.value = selectedDateWhithinPreviousRange;
    } else {
      const lastUserSelection = [(e.detail.value as string[]).at(-1) as string];
      currentUserSelection.value = lastUserSelection;
    }
  }

  previousUserSelection.value = currentUserSelection.value;
  emit('update:start', currentUserSelection.value[0]);
  emit('update:end', currentUserSelection.value.at(-1) as string);
};
</script>