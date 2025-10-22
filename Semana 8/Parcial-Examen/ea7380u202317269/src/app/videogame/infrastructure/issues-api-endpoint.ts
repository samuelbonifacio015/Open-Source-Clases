import {BaseApiEndpoint} from '../../shared/infrastructure/base-api-endpoint';
import {Issues} from '../domain/model/issues.entity';
import {IssuesResource, IssuesResponse} from './issues-response';
import {IssuesAssembler} from './issues-assembler';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';

/**
 * API endpoint for managing issues.
 * @author Samuel Bonifacio - u202317269
 */
export class IssuesApiEndpoint extends BaseApiEndpoint<Issues, IssuesResource, IssuesResponse, IssuesAssembler>{
  /**
   * Creates an instance of IssuesApiEndpoint.
   * @param http
   */
  constructor(http: HttpClient) {
    super(http, `${environment.platformProviderApiBaseUrl}${environment.platformProviderIssuesEndpointPath}`, new IssuesAssembler());
  }
}
