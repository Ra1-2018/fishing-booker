export class Review {
    constructor(
        public service: any,
        public grade: number|null,
        public revision: string,
        public client: any
    ) {}
}