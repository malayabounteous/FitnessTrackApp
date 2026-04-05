import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar';
import {CommonModule} from '@angular/common';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HttpClientModule, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
  standalone: true
})
export class App {
  protected readonly title = signal('fitness-app');
  constructor(private router: Router) {}

  showNavbar() {
    const url = this.router.url;

    // ❌ hide navbar on these pages
    return !(url === '/' || url === '/login' || url === '/register');
  }
  logout() {
  localStorage.removeItem('token');
  this.router.navigate(['/login']);
}
}
