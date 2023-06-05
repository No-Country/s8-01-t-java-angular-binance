import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Nft } from 'src/app/helpers/interfaces';

@Injectable({
  providedIn: 'root',
})
export class ModalService {
  private cardDataSubject: BehaviorSubject<Nft | null> =
    new BehaviorSubject<Nft | null>(null);

  constructor() {}
  getCardData() {
    return this.cardDataSubject.asObservable();
  }

  setCardData(nft: Nft) {
    this.cardDataSubject.next(nft);
  }
}
