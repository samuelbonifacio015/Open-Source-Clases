import {BaseEntity} from './base-entity';
import {BaseResource, BaseResponse} from './base-response';

/**
 * Defines a contract for assembler classes that convert between entities, resources, and API responses.
 * @template TEntity - The entity type (e.g., Course), must
 * @template TResource - The resource type, must extend BaseResource.
 * @template TResponse - The response type, must extend BaseResponse.
 * @author Samuel Bonifacio - u202317269
 */
export interface BaseAssembler<TEntity extends BaseEntity, TResource extends BaseResource, TResponse extends BaseResponse> {
  /**
   * Converts a resource to an entity.
   * @param resource
   */
  toEntityFromResource(resource: TResource): TEntity;

  /**
   * Converts an entity to a resource.
   * @param entity
   */
  toResourceFromEntity(entity: TEntity): TResource;

  /**
   * Converts an API response to an array of entities.
   * @param response
   */
  toEntitiesFromResponse(response: TResponse): TEntity[];
}
