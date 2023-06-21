import { User } from "./user";

export class Transaction {

constructor(
    public id: number,
    public dateTransaction: Date,
    public description: string,
    public amount: number,
    public fees: number,
    public userSender: User,
    public userReceiver: User
    ){}
        }