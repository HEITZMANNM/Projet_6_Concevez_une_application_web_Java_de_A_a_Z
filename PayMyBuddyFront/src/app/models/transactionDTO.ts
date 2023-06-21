export class TransactionDTO {

    constructor(
        
        public description: string,
        public amount: number,
        public userSenderId: number,
        public userReceiverId: number
        ){}
            }