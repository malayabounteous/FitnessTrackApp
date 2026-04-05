import { Component, OnInit,ChangeDetectorRef } from '@angular/core';
import { ApiService } from '../../../services/api';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common'; 
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css'] ,
  standalone: true,
  imports:[RouterModule, CommonModule]
})
export class DashboardComponent implements OnInit {

  caloriesConsumed = 0;
  caloriesBurned = 0;

  workouts: any[] = [];
  meals: any[] = [];

  constructor(private api: ApiService, private router: Router,private cdr: ChangeDetectorRef) {}
   logout() {
  localStorage.removeItem('token');
  this.router.navigate(['/login']);
}
getUserIdFromToken(): number {
  const token = localStorage.getItem('token');
  if (!token) return 0;

  const payload = token.split('.')[1]; // get middle part
  const decodedPayload = atob(payload); // decode base64
  const parsed = JSON.parse(decodedPayload);

  return parsed.userId; // must match backend claim name
}
  ngOnInit() {
    const userId = this.getUserIdFromToken(); 
    // WORKOUTS
    this.api.getWorkouts(userId).subscribe((res: any) => {
      this.workouts = res;
      this.caloriesBurned = res.reduce((a: any, b: any) => a + b.caloriesBurned, 0);
      
      this.cdr.detectChanges(); // 3. FORCE THE UI TO UPDATE
      console.log('UI Refresh Triggered for Workouts');
    });

    // MEALS
    this.api.getMeals(userId).subscribe((res: any) => {
      this.meals = res;
      this.caloriesConsumed = res.reduce((a: any, b: any) => a + (b.calories || 0), 0);
      
      this.cdr.detectChanges(); // 4. FORCE THE UI TO UPDATE
    });

    // RECOMMENDATIONS
   
  }
}