import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiService } from '../../services/api'; // Ensure correct path
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-goal',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './goal.html',
  styleUrls: ['./goal.css']
})
export class GoalComponent implements OnInit {
  goals: any[] = [];

  constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    const userId = this.getUserIdFromToken();
    if (userId) {
      this.api.getGoals(userId).subscribe({
        next: (res: any) => {
          this.goals = res;
          console.log('Goals loaded:', res);
          this.cdr.detectChanges(); // Force UI refresh for async data
        },
        error: (err) => console.error('Error fetching goals:', err)
      });
    }
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
  // Inside your GoalComponent class
isExpired(endDate: string): boolean {
  if (!endDate) return false;
  const today = new Date();
  today.setHours(0, 0, 0, 0); // Reset time to compare only dates
  const end = new Date(endDate);
  return end < today;
}
}