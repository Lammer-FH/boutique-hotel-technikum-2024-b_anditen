import Customer from "@/models/customer";
import axios from "axios";
import { defineStore } from "pinia";

export const useBookingStore = defineStore("booking", {
  state: () => ({
    customer: new Customer("", "", "", "", "", ""),
    numberOfGuests: 1,
    breakfast: false,
    roomId: 0,
    loading: false,
  }),
  actions: {
    async bookRoom(
      customer: Customer,
      roomId: number,
      numberOfGuests: number,
      breakfast: boolean,
      startDate: string,
      endDate: string
    ) {
      if (!customer) {
        throw new Error("Customer not set");
      }
      this.customer = customer;
      this.roomId = roomId;

      return await axios.post(
        import.meta.env.VITE_API_BASE_URL + "/bookings",
        {
          roomIds: [roomId],
          startDate,
          endDate,
          customer: {
            firstName: customer.firstName,
            lastName: customer.lastName,
            email: customer.email,
            phoneNumber: customer.phoneNumber,
            birthDate: customer.birthDate,
          },
          numberOfGuests: numberOfGuests,
          breakfast: breakfast,
        },
        {
          validateStatus: (status) => status >= 200 && status < 499,
        }
      );
    },
  },
});
