<template>
  <GMapMap
      :center="center"
      :zoom="zoom"
      style="width: 100%; height: 500px"
      @tilesloaded="onMapLoaded"
  >
    <GMapMarker
        v-for="(marker, index) in markers"
        :key="index"
        :position="marker.position"
    />
    <GMapPolyline
        v-if="routePath.length"
        :path="routePath"
        :options="{ strokeColor: '#FF0000', strokeOpacity: 1.0, strokeWeight: 2 }"
    />
  </GMapMap>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'RouteMap',
  setup() {
    const center = ref({ lat: 48.210033, lng: 16.363449 });
    const zoom = ref(12);
    const markers = ref<any[]>([]);
    const routePath = ref<any[]>([]);

    const onMapLoaded = () => {
      const geocoder = new google.maps.Geocoder();
      const directionsService = new google.maps.DirectionsService();
      const startAddress = 'Canongasse 5, 1180 Wien';
      const endAddress = 'Höchstädtpl. 6, 1200 Wien';

      geocoder.geocode({ address: startAddress }, (startResults, startStatus) => {
        if (startStatus === google.maps.GeocoderStatus.OK && startResults[0]) {
          const startLocation = startResults[0].geometry.location;
          markers.value.push({ position: startLocation });

          geocoder.geocode({ address: endAddress }, (endResults, endStatus) => {
            if (endStatus === google.maps.GeocoderStatus.OK && endResults[0]) {
              const endLocation = endResults[0].geometry.location;
              markers.value.push({ position: endLocation });

              const request = {
                origin: startLocation,
                destination: endLocation,
                travelMode: google.maps.TravelMode.DRIVING,
              };

              directionsService.route(request, (result, status) => {
                if (status === google.maps.DirectionsStatus.OK && result.routes[0]) {
                  routePath.value = result.routes[0].overview_path.map((point: any) => ({
                    lat: point.lat(),
                    lng: point.lng(),
                  }));
                } else {
                  console.error('Error fetching directions', status);
                }
              });
            } else {
              console.error('End address geocoding failed:', endStatus);
            }
          });
        } else {
          console.error('Start address geocoding failed:', startStatus);
        }
      });
    };

    return {
      center,
      zoom,
      markers,
      routePath,
      onMapLoaded,
    };
  },
});
</script>

<style scoped>
</style>
