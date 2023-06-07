import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { faAngleLeft, faAngleRight, faArrowRight } from '@fortawesome/free-solid-svg-icons'
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
  faArrowRight = faArrowRight;
  
  cryptoModal: boolean = false;
  fiatModal: boolean = false;
  paymentMethod: boolean = false;
  
  formStep: number = 1;
  backendCryptos = [];
  selectedCrypto: any | undefined;
  ars: any | undefined;
  wallet: any | undefined;

  buyForm: FormGroup = new FormGroup({});

  cryptoService = inject(CryptoService);
  formBuilder = inject(FormBuilder);

  ngOnInit(): void {
    this.getCoins();
    this.getWallet();
    this.buyForm = this.formBuilder.group({
      purchaseAmount: ['', Validators.required],
      purchaseCoinId: ['', Validators.required],
      saleAmount: ['', Validators.required],
      saleCoinId: [5],
      walletId: [1, Validators.required]
    })
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['cryptos'] && changes['cryptos'].currentValue) {
      this.selectedCrypto = changes['cryptos'].currentValue[0];
    }
  }

  emitEvent() {
    this.event.emit();
  }

  showCryptoModal() {
    this.cryptoModal = !this.cryptoModal;
    document.body.classList.toggle('overflow');
  }

  showFiatModal() {
    this.fiatModal = !this.fiatModal;
    document.body.classList.toggle('overflow');
  }

  increaseStep() {
    this.formStep ++;
  }

  decreaseStep() {
    this.formStep--;
  }

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

  togglePaymentMethod() {
    this.paymentMethod = !this.paymentMethod;
  }

  getCoins() {
    this.cryptoService.getCoins().subscribe({
      next: (res: any) => {
        this.backendCryptos = res;
        this.ars = res.filter((crypto: any) => crypto.name === 'ARS');
      }
    });
  }

  getWallet() {
    this.cryptoService.getWallet().subscribe({
      next: (res: any) => {
        this.wallet = res.ARS;
      }
    });
  }

  buy() {
    this.cryptoService.buy(this.buyForm.getRawValue()).subscribe({
      next: (res) => {
      },
      error: (err) => {
        console.log(err);
      },
      complete: () => {
        this.increaseStep();
      }
    });
  }

}
