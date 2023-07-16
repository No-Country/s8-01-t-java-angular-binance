import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './core/header/header.component';
import { FooterComponent } from './core/footer/footer.component';
import { LandingComponent } from './private/landing/landing.component';
import { DashboardComponent } from './core/dashboard/dashboard.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SideBarComponent } from './core/side-bar/side-bar.component';

import { NumberShortenerPipe } from './pipes/number-shortener.pipe';
import { ButtonComponent } from './components/button/button.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SpinnerComponent } from './shared/spinner/spinner.component';
import { CryptoComponent } from './pages/crypto/crypto.component';
import { BuyComponent } from './pages/crypto/components/buy/buy.component';
import { SellComponent } from './pages/crypto/components/sell/sell.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LandingComponent,
    DashboardComponent,
    SideBarComponent,
    NumberShortenerPipe,
    ButtonComponent,
    CryptoComponent,
    BuyComponent,
    SellComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    SpinnerComponent,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
