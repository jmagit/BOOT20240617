import { NgModule } from '@angular/core';
import { CONTACTOS_COMPONENTES, ContactosAddComponent, ContactosEditComponent, ContactosListComponent, ContactosViewComponent } from './componente.component';
import { RouterModule, Routes } from '@angular/router';
import { InRoleCanActivate } from '../security';

export const routes: Routes = [
  { path: '', component: ContactosListComponent },
  { path: 'add', component: ContactosAddComponent, canActivate: [ InRoleCanActivate('Empleados')] },
  { path: ':id/edit', component: ContactosEditComponent, canActivate: [ InRoleCanActivate('Empleados')] },
  { path: ':id', component: ContactosViewComponent, canActivate: [ InRoleCanActivate('Empleados')] },
  { path: ':id/:kk', component: ContactosViewComponent, canActivate: [ InRoleCanActivate('Empleados')] },
]
@NgModule({
  declarations: [],
  imports: [ CONTACTOS_COMPONENTES, RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})
export class ContactosModule { }
