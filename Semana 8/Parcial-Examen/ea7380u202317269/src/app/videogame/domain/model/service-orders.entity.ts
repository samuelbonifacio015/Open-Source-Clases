
/**
 * ServiceOrders Entity
 * @class ServiceOrders
 * Represents a service order in the video game domain.
 * @author Samuel Bonifacio - u202317269
 */

export class ServiceOrders {
  id: number;
  serverId: number;
  issueId: number;
  needAction: string;
  priority: string;
  registeredAt: Date;

  /**
   * Creates a new ServiceOrder instance.
   * @param id
   * @param serverId
   * @param issueId
   * @param needAction
   * @param priority
   */
  constructor(id: number, serverId: number, issueId: number, needAction: string, priority: string) {
    this.id = id;
    this.serverId = serverId;
    this.issueId = issueId;
    this.needAction = needAction;
    this.priority = priority;
    this.registeredAt = new Date();
  }
}
