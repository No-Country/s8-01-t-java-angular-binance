import { Component } from '@angular/core';

@Component({
  selector: 'app-crypto',
  templateUrl: './crypto.component.html',
  styleUrls: ['./crypto.component.scss']
})
export class CryptoComponent {

  buy: boolean = true;
  sell: boolean = false;

  setBuy() {
    this.buy = true;
    this.sell = false;
  }

  setSell() {
    this.sell = true;
    this.buy = false;
  }

}
