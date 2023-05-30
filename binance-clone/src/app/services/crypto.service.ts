import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CryptoService {
  constructor(private httpClient: HttpClient) {}

  getPopularCryptos() {
    return this.httpClient.get(
      'https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=binancecoin%2C%20bitcoin%2C%20ethereum&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en'
    );
  }
}
