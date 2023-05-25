import { Component,EventEmitter, OnInit, Output,HostListener } from '@angular/core';

import { faUser } from '@fortawesome/free-solid-svg-icons';
import { faShield } from '@fortawesome/free-solid-svg-icons';
import { faIdCard } from '@fortawesome/free-solid-svg-icons';
import { faCoins } from '@fortawesome/free-solid-svg-icons';
import { faUserPlus } from '@fortawesome/free-solid-svg-icons';
import { faGift } from '@fortawesome/free-solid-svg-icons';
import { faClipboardList } from '@fortawesome/free-solid-svg-icons';
import { faArrowsToDot } from '@fortawesome/free-solid-svg-icons';
import { faUserGroup } from '@fortawesome/free-solid-svg-icons';
import { faGear} from '@fortawesome/free-solid-svg-icons';
import { faArrowRightFromBracket } from '@fortawesome/free-solid-svg-icons';
import { faClose } from '@fortawesome/free-solid-svg-icons';
import { faBars } from '@fortawesome/free-solid-svg-icons';

interface SideNavToggle {
  screenWidth: number;
  collapsed: boolean;
}

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit {

  @Output() onToggleSideNav: EventEmitter<SideNavToggle> = new EventEmitter();
  collapsed = false;
  screenWidth = 0;

  @HostListener('window:resize', ['$event'])
     onResize(event: any) {
      this.screenWidth = window.innerWidth;
        if(this.screenWidth <= 768 ) {
          this.collapsed = false;
          this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
        }
      }

  ngOnInit(): void {
    this.screenWidth = window.innerWidth;
  }

  toggleCollapse(): void {
    this.collapsed = !this.collapsed;
    this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
  }

  closeSidenav(): void {
    this.collapsed = false;
    this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
  }

  faUser= faUser;
  faShield=faShield;
  faIdCard=faIdCard;
  faCoins=faCoins;
  faUserPlus=faUserPlus;
  faGift=faGift;
  faClipboardList=faClipboardList;
  faArrowsToDot=faArrowsToDot;
  faUserGroup=faUserGroup;
  faGear=faGear;
  faArrowRightFromBracket=faArrowRightFromBracket;
  faClose=faClose;
  faBars=faBars;

}
