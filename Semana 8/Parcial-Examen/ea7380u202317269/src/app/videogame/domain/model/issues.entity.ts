
/**
 * Issues Entity
 * @class Issues
 * Represents an issue in the video game domain.
 * @author Samuel Bonifacio - u202317269
 */

export class Issues {
  id: number;
  serverId: number
  issueType: string;
  registeredAt: Date
  status: string;

  /**
   * Creates a new Issue instance.
   * @param id
   * @param serverId
   * @param issueType
   * @param registeredAt
   * @param status
   */
  constructor(id: number, serverId: number, issueType: string, registeredAt: Date, status: string) {
    this.id = id;
    this.serverId = serverId;
    this.issueType = issueType;
    this.registeredAt = registeredAt;
    this.status = status;
  }
}
