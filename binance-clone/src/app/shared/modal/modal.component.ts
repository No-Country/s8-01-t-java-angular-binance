import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalService } from './services/modal.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Modal, Ripple, initTE } from 'tw-elements';
import { Nft } from 'src/app/helpers/interfaces';

@Component({
  selector: 'app-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './modal.component.html',
  styles: [
    `
      .text-gradient {
        background: linear-gradient(to right, #00ff00, #0000ff);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
      }
    `,
  ],
})
export class ModalComponent implements OnInit {
  constructor(
    private modalService: ModalService,
    private router: Router,
    public readonly dialogRef: MatDialogRef<ModalComponent>,
    @Inject(MAT_DIALOG_DATA) public readonly nft: Nft
  ) {}

  ngOnInit() {
    initTE({ Modal, Ripple });
  }

  closeDetail() {
    this.dialogRef.close({ ...this.nft });
  }

  sendId(id: string): void {
    this.router.navigate(['/nft', id]);
  }
}
