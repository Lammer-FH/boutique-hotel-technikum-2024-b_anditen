export default class Extras {
    name: string;
    iconName: string;

    constructor(name: string) {
        this.name = name;
        this.iconName = this.mapNameToIcon(name);
    }
}