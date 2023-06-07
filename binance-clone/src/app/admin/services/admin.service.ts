import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from 'src/app/helpers/interfaces';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private baseUrl: string = environment.API_URL;
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  [x: string]: any;
  private mainSubject: BehaviorSubject<string | ''> = new BehaviorSubject<
    string | ''
  >('');
  public main$: Observable<string | ''> = this.mainSubject.asObservable();

  private http = inject(HttpClient);

  // private componentValue: string = '';

  setComponentValue(value: string) {
    // this.componentValue = value;
    this.mainSubject.next(value);
    this.main$ = this.mainSubject.asObservable();
  }
  getComponentValue() {
    return this.mainSubject.value;
  }

  getAll() {
    return this.http.get<User[]>(`${this.baseUrl}/users`);
  }
  getById(id: number) {
    return this.http.get<User[]>(`${this.baseUrl}/users/${id}`);
  }

  updateUser(id: number, user: User) {
    return this.http.post<User>(`${this.baseUrl}/users/update/${id}`, user);
  }
  deleteUser(id: number) {
    return this.http.get<User>(`${this.baseUrl}/users/delete/${id}`);
  }
}
