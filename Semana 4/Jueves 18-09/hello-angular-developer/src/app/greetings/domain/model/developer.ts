/**
 * Represents a developer with a first and last name.
 */
export class Developer {

  /**
   * The first name of the developer.
   * @readonly
   */
  private readonly _firstName: string;

  /**
   * The last name of the developer.
   * @readonly
   */
  private readonly _lastName: string;

  /**
   * Creates a new Developer instance.
   * @param firstName
   * @param lastName
   */
  constructor(firstName: string, lastName: string) {
    this._firstName = firstName;
    this._lastName = lastName;
  }

  /**
   * Gets the full name of the developer.
   * @returns The full name in the format "FirstName LastName".
   */
  get fullName(): string {
    return `${this._firstName} ${this._lastName}`.trim();
  }
}
