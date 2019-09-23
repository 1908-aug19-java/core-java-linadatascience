import { Address } from './Address';
import { Company } from './Company';

export class User {
   
    id: number;
    name: string;
    username: string;
    email: string;
    address: Address
    phone: string;
    company: Company;    
}