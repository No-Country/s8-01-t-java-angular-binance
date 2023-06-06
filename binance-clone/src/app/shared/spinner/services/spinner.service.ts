import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SpinnerService {
  private showSpinner: boolean = false;
  private spinnerTimer: any;

  constructor() {}

  show(): void {
    this.showSpinner = true;
  }

  hide(): void {
    this.showSpinner = false;
  }

  startTimer(duration: number): void {
    this.spinnerTimer = setTimeout(() => {
      this.hide();
    }, duration);
  }

  resetTimer(): void {
    clearTimeout(this.spinnerTimer);
  }

  getSpinnerStatus(): boolean {
    return this.showSpinner;
  }
}
