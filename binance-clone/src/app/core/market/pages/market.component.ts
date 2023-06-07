import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableCryptoComponent } from '../components/table-crypto/table-crypto.component';

@Component({
  selector: 'app-market',
  standalone: true,
  imports: [CommonModule, TableCryptoComponent],
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.scss'],
})
export class MarketComponent {}
