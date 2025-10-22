import {BaseApiEndpoint} from '../../shared/infrastructure/base-api-endpoint';
import {ServiceOrders} from '../domain/model/service-orders.entity';
import {ServiceOrdersResource, ServiceOrdersResponse} from './service-orders-response';
import {ServiceOrdersAssembler} from './service-orders-assembler';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';

/**
 * API endpoint for managing service orders.
 * @author Samuel Bonifacio - u202317269
 */
export class ServiceOrdersApiEndpoint extends BaseApiEndpoint<ServiceOrders, ServiceOrdersResource, ServiceOrdersResponse, ServiceOrdersAssembler>{
  /**
   * Creates an instance of ServiceOrdersApiEndpoint.
   * @param http
   */
  constructor(http: HttpClient) {
    super(http, `${environment.platformProviderApiBaseUrl}${environment.platformProviderServiceOrdersEndpointPath}`, new ServiceOrdersAssembler());
  }
}
