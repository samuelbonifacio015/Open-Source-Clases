import {BaseResource, BaseResponse} from './base-response';
import {BaseEntity} from './base-entity';

/**
 * Defines a contract for assembler classes that convert between entities, resources, and API responses.
 *
 * @template TEntity - The entity type (e.g., Course), must
 * @template TResource - The resource type, must extend BaseResource.
 * @template TResponse - The response type, must extend BaseResponse.
 */
export interface BaseAssembler<TEntity extends BaseEntity, TResource extends BaseResource, TResponse extends BaseResponse> {
  /**
   * Converts a resource to an entity.
   * @param resource - The resource to convert.
   * @returns The converted entity.
   */
  toEntityFromResource(resource: TResource): TEntity;

  /**
   * Converts an entity to a resource.
   * @param entity - The entity to convert.
   * @returns The converted resource.
   */
  toResourceFromEntity(entity: TEntity): TResource;

  /**
   * Converts an API response to an array of entities.
   * @param response - The API response containing entities.
   * @returns An array of entities.
   */
  toEntitiesFromResponse(response: TResponse): TEntity[];
}
