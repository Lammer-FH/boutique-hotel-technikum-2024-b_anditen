export default class Customer {
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    birthDate: string;
    address: string;

    constructor(firstName: string, lastName: string, email: string, phoneNumber: string, birthDate: string, address: string) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address = address;
    }
}