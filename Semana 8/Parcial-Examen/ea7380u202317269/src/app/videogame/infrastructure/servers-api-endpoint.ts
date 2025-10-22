import {BaseApiEndpoint} from '../../shared/infrastructure/base-api-endpoint';
import {Servers} from '../domain/model/servers.entity';
import {ServerResource, ServerResponse} from './servers-response';
import {ServersAssembler} from './servers-assembler';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

/**
 * API endpoint for managing servers.
 * @author Samuel Bonifacio - u202317269
 */
export class ServersApiEndpoint extends BaseApiEndpoint<Servers, ServerResource,
  ServerResponse, ServersAssembler> {
  constructor(http: HttpClient) {
    super(http, `${environment.platformProviderApiBaseUrl}${environment.platformProviderServersEndpointPath}`, new ServersAssembler());
  }
}
