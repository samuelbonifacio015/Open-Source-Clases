import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {DeveloperGreeting} from './greetings/presentation/components/developer-greeting/developer-greeting';
import {DeveloperRegistration} from './greetings/presentation/components/developer-registration/developer-registration';

@Component({
  selector: 'app-root',
  imports: [DeveloperGreeting, DeveloperRegistration],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('hello-angular-developer');

  public firstName : string = '';
  public lastName : string = '';

  public updateRegisteredDeveloperInfo(developer: { firstName: string; lastName: string }): void {
    this.firstName = developer.firstName;
    this.lastName = developer.lastName;
  }

  public resetRegisteredDeveloperInfo(): void {
    this.firstName = '';
    this.lastName = '';
  }

}
