<template>
    <ion-datetime 
    presentation="date" 
    :multiple="true" 
    :value="['2024-06-03', '2022-06-29']"
    >
    </ion-datetime>
</template>

<!-- done-text="Auswählen" 
cancel-text="Abbrechen"  -->

<script lang="ts">
    import { IonDatetime } from '@ionic/vue';
    import { defineComponent } from 'vue';

    export default defineComponent({
        components: { IonDatetime },
    });
</script>