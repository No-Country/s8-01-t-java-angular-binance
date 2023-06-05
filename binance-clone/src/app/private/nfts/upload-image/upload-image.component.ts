import { Component, OnInit } from '@angular/core';
import { IpfsService } from '../../../services/ipfs.service';
import {
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { GalleryService } from '../../../services/gallery.service';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MetaMaskService } from 'src/app/services/meta-mask.service';

@Component({
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    HttpClientModule,
  ],
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.scss'],
})
export class UploadImageComponent implements OnInit {
  public uploadForm = this.fb.group({
    title: this.fb.control('No-Country #001', Validators.required),
    category: this.fb.control('Collectibles', Validators.required),
    contract: this.fb.control('', Validators.required),
    price: this.fb.control('46.4', Validators.required),
    date: this.fb.control(
      { value: new Date().toISOString().substring(0, 10), disabled: true },
      Validators.required
    ),
    fileUrl: this.fb.control('', Validators.required),
    description: this.fb.control('Art-Country', Validators.required),
  });
  public uploadedImage = '';
  public formError = '';
  public isLoading = false;
  public address!: string | null;

  constructor(
    private ipfs: IpfsService,
    private fb: FormBuilder,
    private router: Router,
    private gallery: GalleryService,
    private metaMaskService: MetaMaskService
  ) {}

  ngOnInit(): void {
    const address = this.metaMaskService.getAccountAddress();
    this.uploadForm.get('contract')?.setValue(address);
  }

  public async uploadImage(eventTarget: any) {
    const fileUrl = await this.ipfs.uploadFile(eventTarget.files[0]);
    this.uploadedImage = fileUrl;
    this.uploadForm.get('fileUrl')?.setValue(fileUrl);
  }

  public async onSubmit() {
    if (this.uploadForm.valid) {
      this.isLoading = true;
      const title = this.uploadForm.get('title')?.value;
      const contract = this.uploadForm.get('contract')?.value;
      const category = this.uploadForm.get('category')?.value;
      const fileUrl = this.uploadForm.get('fileUrl')?.value;
      const price = this.uploadForm.get('price')?.value;
      const date = this.uploadForm.get('date')?.value;
      const description = this.uploadForm.get('description')?.value;
      const metaDataUrl = await this.ipfs.uploadFile(
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

        this.isLoading = false;
        if (isItemCreated) {
          await this.router.navigate(['/author']);
        }
      } else {
        console.error('form is not valid');
        this.formError = 'Form is not valid';
      }
    }
  }
}
