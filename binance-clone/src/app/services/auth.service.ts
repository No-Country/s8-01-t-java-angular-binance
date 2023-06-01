import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Register } from 'src/interfaces/register.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl = environment.API_URL;

  constructor(
    private http: HttpClient
  ) { }

  // signIn(email: string) {
  //   this.http.post<Auth>(`${this.apiUrl}/register`, { email });
  // }

  signUp(register: Register) {
    this.http.post<Register>(`${this.apiUrl}/register`, { register });
  }


}
