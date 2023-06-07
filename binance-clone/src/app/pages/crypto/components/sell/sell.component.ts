import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  mostrarDiv: boolean = true;
  divProcesando:boolean=true;
  divVenta:boolean=true;
  divVentaExitosa=true;
  ARS = [];
  BTC=[];
  total=0;
  valorCripto=0;
  faMastercard = faCcMastercard;
  faRight = faArrowRight;
  faSand = faHourglassHalf;
  resultadoMultiplicacion=0;
  cantidadCripto=0;

  inputValor=0;

  constructor(private getCoin: CryptoService,private router:Router) {

  }
  ngOnInit() {
    this.getARS();
    // this.getBTC();
  
  }

   mostrarValorConsola() {
    // console.log(this.cantidadCripto);
    this.inputValor = this.cantidadCripto;
    let totalCompra = this.inputValor * this.valorCripto;
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






}


      ocultar(){
        this.mostrarDiv=!this.mostrarDiv;
      }

      volverDiv(){
        this.mostrarDiv=this.mostrarDiv=true;
      }

      procesando(){
        this.divVenta=!this.divVenta;
        this.divProcesando =!this.divProcesando;
        this.VentaRealizada();
      }
    
      VentaRealizada(){
        setTimeout(()=>{
          this.divProcesando=true
          this.divVentaExitosa=!this.divVentaExitosa
          setTimeout(() => {
            this.redirigir();
          },3000 );
        },3000)

        // setTimeout(() => {
        // this.divProcesando=true
        // this.divVentaExitosa=!this.divVentaExitosa
        // }, 3000);
        // setTimeout(() => {
        //  this.redirigir();
        //   }, 3000);

      }

      redirigir(){
       this.router.navigate(['/landing'])
      }
      
}

