import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { GalleryService } from 'src/app/services/gallery.service';
import { CommonModule } from '@angular/common';
import { MetaMaskService } from 'src/app/services/meta-mask.service';
import { MatDialog } from '@angular/material/dialog';
import { NftCardComponent } from '../nft-card/nft-card.component';
import { ModalComponent } from 'src/app/shared/modal/modal.component';
import { Modal, Ripple, initTE } from 'tw-elements';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { BannerComponent } from 'src/app/shared/banner/banner.component';
import { JumbotronComponent } from 'src/app/shared/jumbotron/jumbotron.component';
import { SpinnerService } from 'src/app/shared/spinner/services/spinner.service';
import { SpinnerComponent } from 'src/app/shared/spinner/spinner.component';
import { NavigationEnd, NavigationStart, Router } from '@angular/router';

@Component({
  standalone: true,
  imports: [
    MaterialModule,
    CommonModule,
    HttpClientModule,
    NftCardComponent,
    ModalComponent,
    BannerComponent,
    JumbotronComponent,
    SpinnerComponent,
  ],
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  public images: any[] = [];
  show: boolean = false;
  loading: boolean = false;

  constructor(
    private gallery: GalleryService,
    private http: HttpClient,
    private metaMaskService: MetaMaskService,
    private readonly dialog: MatDialog,
    public spinnerService: SpinnerService,
    private router: Router
  ) {
    const address = this.metaMaskService.getAccountAddress();
    // if (address) {
    //   console.log('Dirección de MetaMask:', address);
    // }
  }

  public async ngOnInit(): Promise<void> {
    // this.metaMaskService.initialize();
    const images = await this.gallery.getAllImages();
    this.images = await Promise.all(
      images.map(async (image) => {
        const metaData: any = await this.http
          .get(image.imageMetaDataUrl)
          .toPromise();
        return {
          title: image.title,
          fileUrl: metaData.fileUrl,
          date: metaData.date,
          price: metaData.price,
          contract: metaData.contract,
          description: metaData.description,
        };
      })
    );

    this.gallery.setImages(this.images); // Establecer el valor del array en el servicio
    initTE({ Modal, Ripple });

    this.spinnerService.show();
    this.spinnerService.startTimer(3000);
    setTimeout(() => {
      this.loading = true;
    }, 1000);
  }
}
