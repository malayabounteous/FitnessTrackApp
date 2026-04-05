import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../../services/api'; // Adjust path to your service
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-meal',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './add-meal.html',
  styleUrls: ['./add-meal.css']
})
export class AddMealComponent implements OnInit {

  meal = {
    name: '',
    calories: null,
    protein: null,
    carbs: null,
    fats: null,
    mealType: '',
    date: new Date().toISOString().split('T')[0], // Default to today
    userId: 0
  };

  constructor(private api: ApiService, private router: Router) {}

  ngOnInit() {
    this.meal.userId = this.getUserIdFromToken();
  }

  // Helper to get ID from your JWT
  getUserIdFromToken(): number {
    const token = localStorage.getItem('token');
    if (!token) return 0;
    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.userId; 
  }

  addMeal() {
    if (!this.meal.name || !this.meal.mealType) {
      alert('Please fill in the required fields');
      return;
    }

    console.log('Sending Meal to Backend:', this.meal);

    this.api.addMeal(this.meal).subscribe({
      next: (res) => {
        alert('Meal added successfully! 💪');
        this.router.navigate(['/dashboard']); // Go back to see the updated charts
      },
      error: (err) => {
        console.error('Error adding meal:', err);
        alert('Failed to save meal. Check console.');
      }
    });
  }
}