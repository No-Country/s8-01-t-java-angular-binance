import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './private/landing/landing.component';
import { DashboardComponent } from './core/dashboard/dashboard.component';
import { NftComponent } from './private/nfts/nft/nft.component';
import { UploadImageComponent } from './private/nfts/upload-image/upload-image.component';
import { ImagesByAuthorComponent } from './private/nfts/images-by-author/images-by-author.component';
import { HomeComponent } from './private/nfts/home/home.component';
import { CryptoComponent } from './pages/crypto/crypto.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'landing',
    pathMatch: 'full',
  },
  // Admin
  {
    path: 'admin',
    loadComponent: () =>
      import('./admin/pages/main/admin.component').then(
        (c) => c.AdminComponent
      ),
    data: { isAdminComponent: true },
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
  },

  // Private
  {
    path: 'landing',
    component: LandingComponent,
    title: 'Binance: Exchange de criptomonedas de Bitcoin, Ethereum y altcoins',
  },
  { path: 'home', component: HomeComponent },
  { path: 'author', component: ImagesByAuthorComponent },
  { path: 'upload', component: UploadImageComponent },
  { path: 'nft', component: NftComponent },
  { path: 'nft/:id', component: NftComponent },

  // Core
  {
    path: 'register',
    loadComponent: () =>
      import('./core/auth/sign-up/sign-up.component').then(
        (c) => c.SignUpComponent
      ),
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./core/auth/sign-in/sign-in.component').then(
        (c) => c.SignInComponent
      ),
  },
  {
    path: 'crypto',
    component: CryptoComponent,
    title: 'Buy/Sell Bitcoin, Ether and Altcoins | Cryptocurrency Exchange | Binance'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
