import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // for *ngIf, *ngFor etc.
import { RouterModule } from '@angular/router'; // <-- important!

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule], // <-- add RouterModule here
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar { }
