import { Component } from '@angular/core';
import { CryptoService } from 'src/app/services/crypto.service';

@Component({
  selector: 'app-crypto',
  templateUrl: './crypto.component.html',
  styleUrls: ['./crypto.component.scss']
})
export class CryptoComponent {

  popularCryptos: any[] | undefined;

  operationSelectors: boolean = true;
  buy: boolean = true;
  sell: boolean = false;

  constructor(private cryptoService: CryptoService) {}

  ngOnInit(): void {
    this.cryptoService.getPopularCryptos().subscribe({
      next: (res: any) => {
        this.popularCryptos = res;
      }
    });
  }

  receiveEvent() {
    this.operationSelectors = !this.operationSelectors;
  }

  setBuy() {
    this.buy = true;
    this.sell = false;
  }

  setSell() {
    this.sell = true;
    this.buy = false;
  }

}
