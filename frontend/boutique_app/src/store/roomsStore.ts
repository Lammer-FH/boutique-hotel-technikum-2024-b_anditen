import { defineStore } from 'pinia';
import Room from '../models/room';

export const useRoomStore = defineStore('rooms', {
    state: () => {
        return {
            rooms: [
            new Room(
                1,
                'Zimmer 1',
                'Doppelbettzimmer',
                134,
                ["Wlan"],
                'hotel_room_beachfront.jpg',
                true
            ),
            new Room(
                2,
                'Zimmer 2',
                'Doppelbettzimmer',
                102,
                ["Wlan"],
                'hotel_room_forest.jpg',
                true
            ),
            new Room(
                3,
                'Zimmer 3',
                'Doppelbettzimmer',
                97,
                ["Wlan"],
                'hotel_room_mountain.jpg',
                true
            ),
            new Room(
                4,
                'Zimmer 4',
                'Doppelbettzimmer',
                111,
                ["Wlan"],
                'hotel_room_sea.jpg',
                true
            ),
        ] as Room[]
        };
    },
    actions: {
        getRoom(id: number) {
            console.log('getRoom called with id:'+ id);
            return this.rooms.find(room => room.id === id);
        },
    }
    // const rooms = ref();

    // return { rooms };
//   state: () => ({
//     room: {
//       name: '',
//       description: '',
//       owner: '',
//       members: [],
//       messages: [],
//     },
//   }),
});