import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Register } from 'src/interfaces/register.model';
import { Observable, map } from 'rxjs';
import { Credentials } from 'src/interfaces/credentials.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl = environment.API_URL;

  constructor(
    private http: HttpClient
  ) { }

  signIn(credentials: Credentials) {
    const body = credentials;
    return this.http.post<any>(`${this.apiUrl}/auth/login`, body).pipe(map((user) => {
      // localStorage.setItem('user', user);
      localStorage.setItem('user', JSON.stringify(user));
      return user
      
    }));
  }

  // signUp(register: Register){
  //   const userRegister = register;
  //   return this.http.post<any>(`${this.apiUrl}/auth/register`, userRegister).pipe(map((user) => {
  //     console.log('user desde auth',user)
  //     return user;
  //   }));
  // }

  signUp(register: Register) {
    return this.http.post<any>(`${this.apiUrl}/auth/register`, register);
  }

}
