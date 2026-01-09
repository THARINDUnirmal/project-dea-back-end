import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RegistrationComponent } from './pages/registration/registration';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,RegistrationComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('eventhub-frontend');
}
