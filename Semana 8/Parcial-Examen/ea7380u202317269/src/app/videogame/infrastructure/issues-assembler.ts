import {BaseAssembler} from '../../shared/infrastructure/base-assembler';
import {Issues} from '../domain/model/issues.entity';
import {IssuesResource, IssuesResponse} from './issues-response';

/**
 * Assembler for converting between Issues entities, IssuesResource resources, and IssuesResponse.
 * @author Samuel Bonifacio - u202317269
 */
export class IssuesAssembler implements BaseAssembler<Issues, IssuesResource, IssuesResponse> {
  toEntitiesFromResponse(response: IssuesResponse): Issues[] {
    console.log(response);
    return response.issues.map(resource => this.toEntityFromResource(resource as IssuesResource));
  }

  /**
   * Converts a IssuesResource to an Issues entity.
   * @param resource
   */
  toEntityFromResource(resource: IssuesResource): Issues {
    return new Issues(
      resource.id,
      resource.serverId,
      resource.issueType,
      resource.registeredAt,
      resource.status
    );
  }

  /**
   * Converts an Issues entity to a IssuesResource.
   * @param entity
   */
  toResourceFromEntity(entity: Issues): IssuesResource {
    return {
      id: entity.id,
      serverId: entity.serverId,
      issueType: entity.issueType,
      registeredAt: entity.registeredAt,
      status: entity.status
    } as IssuesResource;
  }
}
