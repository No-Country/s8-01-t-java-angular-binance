import { UploadImageComponent } from './../../../private/nfts/upload-image/upload-image.component';
import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef,
  inject,
} from '@angular/core';
import { PanelComponent } from '../../components/panel/panel.component';
import { RouterModule } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';
import { ProfileComponent } from '../../components/profile/profile.component';
import { UserListComponent } from '../../components/user-list/user-list.component';
import { HomeComponent } from './../../../private/nfts/home/home.component';

import { DashboardComponent } from 'src/app/core/dashboard/dashboard.component';
import { SignUpComponent } from 'src/app/core/auth/sign-up/sign-up.component';
import { ImagesByAuthorComponent } from 'src/app/private/nfts/owner-nft/images-by-author.component';

@Component({
  standalone: true,
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
  imports: [
    CommonModule,
    RouterModule,
    PanelComponent,
    ProfileComponent,
    UserListComponent,
    SignUpComponent,
    HomeComponent,
  ],
})
export class AdminComponent implements OnInit {
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  componentToLoad: any;
  currentComponent: boolean = true;
  componentValue: string | '' = '';
  @ViewChild('dynamicComponentContainer', { read: ViewContainerRef })
  dynamicComponentContainer!: ViewContainerRef;
  private componentFactoryResolver = inject(ComponentFactoryResolver);
  // private adminService = inject(AdminService);
  private subscription: Subscription | undefined;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.currentComponent = true;
    this.subscription = this.adminService.main$.subscribe(
      (value: string | '') => {
        // console.log('Valor de main main:', value);
        this.loadComponent(value);
      }
    );
  }

  loadComponent(Component: string) {
    if (this.dynamicComponentContainer) {
      this.dynamicComponentContainer.clear();

      switch (Component) {
        case 'home':
          this.componentToLoad = HomeComponent;
          this.currentComponent = false;
          break;
        case 'upload':
          this.componentToLoad = UploadImageComponent;
          this.currentComponent = false;
          break;
        case 'author':
          this.componentToLoad = ImagesByAuthorComponent;
          this.currentComponent = false;
          break;
        case 'profile':
          this.componentToLoad = ProfileComponent;
          this.currentComponent = false;
          break;
        case 'users':
          this.componentToLoad = UserListComponent;
          this.currentComponent = false;
          break;
        case 'dashboard':
          this.componentToLoad = DashboardComponent;
          this.currentComponent = false;
          break;
        case 'register':
          this.componentToLoad = SignUpComponent;
          this.currentComponent = false;
          break;
      }

      if (this.componentToLoad) {
        const componentFactory =
          this.componentFactoryResolver.resolveComponentFactory(
            this.componentToLoad
          );
        this.dynamicComponentContainer.createComponent(componentFactory);
      }
    }
  }

  // eslint-disable-next-line @angular-eslint/use-lifecycle-interface
  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
