import { Component, OnInit, OnDestroy, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePipe, } from '@angular/common';
import { PaginatorModule } from 'primeng/paginator';
import { ErrorMessagePipe, TypeValidator } from '@my/core';
import { ActoresViewModelService } from './servicios.service';

@Component({
    selector: 'app-actores-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [RouterLink, PaginatorModule]
})
export class ActoresListComponent implements OnChanges, OnDestroy {
  @Input() page = 0

  constructor(protected vm: ActoresViewModelService) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    this.vm.load(this.page)
  }
  ngOnDestroy(): void { this.vm.clear(); }
}
@Component({
    selector: 'app-actores-add',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [FormsModule, TypeValidator, ErrorMessagePipe]
})
export class ActoresAddComponent implements OnInit {
  constructor(protected vm: ActoresViewModelService) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.add();
  }
}
@Component({
    selector: 'app-actores-edit',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [FormsModule, TypeValidator, ErrorMessagePipe]
})
export class ActoresEditComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: ActoresViewModelService, protected router: Router) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}
@Component({
    selector: 'app-actores-view',
    templateUrl: './tmpl-view.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [DatePipe]
})
export class ActoresViewComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: ActoresViewModelService, protected router: Router) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}

export const ACTORES_COMPONENTES = [
  ActoresListComponent, ActoresAddComponent, ActoresEditComponent, ActoresViewComponent,
];
