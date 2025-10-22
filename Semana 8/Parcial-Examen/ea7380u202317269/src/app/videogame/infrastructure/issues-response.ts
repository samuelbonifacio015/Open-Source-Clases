import {BaseResource, BaseResponse} from '../../shared/infrastructure/base-response';

/**
 * Represents a single issue resource returned from the API.
 *
 * @remarks
 * This interface extends {@link BaseResource} and includes the core properties of an issue.
 *
 * @property id - Unique identifier for the issue.
 * @property serverId - Identifier of the server associated with the issue.
 * @property issueType - Type/category of the issue.
 * @property registeredAt - Date and time when the issue was registered.
 * @property status - Current status of the issue.
 * @author Samuel Bonifacio - u202317269
 */
export interface IssuesResource extends BaseResource {
  id: number;
  serverId: number;
  issueType: string;
  registeredAt: Date;
  status: string;
}

export interface IssuesResponse extends BaseResponse {
  issues: IssuesResource[];
}
