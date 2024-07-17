import { Routes } from '@angular/router';
import { HomeComponent, PageNotFoundComponent } from './main';

export const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'inicio', component: HomeComponent },
  { path: 'actores', loadChildren: () => import('./actores/modulo.module').then(mod => mod.ActoresModule) },



  { path: '404.html', component: PageNotFoundComponent },
  { path: '**', component: PageNotFoundComponent },
];
