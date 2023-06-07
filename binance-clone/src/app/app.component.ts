import { Component, OnInit, inject } from '@angular/core';
import {
  ActivatedRoute,
  NavigationEnd,
  NavigationStart,
  Router,
} from '@angular/router';
import { SpinnerService } from './shared/spinner/services/spinner.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  showSpinner: boolean = false;
  title = 'binance-clone';
  isAdminComponent!: boolean;
  loading: boolean = false;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    public spinnerService: SpinnerService
  ) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.isAdminComponent =
          this.activatedRoute.firstChild?.snapshot.data?.['isAdminComponent'];
      }
    });
  }
  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationStart) {
        this.showSpinner = true;
      } else if (event instanceof NavigationEnd) {
        setTimeout(() => {
          this.showSpinner = false;
        }, 2000);
      }
    });

    this.spinnerService.show();
    this.spinnerService.startTimer(3000);
    setTimeout(() => {
      this.loading = true;
    }, 1000);
  }
}
