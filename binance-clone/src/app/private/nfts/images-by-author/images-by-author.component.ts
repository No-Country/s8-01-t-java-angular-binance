import { Component, OnInit } from '@angular/core';
import { GalleryService } from '../../../services/gallery.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Nft } from 'src/app/helpers/interfaces';
import { ModalComponent } from 'src/app/shared/modal/modal.component';
import { CarouselComponent } from 'src/app/shared/carousel/carousel.component';
import { JumbotronComponent } from 'src/app/shared/jumbotron/jumbotron.component';

@Component({
  standalone: true,
  imports: [
    MaterialModule,
    CommonModule,
    HttpClientModule,
    CarouselComponent,
    JumbotronComponent,
  ],
  selector: 'app-images-by-author',
  templateUrl: './images-by-author.component.html',
  styleUrls: ['./images-by-author.component.scss'],
})
export class ImagesByAuthorComponent implements OnInit {
  public nfts: any[] = [];

  constructor(
    private gallery: GalleryService,
    private http: HttpClient,
    private readonly dialog: MatDialog
  ) {}

  public async ngOnInit(): Promise<void> {
    const nfts = await this.gallery.getImagesByAuthor();
    this.nfts = await Promise.all(
      nfts.map(async (nft) => {
        const metaData: any = await this.http
          .get(nft.imageMetaDataUrl)
          .toPromise();
        return {
          title: nft.title,
          price: metaData.price,
          contract: metaData.contract,
          date: metaData.date,
          fileUrl: metaData.fileUrl,
          description: metaData.description,
        };
      })
    );
  }

  detailNft(nft: Nft) {
    const dialogConfig: MatDialogConfig = {
      data: { ...nft },
      panelClass: 'custom-modalbox',
      // Resto de las opciones del modal
    };

    this.dialog.open(ModalComponent, dialogConfig);
  }
}
