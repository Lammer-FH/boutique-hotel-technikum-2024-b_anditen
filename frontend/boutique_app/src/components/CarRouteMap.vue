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
  <div v-if="totalTravelTime" class="travel-time">
    <h2>Total Travel Time: {{ totalTravelTime }}</h2>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'CarRouteMap',
  props: {
    startAddress: {
      type: String,
      required: true,
    },
  },
  setup(props) {
    const center = ref({ lat: 48.210033, lng: 16.363449 });
    const zoom = ref(12);
    const markers = ref([]);
    const routePath = ref([]);
    const totalTravelTime = ref('');

    const onMapLoaded = () => {
      const directionsService = new google.maps.DirectionsService();

      const request = {
        origin: props.startAddress,
        destination: 'Höchstädtpl. 6, 1200 Wien',
        travelMode: google.maps.TravelMode.DRIVING, // Use driving mode for car routes
      };

      directionsService.route(request, (result, status) => {
        if (status === google.maps.DirectionsStatus.OK && result.routes[0]) {
          const overviewPath = result.routes[0].overview_path;
          routePath.value = overviewPath.map(point => ({
            lat: point.lat(),
            lng: point.lng(),
          }));

          // Add markers for start and end locations
          markers.value.push({
            position: result.routes[0].legs[0].start_location,
          });
          markers.value.push({
            position: result.routes[0].legs[0].end_location,
          });

          // Extract total travel time
          totalTravelTime.value = result.routes[0].legs[0].duration.text;
        } else {
          console.error('Error fetching directions:', status);
        }
      });
    };

    return {
      center,
      zoom,
      markers,
      routePath,
      totalTravelTime,
      onMapLoaded,
    };
  },
});
</script>

<style scoped>
.travel-time {
  margin-top: 1rem;
}

.travel-time h2 {
  font-size: 1.2rem;
}
</style>
