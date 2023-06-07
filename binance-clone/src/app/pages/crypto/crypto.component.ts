import { Component, OnInit } from '@angular/core';
import { CryptoService } from 'src/app/services/crypto.service';

@Component({
  selector: 'app-crypto',
  templateUrl: './crypto.component.html',
  styleUrls: ['./crypto.component.scss']
})
export class CryptoComponent implements OnInit {

  popularCryptos: any[] | undefined;

  operationSelectors: boolean = true;
  buyForm: boolean = true;
  sellForm: boolean = false;

  constructor(private cryptoService: CryptoService) {}

  ngOnInit(): void {
    this.cryptoService.getPopularCryptos().subscribe({
      next: (res: any) => {
        this.popularCryptos = res;
      }
    });
  }

  operationSelectorsToogle() {
    this.operationSelectors = !this.operationSelectors;
  }

  setBuyForm() {
    this.buyForm = true;
    this.sellForm = false;
  }

  setSellForm() {
    this.sellForm = true;
    this.buyForm = false;
  }

}
