import { Component, inject } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  standalone: true,
  templateUrl: './private.component.html',
  styleUrls: ['./private.component.css'],
})
export class PrivateComponent {
  constructor(private authService: AuthService) {}

  get user() {
    const user = this.authService.userValue;
    return JSON.stringify(user, null, 2);
  }

  onLogout() {
    this.authService.logout();
  }
}
