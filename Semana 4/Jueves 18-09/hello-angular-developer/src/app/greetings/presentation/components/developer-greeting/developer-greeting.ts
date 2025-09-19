import {Component, computed, input} from '@angular/core';
import {Developer} from '../../../domain/model/developer';

/**
 * Component that displays a greeting message for a developer.
 * It shows the developer's full name if provided, otherwise defaults to 'Anonymous Developer'.
 * It also indicates whether the developer is registered based on the presence of a first name or last name.
 */
@Component({
  selector: 'app-developer-greeting',
  imports: [],
  templateUrl: './developer-greeting.html',
  styleUrl: './developer-greeting.css'
})
export class DeveloperGreeting {

  firstName = input<string>('');
  lastName = input<string>('');

  /**
   * Computes the full name of the developer.
   * If both first name and last name are empty, returns 'Anonymous Developer'.
   * @returns The full name of the developer or 'Anonymous Developer'.
   */
  fullName = computed(() => {
    if (!this.firstName && !this.lastName) {
      return 'Anonymous Developer';
    }
    const developer = new Developer(this.firstName(), this.lastName());
    return developer.fullName;
  });

  /**
   * Indicates whether the developer is registered (i.e., has a first name or last name).
   * @returns True if the developer is registered, false otherwise.
   */
  isRegistered = computed(() => !!this.firstName() || !!this.lastName());
}
