import { Component, OnInit } from '@angular/core';
import { faCcMastercard } from '@fortawesome/free-brands-svg-icons';
import { faArrowRight, faHourglassHalf } from '@fortawesome/free-solid-svg-icons';
import { forEach } from 'lodash';
import { CryptoService } from 'src/app/services/crypto.service';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.scss']
})
export class SellComponent implements OnInit {

  ARS = [];
  BTC=[];
  faMastercard = faCcMastercard;
  faRight = faArrowRight;
  faSand = faHourglassHalf;



  constructor(private getCoin: CryptoService) {

  }
  ngOnInit() {
    this.getARS();
    this.getBTC();
  }


  getARS() {
    this.getCoin.getCoins().subscribe({
      next: (res: any) => {
        this.ARS = res;
        console.log(this.ARS = res.filter((crypto: any) => crypto.name === 'ARS' || crypto.name === 'BTC'));
      }
    });
  }

  getBTC() {
    this.getCoin.getCoins().subscribe({
      next: (res: any) => {
        this.BTC = res;
        console.log(this.BTC);
      }
    });
  }





}


