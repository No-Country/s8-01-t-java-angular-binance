export interface User {
  id: number;
  email: string;
  username: string;
  legalName: string;
  legalLastName: string;
  birthdate: Date;
  nationality: string;
  fullAddress: string;
  city: string;
  zipCode: string;
  country: string;
  balance: Balance;
}

export interface Balance {
  ARS: number;
  USDT: number;
}
