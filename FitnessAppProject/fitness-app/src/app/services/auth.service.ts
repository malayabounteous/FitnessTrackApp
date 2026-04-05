import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/api/auth';

  // 🔥 Reactive login state
  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());
  public isLoggedIn$: Observable<boolean> = this.loggedIn.asObservable();

  constructor(private http: HttpClient) {}

  // ✅ API calls
  register(data: any) {
    return this.http.post(`${this.baseUrl}/register`, data);
  }

  login(data: any) {
    return this.http.post(`${this.baseUrl}/login`, data);
  }

  // ✅ Token handling
  saveToken(token: string) {
    localStorage.setItem('token', token);
    this.loggedIn.next(true); // 🔥 notify all components
  }

  getToken() {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
    this.loggedIn.next(false); // 🔥 notify logout
  }

  // ✅ Helper
  private hasToken(): boolean {
    return !!localStorage.getItem('token');
  }
}