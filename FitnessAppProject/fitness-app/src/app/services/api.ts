import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getHeaders() {
  const token = localStorage.getItem('token');

  return {
    headers: new HttpHeaders({
      Authorization: `Bearer ${token}`
    })
  };
}
  // 🔐 AUTH
  login(data: any) {
    return this.http.post(`${this.baseUrl}/auth/login`, data);
  }

  register(data: any) {
    return this.http.post(`${this.baseUrl}/auth/register`, data);
  }

  // 🏋️ WORKOUT
  getWorkouts(userId: number) {
    return this.http.get(`${this.baseUrl}/workouts/user/${userId}`, this.getHeaders());
  }

  addWorkout(data: any) {
    return this.http.post(`${this.baseUrl}/workouts`, data, this.getHeaders());
  }

  // 🍽️ MEAL
  getMeals(userId: number) {
    return this.http.get(`${this.baseUrl}/meals/user/${userId}`, this.getHeaders());
  }

  addMeal(data: any) {
    return this.http.post(`${this.baseUrl}/meals/add`, data, this.getHeaders());
  }

  // 📊 PROGRESS
  getProgress(userId: number) {
    return this.http.get(`${this.baseUrl}/progress/weekly/${userId}`, this.getHeaders());
  }

  addProgress(data: any) {
    return this.http.post(`${this.baseUrl}/progress`, data, this.getHeaders());
  }
  addGoal(data:any)
  {
    return this.http.post(`${this.baseUrl}/goals`, data, this.getHeaders());
  }
  getGoals(userId: number) {
    return this.http.get(`${this.baseUrl}/goals/user/${userId}`, this.getHeaders());
  }
  // 🤖 AI
  getRecommendation(userId: number) {
    return this.http.get(`${this.baseUrl}/recommendations/${userId}`, this.getHeaders());
  }
}