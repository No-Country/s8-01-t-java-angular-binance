import { Component } from '@angular/core';

import { faUser } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent {

  faUser = faUser;

}