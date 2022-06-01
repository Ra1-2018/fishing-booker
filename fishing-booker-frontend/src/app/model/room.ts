export class Room
{
    constructor(
        public roomCode: string,
        public numberOfBeds: number,
        public cottageId: number | null
    ) {}
}