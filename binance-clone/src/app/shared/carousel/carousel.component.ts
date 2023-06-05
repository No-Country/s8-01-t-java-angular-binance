import { Component, OnInit } from '@angular/core';
import { Carousel, initTE } from 'tw-elements';

@Component({
  standalone: true,
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styles: [],
})
export class CarouselComponent implements OnInit {
  ngOnInit(): void {
    initTE({ Carousel });
  }
}
