import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
// Initialization for ES Users
import { Modal, Ripple, initTE } from 'tw-elements';
import { ModalService } from './services/modal.service';

import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Nft } from 'src/app/helpers/interfaces';

@Component({
  selector: 'app-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './modal.component.html',
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
    console.log(this.nft.title);
  }

  closeDetail() {
    this.dialogRef.close({ ...this.nft });
  }

  sendId(id: string): void {
    this.router.navigate(['/nft', id]);
  }
}