export class ReservationSearch {
    constructor(
        public serviceType: string,
        public startDate: Date | null,
        public durationInDays: number | null,
        public address: string,
        public numberOfPeople: number | null,
        public minAverageGrade: number | null
    ) { }
}