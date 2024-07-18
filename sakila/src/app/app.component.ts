import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AjaxWaitComponent, FooterComponent, HeaderComponent, NotificationComponent, NotificationModalComponent } from './main';
import { NavigationService } from './common-services';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NotificationComponent, NotificationModalComponent, AjaxWaitComponent, HeaderComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor(private _nav: NavigationService) { }
}
