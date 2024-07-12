import { Component } from '@angular/core';
import { HomeComponent } from 'src/app/main';
import { DemosComponent } from '../demos/demos.component';
import GraficoSvgComponent from 'src/lib/my-core/components/grafico-svg/grafico-svg.component';
import { NotificationComponent } from "../../main/notification/notification.component";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NotificationComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  menu = [
    { texto: 'inicio', icono: '', componente: HomeComponent },
    { texto: 'demos', icono: '', componente: DemosComponent},
    { texto: 'gráfico', icono: '', componente: GraficoSvgComponent },
  ]
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  actual: any = this.menu[0].componente

  seleccionar(indice: number) {
    this.actual = this.menu[indice].componente
  }
}
