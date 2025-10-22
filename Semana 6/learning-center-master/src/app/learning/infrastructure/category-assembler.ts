import {BaseAssembler} from '../../shared/infrastructure/base-assembler';
import {Category} from '../domain/model/category.entity';
import {CategoriesResponse, CategoryResource} from './categories-response';

/**
 * Assembler for converting between Category entities, CategoryResource resources, and CategoriesResponse.
 */
export class CategoryAssembler implements BaseAssembler<Category, CategoryResource, CategoriesResponse> {

  /**
   * Converts a CategoriesResponse to an array of Category entities.
   * @param response - The API response containing categories.
   * @returns An array of Category entities.
   */
  toEntitiesFromResponse(response: CategoriesResponse): Category[] {
    return response.categories.map(resource => this.toEntityFromResource(resource as CategoryResource));
  }

  /**
   * Converts a CategoryResource to a Category entity.
   * @param resource - The resource to convert.
   * @returns The converted Category entity.
   */
  toEntityFromResource(resource: CategoryResource): Category {
    return new Category({
      id: resource.id,
      name: resource.name
    });
  }

  /**
   * Converts a Category entity to a CategoryResource.
   * @param entity - The entity to convert.
   * @returns The converted CategoryResource.
   */
  toResourceFromEntity(entity: Category): CategoryResource {
    return {
      id: entity.id,
      name: entity.name
    } as CategoryResource;
  }


}
