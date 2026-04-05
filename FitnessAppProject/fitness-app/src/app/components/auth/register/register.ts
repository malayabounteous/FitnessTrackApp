import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {CommonModule} from "@angular/common";
@Component({
  selector: 'app-register',
  templateUrl: './register.html'
  ,imports: [FormsModule, CommonModule]
})
export class RegisterComponent {

  user = {
    name: '',
    email: '',
    password: '',
    age: 0,
    height: 0,
    weight: 0
  };

  constructor(private auth: AuthService, private router: Router) {}

  register() {
  if (!this.user.name || !this.user.email || !this.user.password) {
    alert("Please fill all required fields");
    return;
  }

  this.auth.register(this.user).subscribe({
    next: () => {
      alert("Registered Successfully");
      this.router.navigate(['/login']);
    },
    error: () => {
      alert("Registration failed");
    }
  });
}
}