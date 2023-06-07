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
  total=0;
  valorCripto=0;
  faMastercard = faCcMastercard;
  faRight = faArrowRight;
  faSand = faHourglassHalf;
  resultadoMultiplicacion=0;
  cantidadCripto=0;

  constructor(private getCoin: CryptoService) {

  }
  ngOnInit() {
    this.getARS();
    // this.getBTC();
   
  }

   mostrarValorConsola() {
    // console.log(this.cantidadCripto);
    let inputValor = this.cantidadCripto;
    let totalCompra = inputValor * this.valorCripto;
    this.resultadoMultiplicacion=totalCompra;
    console.log(totalCompra);
   }
  // }
  getARS() {
    this.getCoin.getCoins().subscribe({
      next: (res: any) => {
        this.ARS = res;
        console.log(this.ARS = res.filter((crypto: any) => crypto.name === 'ARS' || crypto.name === 'BTC'));
        console.log(this.ARS[0]['usdValue']);
        this.valorCripto= parseFloat(this.ARS[0]['usdValue']); 
      
  
      
        return(this.total);
      
      }
    });
  // getARS() {
  //   this.getCoin.getCoins().subscribe({
  //     next: (res: any) => {
  //       this.ARS = res;
  //       console.log(this.ARS = res.filter((crypto: any) => crypto.name === 'ARS' || crypto.name === 'BTC'));
  //     }
  //   });
  // }

  // getBTC() {
  //   this.getCoin.getCoins().subscribe({
  //     next: (res: any) => {
  //       this.BTC = res;
  //       console.log(this.BTC);
  //     }
  //   });
  // }





}
}

