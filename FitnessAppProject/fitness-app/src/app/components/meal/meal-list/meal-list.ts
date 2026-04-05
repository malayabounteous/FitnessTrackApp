import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiService } from '../../../services/api';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-meal-logs',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './meal-list.html',
  styleUrls: ['./meal-list.css']
})
export class MealLogsComponent implements OnInit {
  meals: any[] = [];

  constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    const userId = this.getUserIdFromToken();
    this.api.getMeals(userId).subscribe({
      next: (res: any) => {
        this.meals = res;
        this.cdr.detectChanges(); // Ensures the table shows up immediately
      },
      error: (err) => console.error('Error fetching meals:', err)
    });
  }

  getUserIdFromToken(): number {
    const token = localStorage.getItem('token');
    if (!token) return 0;
    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.userId;
  }
}