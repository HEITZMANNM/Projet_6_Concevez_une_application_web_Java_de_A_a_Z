import { User } from "./user";

export class Bankaccount {

    constructor(
        public id: number,
       public iban: string,
    public bic: string,
    public status: string,
    public user: User
        ){}
            }