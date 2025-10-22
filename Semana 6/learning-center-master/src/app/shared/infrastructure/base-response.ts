/**
 * Abstract interface for API response structures.
 */
export interface BaseResponse {}

/**
 * Defines a standard structure for API resources/DTOs with a unique identifier.
 */
export interface BaseResource {
  /**
   * The unique identifier for the resource.
   */
  id: number;
}
