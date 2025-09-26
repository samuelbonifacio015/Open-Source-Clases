
import {environment} from '../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {inject} from '@angular/core';

export abstract class BaseService<T> {
  protected httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

  protected serverBaseUrl: string = environment.serverBaseUrl;

  protected resourceEndpointPath: string = '/resources';

  protected http: HttpClient = inject(HttpClient);
}
