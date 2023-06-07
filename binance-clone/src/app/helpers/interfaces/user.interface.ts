export interface User {
  id?: number;
  email?: string;
  username: string;
  password: string;
  legalName?: string;
  legalLastName?: string;
  birthdate?: Date;
  nationality?: string;
  fullAddress?: string;
  city?: string;
  zipCode?: string;
  country?: string;
  balance?: Balance;
  isActive?: boolean;
  role?: Role;
}

export type Role = 'admin' | 'user';

export interface Balance {
  ARS: number;
  USDT: number;
}
