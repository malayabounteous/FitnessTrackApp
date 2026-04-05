import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiService } from '../../../services/api';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-workout-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './workout-list.html',
  styleUrls: ['./workout-list.css']
})
export class WorkoutListComponent implements OnInit {
  workouts: any[] = [];

  constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    const userId = this.getUserIdFromToken();
    if (userId) {
      this.api.getWorkouts(userId).subscribe({
        next: (res: any) => {
          this.workouts = res;
          console.log('Workouts loaded:', res);
          this.cdr.detectChanges(); // Vital for async updates in Standalone components
        },
        error: (err) => console.error('Error fetching workouts:', err)
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
}