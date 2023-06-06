import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Register } from 'src/app/helpers/interfaces/register.model';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { Credentials } from 'src/app/helpers/interfaces/credentials.model';
import { Router } from '@angular/router';
import { Role, User } from '../helpers/interfaces';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  apiUrl = environment.API_URL;
  private userSubject: BehaviorSubject<User | null>;
  public user$: Observable<User | null>;

  constructor(private http: HttpClient, private router: Router) {
    this.userSubject = new BehaviorSubject(
      JSON.parse(localStorage.getItem('user')!)
    );
    this.user$ = this.userSubject.asObservable();
  }

  public get userValue() {
    return this.userSubject.value;
  }

  signIn(credentials: Credentials) {
    const body = credentials;
    return this.http.post<any>(`${this.apiUrl}/auth/login`, body).pipe(
      map((user) => {
        const { username, password } = user;
        const isActive = true;
        let role: Role = 'user';

        if (username === 'antonelaroccuzzo') {
          role = 'admin';
        } else {
          role = 'user';
        }

        const token = {
          username,
          password,
          isActive,
          role,
        };
        localStorage.setItem('token', JSON.stringify(token));
        this.userSubject.next(token);
        return user;
      })
    );
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

  logout() {
    localStorage.clear();
    this.router.navigateByUrl('landing');
  }
}
