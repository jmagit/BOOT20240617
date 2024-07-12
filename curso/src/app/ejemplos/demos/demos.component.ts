import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MyCoreModule } from '@my/core';
import { Unsubscribable } from 'rxjs';
import { NotificationService, NotificationType } from 'src/app/common-services';
import { CalculadoraComponent } from '../calculadora/calculadora.component';
import { NotificationModalComponent } from 'src/app/main';
import { CardComponent } from 'src/app/common-components';

@Component({
  selector: 'app-demos',
  standalone: true,
  imports: [CommonModule, FormsModule, MyCoreModule, CalculadoraComponent, NotificationModalComponent, CardComponent],
  templateUrl: './demos.component.html',
  styleUrl: './demos.component.css',
  providers: [ NotificationService ],
})
export class DemosComponent implements OnInit, OnDestroy  {
  private nombre: string = 'mundo'
  fecha='2024-07-11'
  fontSize = 24
  listado = [
    { id: 1, nombre: 'Madrid'},
    { id: 2, nombre: 'BARCELONA'},
    { id: 3, nombre: 'oviedo'},
    { id: 4, nombre: 'ciudad Real'},
  ]
  idProvincia = 2

  resultado?: string
  visible = true
  estetica = { importante: true, error: false, urgente: true }

  constructor(public vm: NotificationService) { }

  public get Nombre(): string { return this.nombre }
  public set Nombre(value: string) {
    if(this.nombre === value) return
    this.nombre = value
  }

  public saluda(): void {
    this.resultado = `Hola ${this.Nombre}`
  }

  public despide(): void {
    this.resultado = `Adios ${this.Nombre}`
  }

  public di(algo: string): void {
    this.resultado = `Dice ${algo}`
  }

  cambia() {
    this.visible = !this.visible
    this.estetica.error = !this.estetica.error
    this.estetica.importante = !this.estetica.importante
  }

  public calcula(a: number, b: number): number { return a + b }

  public add(provincia: string) {
    const id = this.listado[this.listado.length - 1].id + 1
    this.listado.push({id, nombre: provincia})
    this.idProvincia = id
  }

  // Laboratorio de Notificaciones
  private suscriptor?: Unsubscribable;

  ngOnInit(): void {
    this.suscriptor = this.vm.Notificacion.subscribe(n => {
      // if (n.Type !== NotificationType.error) { return; }
      // window.alert(`Suscripción: ${n.Message}`);
      // this.vm.remove(this.vm.Listado.length - 1);
    });
  }
  ngOnDestroy(): void {
    if (this.suscriptor) {
      this.suscriptor.unsubscribe();
    }
  }

  // Ejemplo de Calculadora
  idiomas = [
    { codigo: 'en-US', region: 'USA' },
    { codigo: 'es', region: 'España' },
    { codigo: 'pt', region: 'Portugal' },
  ];
  idioma = this.idiomas[0].codigo;
  calculos: Calculo[] = [];
  valCalculadora = 777;

  ponResultado(origen: string, valor: number) {
    this.calculos.push({
      pos: this.calculos.length + 1,
      origen,
      valor
    });
  }

}
interface Calculo {
  pos: number
  origen: string
  valor: number
}
