import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiService } from '../../../services/api';
import { CommonModule } from '@angular/common';
import { marked } from 'marked';

@Component({
  selector: 'app-recommendation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './recommendation.html',
  styleUrls: ['./recommendation.css']
})
export class RecommendationComponent implements OnInit {
  recommendationHtml: string = '';
  isLoading: boolean = true;

  constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    const userId = this.getUserIdFromToken();
    if (userId) {
      this.api.getRecommendation(userId).subscribe({
        next: (res: any) => {
          // Convert the Markdown string (with **, ##, *) into HTML
          this.recommendationHtml = marked.parse(res.data) as string;
          this.isLoading = false;
          this.cdr.detectChanges();
        },
        error: (err) => {
          console.error('API Error:', err);
          this.recommendationHtml = "<p>Could not generate recommendations. Keep pushing toward your goals!</p>";
          this.isLoading = false;
          this.cdr.detectChanges();
        }
      });
    }
  }

  getUserIdFromToken(): number {
    const token = localStorage.getItem('token');
    if (!token) return 0;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.userId;
    } catch (e) { return 0; }
  }
}