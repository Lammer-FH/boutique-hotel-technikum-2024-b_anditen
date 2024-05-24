<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-menu-button></ion-menu-button>
        </ion-buttons>
        <ion-title>Start</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <div id="overview-container">
        <strong id="heading">Willkommen im Boutique Hotel Technikum!</strong>
        <p class="justified-text">Sie sind ein Fan von Sonne, Meer und Strand? Oder lieben doch eher Berge, Wald und Schnee vor der Haustüre
          <br>
          Kein Problem! Im Boutique Hotel Technikum ist für jeden das Richtige dabei. Bestaunen Sie die 100%
          wahrheitsgetreuen Bilder auf unserer Seite und buchen Sie noch heute Ihren Aufenthalt. Wir können Sie kaum
          erwarten!</p>
        <swiper ref="swiper" class="mySwiper">
          <swiper-slide>
            <img src="/images/technikum_oversight.jpg" alt="Boutique Hotel Technikum overview"/>
          </swiper-slide>
          <swiper-slide>
            <img src="/images/hotel_room_sea.jpg" alt="Room with beach view"/>
          </swiper-slide>
          <swiper-slide>
            <img src="/images/hotel_room_mountain.jpg" alt="Room with mountain view"/>
          </swiper-slide>
        </swiper>
      </div>

    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {IonPage, IonButtons, IonContent, IonHeader, IonMenuButton, IonTitle, IonToolbar} from '@ionic/vue';
import {Swiper, SwiperSlide} from 'swiper/vue';
import 'swiper/css'
import 'swiper/css/navigation';

</script>

<style scoped>
#overview-container {
  padding: 1.1rem;
}

.justified-text {
  text-align: justify;
}

.mySwiper {
  width: 100%;
  height: 100%;
}

swiper-slide {
  display: flex;
  justify-content: center;
  align-items: center;
}

img {
  display: block;
  margin-left: auto;
  margin-right: auto;
  max-width: 100%;
  max-height: 100%;
  object-fit: cover;
}

#heading {
  text-align: center;
}

</style>
