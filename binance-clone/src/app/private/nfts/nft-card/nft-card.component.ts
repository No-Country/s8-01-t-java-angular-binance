import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { Router, RouterModule } from '@angular/router';
import { Modal, Ripple, initTE } from 'tw-elements';
import { ModalComponent } from 'src/app/shared/modal/modal.component';
import { ModalService } from 'src/app/shared/modal/services/modal.service';
import { Subject } from 'rxjs';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Nft } from 'src/app/helpers/interfaces';

@Component({
  selector: 'app-nft-card',
  standalone: true,
  imports: [CommonModule, MaterialModule, RouterModule, ModalComponent],
  templateUrl: './nft-card.component.html',
})
export class NftCardComponent implements OnInit {
  @Input() nft!: Nft;
  @Input() index!: number;
  public cardData: any;
  public selectedNft: Nft | null = null;
  destroyed$ = new Subject<void>();

  constructor(
    private router: Router,
    private modalDataService: ModalService,
    private readonly dialog: MatDialog
  ) {}

  ngOnInit() {
    initTE({ Modal, Ripple });
  }

  onCardClick(event: Event, nft: Nft) {
    // event.stopPropagation();
    // this.modalDataService.setCardData(nft);
  }
  sendId(id: string): void {
    this.router.navigate(['/nft', id]);
  }

  // detailNft(nft: Nft) {

  //   this.dialog.open(ModalComponent, {
  //     data: { ...nft },
  //     width: '800px',
  //     panelClass: 'custom-modalbox',
  //   });
  // }

  detailNft(nft: Nft) {
    const dialogConfig: MatDialogConfig = {
      data: { ...nft },
      panelClass: 'custom-modalbox',
      // Resto de las opciones del modal
    };

    this.dialog.open(ModalComponent, dialogConfig);
  }
}
