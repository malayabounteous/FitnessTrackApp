import { Component, OnInit, ViewChild, ElementRef, AfterViewInit,ChangeDetectorRef } from '@angular/core';
import { ApiService } from '../../../services/api';
import { CommonModule } from '@angular/common';
import Chart from 'chart.js/auto';

@Component({
  selector: 'app-progress',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './progress-chart.html',
  styleUrls: ['./progress-chart.css']
})

export class ProgressComponent implements OnInit {
  @ViewChild('weightChart') weightChartCanvas!: ElementRef;
  @ViewChild('calorieChart') calorieChartCanvas!: ElementRef;

  // Track chart instances to prevent duplicates
  private weightChartInst: any;
  private calorieChartInst: any;

  progressData: any[] = [];
  public caloriesConsumed: number = 0;
  public caloriesBurned: number = 0;
  public weightChange: number = 0;

  constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    const userId = this.getUserIdFromToken();
    this.api.getProgress(userId).subscribe((res: any) => {
      // 1. Correct the mappings from your backend
      console.log("Progress API Response:", res); // Debug log
      this.progressData = res.dailyStats;
      this.caloriesConsumed = res.totalWeeklyConsumed; // Fixed
      this.caloriesBurned = res.totalWeeklyBurned;     // Fixed
      this.weightChange = res.weightChange;

      // 2. Wait for Angular to render the HTML elements
      this.cdr.detectChanges(); 
      
      // 3. Render charts
      this.createCharts();
    });
  }

  getUserIdFromToken(): number {
    const token = localStorage.getItem('token');
    if (!token) return 0;
    try {
      return JSON.parse(atob(token.split('.')[1])).userId;
    } catch(e) { return 0; }
  }

  createCharts() {
    // Safety check: Ensure Canvas elements are available in the DOM
    if (!this.weightChartCanvas || !this.calorieChartCanvas) return;

    // 4. Destroy existing charts if they exist (prevents flickering/memory leaks)
    if (this.weightChartInst) this.weightChartInst.destroy();
    if (this.calorieChartInst) this.calorieChartInst.destroy();

    const labels = this.progressData.map(d => new Date(d.date).toLocaleDateString());
    const weights = this.progressData.map(d => d.weight);
    const consumed = this.progressData.map(d => d.caloriesConsumed);
    const burned = this.progressData.map(d => d.caloriesBurned);

    // 📉 Weight Line Chart
    this.weightChartInst = new Chart(this.weightChartCanvas.nativeElement, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [{
          label: 'Weight (kg)',
          data: weights,
          borderColor: '#00e676',
          backgroundColor: 'rgba(0, 230, 118, 0.2)',
          fill: true,
          tension: 0.4
        }]
      },
      options: { 
        responsive: true, 
        maintainAspectRatio: false, // Better for your "Glass" cards
        plugins: { legend: { labels: { color: 'white' } } },
        scales: {
          y: { ticks: { color: 'rgba(255,255,255,0.7)' }, grid: { color: 'rgba(255,255,255,0.1)' } },
          x: { ticks: { color: 'rgba(255,255,255,0.7)' }, grid: { display: false } }
        }
      }
    });

    

    // 📊 Calorie Comparison Bar Chart
    this.calorieChartInst = new Chart(this.calorieChartCanvas.nativeElement, {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [
          { label: 'Consumed', data: consumed, backgroundColor: '#42a5f5', borderRadius: 5 },
          { label: 'Burned', data: burned, backgroundColor: '#ff7043', borderRadius: 5 }
        ]
      },
      options: { 
        responsive: true, 
        maintainAspectRatio: false,
        plugins: { legend: { labels: { color: 'white' } } },
        scales: {
          y: { ticks: { color: 'rgba(255,255,255,0.7)' }, grid: { color: 'rgba(255,255,255,0.1)' } },
          x: { ticks: { color: 'rgba(255,255,255,0.7)' }, grid: { display: false } }
        }
      }
    });
  }
  
  getAbs(val: number): number {
    return Math.abs(val);
  }
}