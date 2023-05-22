import { Component } from '@angular/core';

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

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent {

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

}
