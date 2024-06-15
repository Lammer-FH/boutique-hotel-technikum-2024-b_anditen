import {defineStore} from "pinia";

export const useDateStore = defineStore('date', {
    state() {
        return {
            start: undefined as string | undefined,
            end: undefined as string | undefined
        };
    },
    actions: {
        setStart(date: string) {
            this.start = date;
        },
        setEnd(date: string) {
            this.end = date;
        }
    }
});