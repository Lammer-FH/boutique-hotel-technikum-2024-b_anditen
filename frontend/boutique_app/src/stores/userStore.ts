import User from "@/models/user";
import {defineStore} from "pinia";
import axios from "axios";

export const useUserStore = defineStore('user', {
    state() {
        return {
            user: null as User | null,
        };
    },
    actions: {
        async sendBooking(user: User, roomId: number, start:string | undefined, end:string | undefined) {
            this.user = user;

            await axios.post(import.meta.env.VITE_API_BASE_URL + "/bookings", {
                roomIds: [roomId],
                startDate: start,
                endDate: end,
                customer: {
                  firstName: user.customer.firstName,
                  lastName: user.customer.lastName,
                  email: user.customer.email,
                  phoneNumber: user.customer.phoneNumber,
                  birthDate: user.customer.birthDate,
                },
                numberOfGuests: user.numberOfGuests,
                breakfast: user.breakfast,
              },
              {
                headers: {
                    'Content-Type': 'application/json'
                },
                transformRequest: [(data, headers) => {
                    console.log('Transformed Data:', data);
                    if (Array.isArray(data.roomIds)) {
                        data.roomIds = data.roomIds.map(id => Number(id));
                    }
                    return JSON.stringify(data);
                }]
              }
            )
            .then((response) => {
                console.log('Response:', response);
            })
            .catch((error) => {
                console.log('Error:', error);
             });
        }
    }
});