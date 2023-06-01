import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Register } from 'src/interfaces/register.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl = environment.API_URL;

  constructor(
    private http: HttpClient
  ) { }

  signIn(password: string, username: string) {
    this.http.post<any>(`${this.apiUrl}/api/v1/auth/login`, { password, username });
  }

  signUp(register: Register){
    this.http.post<any>(`${this.apiUrl}/api/v1/auth/register`, { register });
  }


}
