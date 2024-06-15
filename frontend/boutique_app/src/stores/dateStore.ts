import {defineStore} from "pinia";

export const useDateStore = defineStore('date', {
    state() {
        return {
            checkInDate: '2024-06-15',
            checkOutDate: '2024-06-17'
        };
    },
    actions: {
        setCheckInDate(date: string) {
            this.checkInDate = date;
        },
        setCheckOutDate(date: string) {
            this.checkOutDate = date;
        }
    }
});