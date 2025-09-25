import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MaterialesComponent } from './pages/materiales/materiales.component';
import { inject } from '@angular/core';
import { AuthService } from './services/auth.service';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'materiales',
    component: MaterialesComponent,
    canActivate: [() => inject(AuthService).isLoggedIn() || ['/login']]
  },
  { path: '**', redirectTo: 'login' }
];
