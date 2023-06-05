import { Component, inject } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  standalone: true,
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrls: ['./panel.component.scss'],
})
export class PanelComponent {
  private adminService = inject(AdminService);
  private authService = inject(AuthService);

  loadComponent(component: string) {
    this.adminService.setComponentValue(component);
  }

  logout() {
    this.authService.logout();
  }
}
