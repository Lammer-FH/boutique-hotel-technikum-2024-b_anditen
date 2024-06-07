import { defineStore } from 'pinia';
import axios from 'axios';
import Room from '../models/room';

export const useRoomStore = defineStore('rooms', {
    state: () => {
        return {
            rooms: [
        //     new Room(
        //         1,
        //         'Zimmer 1',
        //         'Doppelbettzimmer',
        //         134,
        //         ["Wlan"],
        //         'hotel_room_beachfront.jpg',
        //         true
        //     ),
        //     new Room(
        //         2,
        //         'Zimmer 2',
        //         'Doppelbettzimmer',
        //         102,
        //         ["Wlan"],
        //         'hotel_room_forest.jpg',
        //         true
        //     ),
        //     new Room(
        //         3,
        //         'Zimmer 3',
        //         'Doppelbettzimmer',
        //         97,
        //         ["Wlan"],
        //         'hotel_room_mountain.jpg',
        //         true
        //     ),
        //     new Room(
        //         4,
        //         'Zimmer 4',
        //         'Doppelbettzimmer',
        //         111,
        //         ["Wlan"],
        //         'hotel_room_sea.jpg',
        //         true
        //     ),
        // 
        ] as Room[]
        };
    },
    actions: {
        async fetchRooms() {
            if (this.rooms.length > 0) {
                return this.rooms;
            } else {
                // axios.get('http://localhost:8080/rooms', {
                //         headers: {
                //             'Content-Type': 'application/json'
                //         }
                //     })
                //     .then(response => {
                //         console.log(response.data);
                //         this.rooms = response.data;
                //         return this.rooms
                //     })
                //     .catch(error => {
                //         console.log(error);
                //     });
                const response = await axios.get('http://localhost:8080/rooms', {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                console.log(response.data);
                this.rooms = response.data;
                console.log(this.rooms);
                return this.rooms;
            }
        },

        getRoom(id: number) {
            console.log('getRoom called with id:'+ id);
            return this.rooms.find(room => room.id === id);
        },
        
    }
});