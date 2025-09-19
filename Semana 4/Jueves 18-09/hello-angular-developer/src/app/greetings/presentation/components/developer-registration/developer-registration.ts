import {Component, computed, EventEmitter, Output, Signal, signal} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-developer-registration',
  imports: [
    FormsModule
  ],
  templateUrl: './developer-registration.html',
  styleUrl: './developer-registration.css'
})
export class DeveloperRegistration {

  firstName = signal<string>('');
  lastName = signal<string>('');
  isFormValid: Signal<boolean> = computed(() =>
    this.firstName().trim().length >= 2 && this.lastName().trim().length >= 2
  );

  @Output() public developerRegistered = new EventEmitter<{ firstName: string; lastName: string }>();

  @Output() public registrationDeferred = new EventEmitter<void>();

  public submitRegistrationRequest(): void {
    if (this.isFormValid()) {
      this.developerRegistered.emit({ firstName: this.firstName(), lastName: this.lastName() });
      this.clearFields();
    }
  }

  public clearFields(): void {
    this.firstName.set('');
    this.lastName.set('');
  }

  /**
   * Handles the "Later" action to defer registration.
   * Resets the form and emits the registrationDeferred event.
   */
  public deferRegistration(): void {
    this.registrationDeferred.emit();
    this.clearFields();
  }
}
