import axios from "axios";
import { defineStore } from "pinia";
import Room from "../models/room";

export const useRoomStore = defineStore("rooms", {
  state: () => {
    return {
      rooms: [] as Room[],
      loading: false,
    };
  },
  actions: {
    async fetchRooms(
      start: string | undefined = undefined,
      end: string | undefined = undefined
    ) {
      if (this.loading) {
        return new Promise((resolve) => {
          setTimeout(() => {
            resolve(this.rooms);
          }, 500);
        });
      }

      this.loading = true;

      const response = await axios.get(
        import.meta.env.VITE_API_BASE_URL + "/rooms",
        {
          headers: {
            "Content-Type": "application/json",
          },
          params: {
            start: start,
            end: end,
          },
        }
      );
      this.rooms = response.data.map(
        (roomData: any) =>
          new Room(
            roomData.id,
            roomData.name,
            roomData.description,
            roomData.pricePerNight,
            roomData.type,
            roomData.beds,
            roomData.extras,
            roomData.imageUrl,
            roomData.available
          )
      );
      console.log("Rooms: ", this.rooms);
      this.loading = false;

      return this.rooms;
    },

    getRoom(id: number) {
      console.log("getRoom called with id:" + id);
      return this.rooms.find((room) => room.id === id);
    },
  },
});
