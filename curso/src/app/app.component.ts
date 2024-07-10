import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SecurityModule } from './security';
import { MyCoreModule } from '@my/core';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SecurityModule, MyCoreModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title: string = 'World';
}
