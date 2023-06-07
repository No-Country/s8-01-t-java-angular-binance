import { HttpClient } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

import { faEye } from '@fortawesome/free-solid-svg-icons';
import { faArrowDown } from '@fortawesome/free-solid-svg-icons';
import { faChartSimple } from '@fortawesome/free-solid-svg-icons';
import { faArrowsRotate } from '@fortawesome/free-solid-svg-icons';


import { tap } from 'rxjs';
import { Coin } from 'src/interfaces/coin.model';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})

export class DashboardComponent implements OnInit {

  @Input() collapsed = false;

  faEye = faEye;
  faArrowDown = faArrowDown;
  faChart = faChartSimple;
  faArrowRotate = faArrowsRotate;

  userData:any;


  api: string =
  'https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false';
  coins: Coin[] = [];
  titles: string[] = ['#', 'Coin', 'Price', 'Price Change', '24H Volume'];
  searchText: string = '';
  filteredCoins: Coin[] = [];

  constructor (
    private http:HttpClient,
    private auth:AuthService)
    {}

  ngOnInit() {

    this.fetchUserData();

    this.chart();

    this.http.get<Coin[]>(this.api).pipe(
      tap((res) => {
        this.coins = res;
        this.filteredCoins = this.coins;
      })
    ).subscribe({
      error: (err) => console.error(err)
    });
  }

  private fetchUserData(){
    this.auth.getUserData().subscribe(
      (data)=>{
        this.userData=data;
      },
      (err) => {
        console.error('An error occurred:', err);
      }
    )
  }

  chart(){
    const chart = new Chart('chart', {
      type: 'line',
      data:{
        labels: ['04-11', '04-15', '04-19', '04-27', '05-01', '05-05', '05-10'],
        datasets: [{
          label: '',
          data: [1000, 1000, 1000, 1000, 1000, 1000,1000],
          borderWidth: 1,
          borderColor:'#F0B90B',
          backgroundColor: this.getDataColors(70),
          fill: true,
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }
 getDataColors = (opacity: number): string => {
  const baseColor = '#F0B90B';
  return `${baseColor}${opacity}`;
  };

  searchCoin() {
    this.filteredCoins = this.coins.filter(
      (coin) =>
        coin.name.toLowerCase().includes(this.searchText.toLowerCase()) ||
        coin.symbol.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

}
