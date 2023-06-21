import { Bankaccount } from "./bankaccount";
import { User } from "./user";

export class TransactionBankaccountDTO {

    constructor(
        
        public description: string,
        public amount: number,
        public bankaccount: Bankaccount,
        public origin: boolean
        ){}
            }