import {defineStore} from "pinia";

export const useDateStore = defineStore('date', {
    state() {
        return {
            start: "" as string | undefined,
            end: "" as string | undefined
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