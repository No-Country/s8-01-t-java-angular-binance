import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  [x: string]: any;
  private mainSubject: BehaviorSubject<string | ''> = new BehaviorSubject<
    string | ''
  >('');
  public main$: Observable<string | ''> = this.mainSubject.asObservable();

  // private componentValue: string = '';

  setComponentValue(value: string) {
    // this.componentValue = value;
    this.mainSubject.next(value);
    this.main$ = this.mainSubject.asObservable();
  }
  getComponentValue() {
    return this.mainSubject.value;
  }
}
