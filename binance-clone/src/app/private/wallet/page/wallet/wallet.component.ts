import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WalletItemComponent } from '../../components/wallet-item/wallet-item.component';
import { WalletListComponent } from '../../components/wallet-list/wallet-list.component';
import { TransactionComponent } from '../../components/transaction/transaction.component';
import { ConvertionCardComponent } from '../../components/convertion-card/convertion-card.component';
import { NavWalletComponent } from '../../components/nav-wallet/nav-wallet.component';
import { environment } from 'src/environments/environment';

import abi from 'abis/Gallery.json';

@Component({
  selector: 'app-wallet',
  standalone: true,
  imports: [
    CommonModule,
    WalletListComponent,
    TransactionComponent,
    ConvertionCardComponent,
    NavWalletComponent,
  ],
  templateUrl: './wallet.component.html',
  styles: [],
})
export class WalletComponent {
  constructor() {}
}
