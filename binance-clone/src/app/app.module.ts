import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { LandingComponent } from './landing/landing.component';
import { DashboardComponent } from './core/dashboard/dashboard.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SideBarComponent } from './core/side-bar/side-bar.component';

import { NumberShortenerPipe } from './pipes/number-shortener.pipe';
import { BuyComponent } from './pages/crypto/components/buy/buy.component';
import { SellComponent } from './pages/crypto/components/sell/sell.component';
import { CryptoComponent } from './pages/crypto/crypto.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LandingComponent,
    DashboardComponent,
    SideBarComponent,
    NumberShortenerPipe,
    BuyComponent,
    SellComponent,
    CryptoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
