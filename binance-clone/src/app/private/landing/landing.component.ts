import { Component, OnInit } from '@angular/core';

import { faUser, faAngleRight } from '@fortawesome/free-solid-svg-icons';
import { CryptoService } from '../../services/crypto.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss'],
})
export class LandingComponent implements OnInit {
  faUser = faUser;
  faAngleRight = faAngleRight;

  popularCryptos: any[] | undefined;

  constructor(private cryptoService: CryptoService) {}

  ngOnInit(): void {
    this.cryptoService.getPopularCryptos().subscribe({
      next: (res: any) => {
        this.popularCryptos = res;
      },
    });
  }
}
