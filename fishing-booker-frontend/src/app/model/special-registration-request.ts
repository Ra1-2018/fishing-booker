export class SpecialRegistrationRequest {
    constructor(
        public email: string,
        public password: string,
        public rePassword: string,
        public name: string,
        public surname: string,
        public address: string,
        public city: string,
        public country: string,
        public telephone: string,
        public appUserType: string,
        public explanation: string
    ) {}
}