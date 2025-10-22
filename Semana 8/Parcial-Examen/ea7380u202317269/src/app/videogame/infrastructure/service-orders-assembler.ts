import {BaseAssembler} from '../../shared/infrastructure/base-assembler';
import {ServiceOrders} from '../domain/model/service-orders.entity';
import {ServiceOrdersResource, ServiceOrdersResponse} from './service-orders-response';

export class ServiceOrdersAssembler implements BaseAssembler<ServiceOrders, ServiceOrdersResource, ServiceOrdersResponse> {
  toEntitiesFromResponse(response: ServiceOrdersResponse): ServiceOrders[] {
    console.log(response);
    return response.services.map(resource => this.toEntityFromResource(resource as ServiceOrdersResource));
  }

  toEntityFromResource(resource: ServiceOrdersResource): ServiceOrders {
    return new ServiceOrders(
      resource.id,
      resource.serviceId,
      resource.issueId,
      resource.needAction,
      resource.priority,
    );
  }

  toResourceFromEntity(entity: ServiceOrders): ServiceOrdersResource {
    return {
      id: entity.id,
      serviceId: entity.serverId,
      issueId: entity.issueId,
      needAction: entity.needAction,
      priority: entity.priority,
      registeredAt: entity.registeredAt
    }
  }
}
