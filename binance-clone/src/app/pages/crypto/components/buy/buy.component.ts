import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { faAngleLeft, faAngleRight } from '@fortawesome/free-solid-svg-icons'
import { CryptoService } from 'src/app/services/crypto.service';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.scss']
})
export class BuyComponent implements OnInit, OnChanges {
  
  @Input() cryptos: any[] = [];
  @Output() event = new EventEmitter();

  faAngleLeft = faAngleLeft;
  faAngleRight = faAngleRight;
  
  cryptoModal: boolean = false;
  fiatModal: boolean = false;
  
  formStep: number = 1;
  backendCryptos = [];
  selectedCrypto: any | undefined;
  ars: any | undefined;

  buyForm: FormGroup = new FormGroup({});

  cryptoService = inject(CryptoService);
  formBuilder = inject(FormBuilder);

  ngOnInit(): void {
    this.getCoins();
    this.buyForm = this.formBuilder.group({
      // cantidad de crypto recibida
      purchaseAmount: ['', Validators.required],
      // crypto a comprar
      purchaseCoinId: ['', Validators.required],
      // lo que se gasta
      saleAmount: ['', Validators.required],
      saleCoinId: [5],
      transactionType: ['PURCHASE'],
      walletId: ['', Validators.required]
    })
  }

  /**
   * cuando cryptos recibe el array del padre se asigna la crypto btc por defecto a selectedCrypto
   * @param changes 
   */
  ngOnChanges(changes: SimpleChanges) {
    if (changes['cryptos'] && changes['cryptos'].currentValue) {
      this.selectedCrypto = changes['cryptos'].currentValue[0];
    }
  }

  /**
   * esto sirve para avisarle al padre (crypto.component) si tiene que mostrar u ocultar una porcion de html
   */
  emitEvent() {
    this.event.emit();
  }

  // =============== modals ===============
  showCryptoModal() {
    this.cryptoModal = !this.cryptoModal;
    document.body.classList.toggle('overflow');
  }

  showFiatModal() {
    this.fiatModal = !this.fiatModal;
    document.body.classList.toggle('overflow');
  }

  // =============== step ===============
  increaseStep() {
    this.formStep ++;
  }

  decreaseStep() {
    this.formStep--;
  }

  /**
   * se asigna la crypto elegida por el usuario a selectedCrypto y se
   * llena el formulario con el id correspondiente
   * @param crypto crypto seleccionada por el usuario en el modal de seleccion
   */
  setSelectedCrypto(crypto: any) {
    switch (crypto.symbol) {
      case 'btc':
        this.selectedCrypto = crypto;
        this.buyForm.patchValue({
          purchaseCoinId: 1
        });
        break;
      case 'eth':
        this.selectedCrypto = crypto;
        this.buyForm.patchValue({
          purchaseCoinId: 2
        });
        break;
      case 'bnb':
        this.selectedCrypto = crypto;
        this.buyForm.patchValue({
          purchaseCoinId: 3
        });
        break;    
      default:
        break;
    }
    this.cryptoConversion();
  }

  cryptoConversion() {
    const saleAmount = this.buyForm.getRawValue().saleAmount;
    if (!saleAmount) {
      this.buyForm.patchValue({
        purchaseAmount: ''
      });
    } else {
      const saleAmountInUsd = saleAmount / 475;
      const purchaseAmount = saleAmountInUsd / this.selectedCrypto.current_price;
      this.buyForm.patchValue({
        purchaseAmount: purchaseAmount.toFixed(6)
      });
    }
  }

  fiatConversion() {
    const purchaseAmount = this.buyForm.getRawValue().purchaseAmount;
    if (!purchaseAmount) {
      this.buyForm.patchValue({
        saleAmount: ''
      });
    } else {
      const purchaseAmountInUsd = purchaseAmount * this.selectedCrypto.current_price;
      const saleAmount = purchaseAmountInUsd * 475;
      this.buyForm.patchValue({
        saleAmount: saleAmount.toFixed(2)
      });
    }
  }

  // =============== api backend ===============
  getCoins() {
    this.cryptoService.getCoins().subscribe({
      next: (res: any) => {
        this.backendCryptos = res;
        this.ars = res.filter((crypto: any) => crypto.name === 'ARS');
      }
    });
  }

  buy() {
    console.log('se hizo la compra');
  }

}
