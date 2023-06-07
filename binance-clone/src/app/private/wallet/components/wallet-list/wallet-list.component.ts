import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { WalletItemComponent } from '../wallet-item/wallet-item.component';
import { WalletItem } from '../../interface/wallet.interface';

@Component({
  selector: 'app-wallet-list',
  standalone: true,
  imports: [CommonModule, HttpClientModule, WalletItemComponent],
  templateUrl: './wallet-list.component.html',
  styleUrls: ['./wallet-list.component.scss'],
})
export class WalletListComponent {
  lista!: WalletItem[];

  constructor(private http: HttpClient) {
    this.loadData();
  }

  loadData() {
    this.http.get<WalletItem[]>('./assets/wallet-data.json').subscribe(
      (data) => {
        this.lista = data;
      },
      (error) => {
        console.log('Error loading wallet data:', error);
      }
    );
  }
}
