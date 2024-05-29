export default class Room {
    id: number;
    title: string;
    description: string;
    pricePerNight: number;
    extras: string[];
    imageUrl: string;
    available: boolean;

    constructor(id: number, title: string, description: string, pricePerNight: number, extras: [string], imageUrl: string, available: boolean) {
        this.id = id,
        this.title = title,
        this.description = description,
        this.pricePerNight = pricePerNight,
        this.extras = extras,
        this.imageUrl = imageUrl,
        this.available = available
    }
}