import {BaseApiEndpoint} from '../../shared/infrastructure/base-api-endpoint';
import {Category} from '../domain/model/category.entity';
import {CategoriesResponse, CategoryResource} from './categories-response';
import {CategoryAssembler} from './category-assembler';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';


/**
 * API endpoint for managing categories.
 */
export class CategoriesApiEndpoint extends BaseApiEndpoint<Category, CategoryResource, CategoriesResponse, CategoryAssembler> {
  /**
   * Creates an instance of CategoriesApiEndpoint.
   * @param http - The HttpClient to be used for making API requests.
   */
  constructor(http: HttpClient) {
    super(http, `${environment.platformProviderApiBaseUrl}${environment.platformProviderCategoriesEndpointPath}`, new CategoryAssembler());
  }
}
