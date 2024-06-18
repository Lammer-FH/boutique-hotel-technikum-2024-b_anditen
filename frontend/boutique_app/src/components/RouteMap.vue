<template>
  <div v-if="totalTravelTime" class="travel-time">
    <h2>Total Travel Time: {{ totalTravelTime }}</h2>
  </div>

  <GMapMap :center="center" :zoom="zoom" style="width: 100%; height: 500px" @tilesloaded="onMapLoaded">
    <GMapMarker v-for="(marker, index) in markers" :key="index" :position="marker.position" />
    <GMapPolyline v-if="routePath.length" :path="routePath"
      :options="{ strokeColor: '#FF0000', strokeOpacity: 1.0, strokeWeight: 2 }" />

    <div v-if="transitDetails.length" class="transit-details">
      <h2>Details zur Anreise</h2>
      <ul>
        <li v-for="(detail, index) in transitDetails" :key="index">
          <p><strong>Linien-Nummer:</strong> {{ detail.lineNumber }}</p>
          <p><strong>Linie:</strong> {{ detail.lineName }}</p>
          <p><strong>Transport-Art:</strong> {{ detail.vehicleType }}</p>
          <p><strong>Abfahrtshaltestelle:</strong> {{ detail.departureStop }}</p>
          <p><strong>Ankunftshaltestelle:</strong> {{ detail.arrivalStop }}</p>
        </li>
      </ul>
    </div>
  </GMapMap>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'RouteMap',
  props: {
    startAddress: {
      type: String,
      required: true,
    },
    travelMode: {
      type: String,
      required: true,
    }
  },
  setup(props) {
    const center = ref({ lat: 48.210033, lng: 16.363449 });
    const zoom = ref(12);
    const markers = ref([]);
    const routePath = ref([]);
    const transitDetails = ref([]);
    const totalTravelTime = ref('');

    const onMapLoaded = () => {
      const directionsService = new google.maps.DirectionsService();
      let travelMode = google.maps.TravelMode.DRIVING;
      if (props.travelMode === 'train') {
        travelMode = google.maps.TravelMode.TRANSIT;
      }

      const request = {
        origin: props.startAddress,
        destination: 'Höchstädtpl. 6, 1200 Wien',
        travelMode: travelMode,
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

          totalTravelTime.value = result.routes[0].legs[0].duration.text;

          if (props.travelMode === 'train') {
            // Extract transit details
            const legs = result.routes[0].legs[0];
            transitDetails.value = legs.steps
              .filter(step => step.travel_mode === 'TRANSIT')
              .map(step => ({
                lineName: step.transit.line.name,
                lineNumber: step.transit.line.short_name,
                vehicleType: step.transit.line.vehicle.type,
                departureStop: step.transit.departure_stop.name,
                arrivalStop: step.transit.arrival_stop.name,
              }));
          }
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
      transitDetails,
      totalTravelTime,
      onMapLoaded,
    };
  },
});
</script>

<style scoped>
.transit-details {
  margin-top: 1rem;
}

.transit-details h2 {
  font-size: 1.2rem;
}

.transit-details ul {
  list-style-type: none;
  padding: 0;
}

.transit-details li {
  margin-bottom: 1rem;
}

.transit-details p {
  margin: 0;
}
</style>
