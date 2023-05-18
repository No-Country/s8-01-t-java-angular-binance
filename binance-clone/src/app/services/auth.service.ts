import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {



  constructor(
    private http: HttpClient
  ) { }

  // signUpEmail(email: string) {
  //   this.http.post<Auth>(`${this.apiUrl}/register`, { email });
  // }

  // signUpPassword(password: string) {
  //   this.http.post<Auth>(`${this.apiUrl}/register`, { password });
  // }


}
