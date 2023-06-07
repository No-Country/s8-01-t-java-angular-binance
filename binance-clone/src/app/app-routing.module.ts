import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './landing/landing.component';
import { DashboardComponent } from './core/dashboard/dashboard.component';
import { CryptoComponent } from './pages/crypto/crypto.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'landing',
    pathMatch: 'full'
  },
  {
    path:'dashboard',
    component: DashboardComponent
  },
  {
    path: 'landing',
    component: LandingComponent,
    title: 'Binance: Exchange de criptomonedas de Bitcoin, Ethereum y altcoins'
  },
  {
    path: 'register',
    loadComponent: () => import('./pages/sign-up/sign-up.component').then(c => c.SignUpComponent)
  },
  {
    path: 'login',
    loadComponent: () => import('./pages/sign-in/sign-in.component').then(c => c.SignInComponent)
  },
  {
    path: 'crypto',
    component: CryptoComponent,
    title: 'Buy/Sell Bitcoin, Ether and Altcoins | Cryptocurrency Exchange | Binance'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
