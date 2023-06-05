import { Component } from '@angular/core';
import { faMoon,faGlobe,faBars,faX,faChartSimple,faCoins   } from '@fortawesome/free-solid-svg-icons';
import { faCircle   } from '@fortawesome/free-regular-svg-icons';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  faMoon=faMoon;
  faGlobe=faGlobe;
  faBars=faBars;
  faCircle=faCircle;
  isMenuOpen=false;
  faX=faX;
  faChart=faChartSimple;
  faCoin=faCoins;
  
  menu(){
    this.isMenuOpen=!this.isMenuOpen;
    document.body.classList.toggle('overflow');
  }

}
