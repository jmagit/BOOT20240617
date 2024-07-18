/* eslint-disable @typescript-eslint/no-explicit-any */
import { HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { LoggerService } from '@my/core';
import { environment } from 'src/environments/environment';
import { ViewModelService } from '../code-base';
import { NotificationService, NavigationService, PeliculasDAOService, IdiomasDAOService, CategoriasDAOService, ActoresDAOService } from '../common-services';
import { AuthService } from '../security';

@Injectable({
  providedIn: 'root'
})
export class PeliculasViewModelService extends ViewModelService<any, number> {
  readonly roleMantenimiento = environment.roleMantenimiento

  constructor(notify: NotificationService, out: LoggerService , auth: AuthService, router: Router, navigation: NavigationService,
    dao: PeliculasDAOService, protected daoIdiomas: IdiomasDAOService, protected daoCategorias: CategoriasDAOService, protected daoActores: ActoresDAOService
  ) {
    super(dao, { rating: 'G' }, notify, out, auth, router, navigation)
    // Soluciona el problema de las clases JavaScript por el cual los métodos pierden la referencia a this cuando se referencian por nombre (ExecPipe)
    this.dameActor = this.dameActor.bind(this)
    this.dameCategoria = this.dameCategoria.bind(this)
  }

  public override view(key: any): void {
    this.dao.get(key, {params: new HttpParams().set('mode', 'details')}).subscribe({
      next: data => {
        this.elemento = data;
        this.modo = 'view';
      },
      error: err => this.handleError(err)
    });
  }
  // Paginado

  page = 0;
  totalPages = 0;
  totalRows = 0;
  rowsPerPage = 24;
  load(page: number = -1) {
    if (!page || page < 0) page = this.page;
    (this.dao as PeliculasDAOService).page(page, this.rowsPerPage).subscribe({
      next: rslt => {
        this.page = rslt.page;
        this.totalPages = rslt.pages;
        this.totalRows = rslt.rows;
        this.listado = rslt.list;
        this.modo = 'list';
      },
      error: err => this.handleError(err)
    })
  }

  public idiomas: any[] = [];
  public clasificaciones: any[] = [];
  private actores: any[] = [];
  private categorias: any[] = [];

  public get Actores(): any { return this.actores.filter(item => !this.elemento?.actors?.includes(item.id)); }
  public get Categorias(): any { return this.categorias.filter(item => !this.elemento?.categories?.includes(item.id)); }

  public cargaCategorias() {
    this.daoCategorias.query().subscribe({
      next: data => {
        this.categorias = data;
      },
      error: err => this.handleError(err)
    });
  }

  override cargaListas() {
    if (this.clasificaciones.length === 0)
    (this.dao as PeliculasDAOService).clasificaciones().subscribe({
        next: data => {
          this.clasificaciones = data;
        },
        error: err => this.handleError(err)
      });
    this.cargaCategorias();
    this.daoActores.query().subscribe({
      next: data => {
        this.actores = data;
      },
      error: err => this.handleError(err)
    });
    this.daoIdiomas.query().subscribe({
      next: data => {
        this.idiomas = data;
      },
      error: err => this.handleError(err)
    });
  }


  dameActor(id: number) {
    if (!this?.actores || this.actores.length === 0) return '(sin cargar)'
    const cat = this.actores.find(item => item.id === id)
    return cat ? cat.nombre : 'error'
  }
  addActor(id: number) {
    if(!this.elemento.actors) {
      this.elemento.actors = []
    } else if (this.elemento.actors.includes(id)) {
      this.notify.add('Ya tiene la categoría')
      return
    }
    this.elemento.actors.push(id)
  }
  removeActor(index: number) {
    this.elemento.actors.splice(index, 1)
  }

  dameCategoria(id: number) {
    if (!this?.categorias || this.categorias.length === 0) return '(sin cargar)'
    const cat = this.categorias.find(item => item.id === id)
    return cat ? cat.categoria : 'error'
  }
  addCategoria(id: number) {
    if(!this.elemento.categories) {
      this.elemento.categories = []
    } else if (this.elemento.categories.includes(id)) {
      this.notify.add('Ya tiene la categoría')
      return
    }
    this.elemento.categories.push(id)
  }
  removeCategoria(index: number) {
    this.elemento.categories.splice(index, 1)
  }

  public porCategorias(id: number) {
    this.cargaCategorias();
    this.daoCategorias.peliculas(id).subscribe({
      next: data => {
        this.listado = data;
      },
      error: err => this.handleError(err)
    });
  }
}
