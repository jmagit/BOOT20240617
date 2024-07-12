import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PIPES_CADENAS } from './pipes/cadenas.pipe';
import { SizerComponent } from './components/sizer.component';
import GraficoSvgComponent from './components/grafico-svg/grafico-svg.component';



@NgModule({
  declarations: [ ],
  exports: [ PIPES_CADENAS, SizerComponent, GraficoSvgComponent, ],
  imports: [
    CommonModule, PIPES_CADENAS, SizerComponent, GraficoSvgComponent,
  ]
})
export class MyCoreModule { }
