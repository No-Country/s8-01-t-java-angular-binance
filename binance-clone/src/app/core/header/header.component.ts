import { Component, OnInit, inject } from '@angular/core';
import {
  faMoon,
  faGlobe,
  faBars,
  faX,
  faChartSimple,
  faCoins,
} from '@fortawesome/free-solid-svg-icons';
import { faCircle } from '@fortawesome/free-regular-svg-icons';
import { Collapse, Dropdown, initTE } from 'tw-elements';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/helpers/interfaces';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  user!: User;
  Active: boolean = false;
  private userSubscription!: Subscription;
  private authService = inject(AuthService);

  ngOnInit(): void {
    this.userSubscription = this.authService.user$.subscribe(
      (user: User | null) => {
        // Verificar si el usuario está logueado y su rol es 'admin'
        if (user && user.role === 'admin') {
          // Usuario logueado con rol de administrador
          console.log('Usuario logueado con rol de administrador');
        } else if (user) {
          // Usuario logueado con rol diferente a 'admin'
          console.log('Usuario logueado con rol diferente a administrador');
        } else {
          // No hay usuario logueado
          console.log('No hay usuario logueado');
        }
      }
    );
  }

  faMoon = faMoon;
  faGlobe = faGlobe;
  faBars = faBars;
  faCircle = faCircle;
  isMenuOpen = false;
  faX = faX;
  faChart = faChartSimple;
  faCoin = faCoins;

  menu() {
    this.isMenuOpen = !this.isMenuOpen;
    document.body.classList.toggle('overflow');
  }

  ngOnDestroy() {
    if (this.userSubscription) {
      this.userSubscription.unsubscribe();
    }
  }
}
