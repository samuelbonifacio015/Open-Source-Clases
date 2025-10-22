import {BaseResource, BaseResponse} from '../../shared/infrastructure/base-response';

/**
 * Represents a single service order resource returned from the API.
 *
 * @remarks
 * This interface extends {@link BaseResource} and includes the core properties of a service order.
 *
 * @property id - Unique identifier for the service order.
 * @property serviceId - Identifier for the associated service.
 * @property issueId - Identifier for the related issue.
 * @property needAction - Description of the required action.
 * @property priority - Priority level of the service order.
 * @property registeredAt - Date when the service order was registered.
 *
 * @author Samuel Bonifacio - u202317269
 */
export interface ServiceOrdersResource extends BaseResource {
  id: number;
  serviceId: number;
  issueId: number;
  needAction: string;
  priority: string;
  registeredAt: Date;
}

export interface ServiceOrdersResponse extends BaseResponse {
  services: ServiceOrdersResource[];
}
