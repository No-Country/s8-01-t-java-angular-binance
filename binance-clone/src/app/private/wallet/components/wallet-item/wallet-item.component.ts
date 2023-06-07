import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { WalletItem } from '../../interface/wallet.interface';

@Component({
  selector: 'app-wallet-item',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './wallet-item.component.html',
  styleUrls: ['./wallet-item.component.scss'],
})
export class WalletItemComponent {
  @Input() item!: WalletItem;
}
