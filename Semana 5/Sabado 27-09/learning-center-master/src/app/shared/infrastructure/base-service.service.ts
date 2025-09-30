
import {environment} from '../../../environments/environment';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {inject} from '@angular/core';
import {catchError, Observable, retry, throwError} from 'rxjs';

export abstract class BaseService<T> {
  /** HTTP headers configuration for JSON communication */
  protected httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

  /**Base URL for the server API */
  protected serverBaseUrl: string = environment.serverBaseUrl;

  /** Endpoint path for the specific resource */
  protected resourceEndpointPath: string = '/resources';

  /** HttpClient instance for making HTTP requests */
  protected http: HttpClient = inject(HttpClient);

  /**
   * Handles HTTP errors and transforms them into an Observable error.
   * @param error - The HttpErrorResponse object containing error details.
   * @returns An Observable that emits an Error with a user-friendly message.
   */
  protected handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error(`An error ocurred:${error.error.message}`);
    } else {
      console.error(`Backend returned code ${error.status}, body was: ${error.error}`);
    }
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }

  /**
   * Constructs the full resource path by combining the server base URL and the resource endpoint path.
   * @returns The full URL string for the resource.
   */
  protected resourcePath(): string {
    return `${this.serverBaseUrl}${this.resourceEndpointPath}`;
  }

  /**
   * Creates a new resource
   * @param resource - The resource object to be created.
   * @returns An Observable of the created resource.
   */
  public create(resource: T): Observable<T> {
    return this.http.post<T>(this.resourcePath(), JSON.stringify(resource), this.httpOptions)
      .pipe(retry(2), catchError(this.handleError));
  }

  /**
   * Deletes a resource by its ID.
   * @param id - The ID of the resource to be deleted.
   * @returns An Observable of the deletion result.
   */
  public delete(id: any): Observable<any> {
    return this.http.delete(this.resourcePath() + '/' + id, this.httpOptions)
      .pipe(retry(2), catchError(this.handleError));
  }

  /**
   * Retrieves a resource by its ID.
   * @param id - The ID of the resource to update.
   * @param resource - The resource object to be updated.
   * @returns An Observable of the updated resource.
   */
  public update(id: any, resource: T): Observable<T> {
    return this.http.put<T>(this.resourcePath() + '/' + id, JSON.stringify(resource), this.httpOptions)
      .pipe(retry(2), catchError(this.handleError));
  }

  /**
   * Retrieves all resources
   * @returns An Observable of an array of resources.
   */
  public getAll(): Observable<T> {
    return this.http.get<T>(this.resourcePath(), this.httpOptions)
      .pipe(retry(2), catchError(this.handleError));
  }

  /**
   * Retrieves a resource by its ID.
   * @param id - The ID of the resource to retrieve.
   * @returns An Observable of the retrieved resource.
   */
  public getById(id: any): Observable<T> {
    return this.http.get<T>(this.resourcePath() + '/' + id, this.httpOptions)
      .pipe(retry(2), catchError(this.handleError));
  }

}





















