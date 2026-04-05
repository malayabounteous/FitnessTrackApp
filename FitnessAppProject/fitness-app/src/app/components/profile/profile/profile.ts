import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './profile.html',
  styleUrls: ['./profile.css']
})
export class ProfileComponent implements OnInit {
  userName: string = 'Athlete'; // Default fallback

  ngOnInit() {
    this.extractUserFromToken();
  }

  extractUserFromToken() {
    const token = localStorage.getItem('token');
    
    if (token) {
      try {

        const payloadBase64 = token.split('.')[1];
        const payloadJson = atob(payloadBase64); // Decodes Base64 to String
        const decodedObject = JSON.parse(payloadJson);
        this.userName =  decodedObject.name || 'Athlete';
        
        console.log("Logged in as:", this.userName);
      } catch (error) {
        console.error("Error decoding token:", error);
        this.userName = 'Athlete';
      }
    }
  }
}