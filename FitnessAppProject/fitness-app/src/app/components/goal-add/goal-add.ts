import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-goal-add',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './goal-add.html',
  styleUrls: ['./goal-add.css']
})
export class GoalAddComponent implements OnInit {

  goal = {
    goalType: '',
    targetValue: null,
    startDate: new Date().toISOString().split('T')[0], // Defaults to today
    endDate: '',
    userId: 0
  };

  constructor(private api: ApiService, private router: Router) {}

  ngOnInit() {
    this.goal.userId = this.getUserIdFromToken();
  }

  getUserIdFromToken(): number {
    const token = localStorage.getItem('token');
    if (!token) return 0;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.userId;
    } catch (e) {
      return 0;
    }
  }

  addGoal() {
    // Assuming your addProgress endpoint handles goals
    this.api.addGoal(this.goal).subscribe({
      next: (res) => {
        alert('Goal Set! Time to crush it! 💪');
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        console.error('Error:', err);
        alert('Could not save goal.');
      }
    });
  }
}