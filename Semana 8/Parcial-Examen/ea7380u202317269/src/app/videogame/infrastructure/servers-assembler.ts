import {BaseAssembler} from '../../shared/infrastructure/base-assembler';
import {Servers} from '../domain/model/servers.entity';
import {ServerResource, ServerResponse} from './servers-response';

/**
 * Assembler for converting between Servers entities, ServerResource resources, and ServerResponse.
 * @author Samuel Bonifacio - u202317269
 */
export class ServersAssembler implements BaseAssembler<Servers, ServerResource, ServerResponse> {
  toEntitiesFromResponse(response: ServerResponse): Servers[] {
    console.log(response);
    return response.servers.map(resource => this.toEntityFromResource(resource as ServerResource));
  }

  /**
   * Converts a ServerResource to a Servers entity.
   * @param resource
   */
  toEntityFromResource(resource: ServerResource): Servers {
    return new Servers(
      resource.id,
      resource.name,
      resource.costPerHour,
      resource.impactInGameplay,
      resource.defaultAction
    );
  }

  /**
   * Converts a Servers entity to a ServerResource.
   * @param entity
   */
  toResourceFromEntity(entity: Servers): ServerResource {
    return {
      id: entity.id,
      name: entity.name,
      costPerHour: entity.costPerHour,
      impactInGameplay: entity.impactInGameplay,
      defaultAction: entity.defaultAction
    } as ServerResource;
  }
}
