import Beds from "./beds";
import Extras from "./extras";

export default class Room {
  id: number;
  name: string;
  // pricePerNightString: string;
  pricePerNight: number;
  type: string;
  description: string;
  beds: Beds;
  extras: Extras[];
  imageUrl: string;
  available: boolean | null;

  constructor(
    id: number,
    name: string,
    description: string,
    pricePerNight: number,
    type: string,
    beds: Beds,
    extras: Extras[],
    imageUrl: string,
    available: boolean | null
  ) {
    (this.id = id),
      (this.name = name),
      (this.pricePerNight = pricePerNight),
      (this.type = type),
      (this.description = description),
      (this.beds = beds),
      (this.extras = extras),
      (this.imageUrl = imageUrl),
      (this.available = available);
  }

  getLocalImage(): string {
    return new URL(`../assets/${this.imageUrl}`, import.meta.url).pathname;
  }
}
