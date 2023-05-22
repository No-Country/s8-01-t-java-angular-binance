import { Component, OnInit } from '@angular/core';

import { faUser, faAngleRight } from '@fortawesome/free-solid-svg-icons'
import { CryptoService } from '../services/crypto.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {

  faUser = faUser;
  faAngleRight = faAngleRight;

  positivo: boolean = true;
  popularCryptos: any[] | undefined;

  marketCap: number = 40000000000;

  constructor(private cryptoService: CryptoService) {}

  ngOnInit(): void {

    // this.cryptoService.getPopularCryptos().subscribe({
    //     next: (res: any) => {
    //       console.log(res);
    //       this.popularCryptos = res;
    //     }
    // });

    // setInterval(() => {
    //   this.cryptoService.getPopularCryptos().subscribe({
    //     next: (res: any) => {
    //       console.log(res);
    //       this.popularCryptos = res;
    //     }
    //   })
    // }, 10000);

  }

}
