import {BaseEntity} from '../../../shared/infrastructure/base-entity';

/**
 * Represents a Category entity in the application.
 * @remarks
 * This class is used as a domain model for categories in the learning context.
 * It implements the BaseEntity interface to ensure consistency across entities.
 * @see {@link BaseEntity}
 */
export class Category implements BaseEntity {
  /**
   * Creates a new Category instance.
   * @param category - An object containing the id and name of the category.
   * @returns A new instance of Category.
   */
  constructor(category: { id: number; name: string }) {
    this._id = category.id;
    this._name = category.name;
  }

  /**
   * The unique identifier for the category.
   */
  private _id: number;

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  /**
   * The name of the category.
   */
  private _name: string;

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }
}
