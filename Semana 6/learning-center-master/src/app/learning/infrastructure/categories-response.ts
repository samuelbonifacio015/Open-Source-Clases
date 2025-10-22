import {BaseResource, BaseResponse} from '../../shared/infrastructure/base-response';

/**
 * Represents the API resource/DTO for a category.
 */
export interface CategoryResource extends BaseResource {
  /**
   * The unique identifier for the category.
   */
  id: number;

  /**
   * The name of the category.
   */
  name: string;

}

/**
 * Represents the API response structure for a list of categories.
 */
export interface CategoriesResponse extends BaseResponse {
  /**
   * The list of categories returned by the API.
   */
  categories: CategoryResource[];
}
