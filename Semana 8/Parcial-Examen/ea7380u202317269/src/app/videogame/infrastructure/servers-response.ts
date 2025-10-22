import {BaseResponse} from '../../shared/infrastructure/base-response';

/**
 * Resource representing a server in the video game context.
 *
 * @remarks
 * This interface extends {@link BaseResource} and includes properties specific to a server.
 *
 * @property id - Unique identifier for the server.
 * @property name - Name of the server.
 * @property costPerHour - Cost to use the server per hour.
 * @property impactInGameplay - Description of how the server impacts gameplay.
 * @property defaultAction - Default action associated with the server.
 * @author Samuel Bonifacio - u202317269
 */
export interface ServerResource extends BaseResponse {
  id: number;
  name: string;
  costPerHour: number;
  impactInGameplay: string;
  defaultAction: string;
}

export interface ServerResponse extends BaseResponse {
  servers: ServerResource[];
}
