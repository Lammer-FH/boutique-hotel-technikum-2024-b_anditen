import {defineStore} from "pinia";

export const useDateStore = defineStore('date', {
    state() {
        return {
            start: new Date().toISOString().split('T')[0] as string | undefined,
            end: tomorrow().split('T')[0] as string | undefined
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

const tomorrow = () => {
    const d = new Date()
    d.setDate(d.getDate() + 1)
    return d.toISOString()
}