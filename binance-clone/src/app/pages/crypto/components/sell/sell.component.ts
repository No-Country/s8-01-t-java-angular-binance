import { Component } from '@angular/core';
import { faCcMastercard } from '@fortawesome/free-brands-svg-icons';
import { faArrowRight,faHourglassHalf } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.scss']
})
export class SellComponent {
  faMastercard=faCcMastercard;
  faRight=faArrowRight;
  faSand=faHourglassHalf;
}
