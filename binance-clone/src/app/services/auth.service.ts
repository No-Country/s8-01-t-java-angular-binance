import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

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


}
