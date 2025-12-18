import { Component, OnInit, OnDestroy, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePipe, } from '@angular/common';
import { PaginatorModule } from 'primeng/paginator';
import { ErrorMessagePipe, TypeValidator } from '@my/core';
import { ContactosViewModelService } from './servicios.service';

@Component({
    selector: 'app-contactos-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [RouterLink, PaginatorModule]
})
export class ContactosListComponent implements OnChanges, OnDestroy {
  @Input() page = 0

  constructor(protected vm: ContactosViewModelService) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    this.vm.load(this.page)
  }
  ngOnDestroy(): void { this.vm.clear(); }
}
@Component({
    selector: 'app-contactos-add',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [FormsModule, TypeValidator, ErrorMessagePipe]
})
export class ContactosAddComponent implements OnInit {
  constructor(protected vm: ContactosViewModelService) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.add();
  }
}
@Component({
    selector: 'app-contactos-edit',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [FormsModule, TypeValidator, ErrorMessagePipe]
})
export class ContactosEditComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: ContactosViewModelService, protected router: Router) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}
@Component({
    selector: 'app-contactos-view',
    templateUrl: './tmpl-view.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [DatePipe]
})
export class ContactosViewComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: ContactosViewModelService, protected router: Router) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}

export const CONTACTOS_COMPONENTES = [
  ContactosListComponent, ContactosAddComponent, ContactosEditComponent, ContactosViewComponent,
];
