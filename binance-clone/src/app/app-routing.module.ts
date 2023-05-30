import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './public/landing/landing.component';
import { DashboardComponent } from './private/dashboard/dashboard.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'landing',
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
  },
  {
    path: 'private',
    loadComponent: () =>
      import('./private/prueba/private.component').then(
        c => c.PrivateComponent
      ),
  },
  {
    path: 'landing',
    component: LandingComponent,
    title: 'Binance: Exchange de criptomonedas de Bitcoin, Ethereum y altcoins',
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./auth/pages/sign-up/sign-up.component').then(
        c => c.SignUpComponent
      ),
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./auth/pages/sign-in/sign-in.component').then(
        c => c.SignInComponent
      ),
  },
  {
    path: '**',
    component: LandingComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
