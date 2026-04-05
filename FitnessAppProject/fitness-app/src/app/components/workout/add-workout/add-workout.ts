import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../../services/api'; // Verify your path
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-workout',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './add-workout.html',
  styleUrls: ['./add-workout.css']
})
export class AddWorkoutComponent implements OnInit {

  workout = {
    type: '',
    duration: null,
    caloriesBurned: null,
    date: new Date().toISOString().split('T')[0],
    userId: 0
  };

  constructor(private api: ApiService, private router: Router) {}

  ngOnInit() {
    this.workout.userId = this.getUserIdFromToken();
  }

  getUserIdFromToken(): number {
    const token = localStorage.getItem('token');
    if (!token) return 0;
    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.userId; // Matches your backend claim
  }

  addWorkout() {
    this.api.addWorkout(this.workout).subscribe({
      next: (res) => {
        alert('Workout saved! Keep it up! 🔥');
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        console.error('Error:', err);
        alert('Failed to save workout.');
      }
    });
  }
}