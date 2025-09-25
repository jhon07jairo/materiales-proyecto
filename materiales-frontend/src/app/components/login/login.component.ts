import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';
  errorMsg = '';

  constructor(private authService: AuthService) {}

  login() {
    if (!this.username || !this.password) {
      this.errorMsg = 'Usuario y contraseña son obligatorios';
      return;
    }

    this.authService.login(this.username, this.password).subscribe({
      next: () => {
        this.errorMsg = '';
      },
      error: () => {
        this.errorMsg = 'Credenciales inválidas';
      }
    });
  }
}
