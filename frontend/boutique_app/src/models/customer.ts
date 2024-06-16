export default class Customer {
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    birthDate: string;

    constructor(firstName: string, lastName: string, email: string, phoneNumber: string, birthDate: string) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }
}