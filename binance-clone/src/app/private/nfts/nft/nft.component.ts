import { MaterialModule } from 'src/app/shared/material/material.module';
import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { GalleryService } from 'src/app/services/gallery.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import {
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { IpfsService } from 'src/app/services/ipfs.service';

@Component({
  selector: 'app-nft',
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    RouterLink,
  ],
  templateUrl: './nft.component.html',
  styleUrls: ['./nft.component.scss'],
})
export class NftComponent implements OnInit {
  public nfts: any;
  public metaDataUrl: any;
  show: boolean = false;
  id!: string;
  public address!: string | null;
  public image!: string | null;
  public title!: string | null;
  public price!: string | null;
  public category!: string | null;
  public description!: string | null;

  public buyForm = this.fb.group({
    title: this.fb.control('', Validators.required),
    category: this.fb.control('Collectibles', Validators.required),
    contract: this.fb.control('', Validators.required),
    price: this.fb.control('', Validators.required),
    date: this.fb.control(
      { value: new Date().toISOString().substring(0, 10), disabled: true },
      Validators.required
    ),
    fileUrl: this.fb.control('', Validators.required),
    description: this.fb.control('', Validators.required),
  });

  constructor(
    private gallery: GalleryService,
    private ipfs: IpfsService,
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder
  ) {}

  public async ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      const id = params.get('id');
      this.id = id !== null ? id : '';
      this.nfts = this.someMethod(this.id);
    });

    const address = this.gallery.getAccountAddress();
    this.buyForm.get('contract')?.setValue(address);

    const image = this.nfts?.fileUrl;
    this.buyForm.get('fileUrl')?.setValue(image);

    const title = this.nfts?.title;
    this.buyForm.get('title')?.setValue(title);

    const price = this.nfts?.price;
    this.buyForm.get('price')?.setValue(price);

    const description = this.nfts?.description;
    this.buyForm.get('description')?.setValue(description);
  }

  public someMethod(title: string): void {
    const images = this.gallery.getImages();
    const foundImage = images.find((image) => image.title === title);
    return foundImage;
  }

  public async buyNft() {
    if (this.buyForm.valid) {
      const title = this.buyForm.get('title')?.value;
      const contract = this.buyForm.get('contract')?.value;
      const category = this.buyForm.get('category')?.value;
      const fileUrl = this.buyForm.get('fileUrl')?.value;
      const price = this.buyForm.get('price')?.value;
      const date = this.buyForm.get('date')?.value;
      const description = this.buyForm.get('description')?.value;
      const metaDataUrl = await this.ipfs.buyNft(
        JSON.stringify({
          fileUrl,
          price,
          contract,
          category,
          date,
          description,
        })
      );

      if (title !== null && title !== undefined) {
        const isItemCreated = await this.gallery.addImage(title, metaDataUrl);

        if (isItemCreated) {
          await this.router.navigate(['/authors-images']);
        }
      } else {
        console.error('form is not valid');
      }
    }
  }
}
