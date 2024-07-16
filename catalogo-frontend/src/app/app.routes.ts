import { Routes } from '@angular/router';
import { HomeComponent, PageNotFoundComponent } from './main';

export const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'inicio', component: HomeComponent },


  { path: '**', component: PageNotFoundComponent },
];
