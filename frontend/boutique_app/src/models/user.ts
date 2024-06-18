import Customer from "./customer";

export default class User {
  customer: Customer;
  numberOfGuests: number;
  breakfast: boolean;

  constructor(customer: Customer, numberOfGuests: number, breakfast: boolean) {
    this.customer = customer;
    this.numberOfGuests = numberOfGuests;
    this.breakfast = breakfast;
  }
}
