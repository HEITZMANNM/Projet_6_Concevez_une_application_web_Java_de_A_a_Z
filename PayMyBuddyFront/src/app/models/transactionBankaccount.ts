import { Bankaccount } from "./bankaccount";
import { User } from "./user";

export class TransactionBankaccount {

    constructor(
        
        public id: number,
        public description: string,
        public amount: number,
        public date: Date,
        public userSender: User,
        public bankaccount: Bankaccount,
        public origin: string
        ){}
            }