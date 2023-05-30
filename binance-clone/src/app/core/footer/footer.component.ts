import { Component } from '@angular/core';
import {
  faDiscord,
  faTelegram,
  faTwitter,
  faFacebook,
  faYoutube,
  faTiktok,
  faInstagram,
} from '@fortawesome/free-brands-svg-icons';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
})
export class FooterComponent {
  faDiscord = faDiscord;
  faTelegram = faTelegram;
  faTwitter = faTwitter;
  faFacebook = faFacebook;
  faYoutube = faYoutube;
  faTiktok = faTiktok;
  faInstagram = faInstagram;
}
