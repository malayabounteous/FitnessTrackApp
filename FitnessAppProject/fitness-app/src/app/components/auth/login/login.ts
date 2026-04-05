import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,              // 🔥 ADD THIS
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {

  loginData = {
    email: '',
    password: ''
  };

  constructor(private auth: AuthService, private router: Router) {}

 login() {
  this.auth.login(this.loginData).subscribe({
    next: (res: any) => {

      // Store token (only once)
      this.auth.saveToken(res.token);

      // Navigate + reload
      this.router.navigate(['/dashboard']).then(() => {
        window.location.reload();
      });

    },
    error: () => {
      alert("Invalid credentials");
    }
  });
}
}