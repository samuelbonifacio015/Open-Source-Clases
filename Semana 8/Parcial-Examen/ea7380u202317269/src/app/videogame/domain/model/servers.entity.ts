
/**
 * Servers Entity
 * @class Servers
 * Represents a server in the video game domain.
 * @author Samuel Bonifacio - u202317269
 */
export class Servers {
  id: number;
  name: string;
  costPerHour: number;
  impactInGameplay: string;
  defaultAction: string;

  /**
   * Creates a new Server instance.
   * @param id
   * @param name
   * @param costPerHour
   * @param impactInGameplay
   * @param defaultAction
   */
  constructor(id: number, name: string, costPerHour: number, impactInGameplay: string, defaultAction: string) {
    this.id = id;
    this.name = name;
    this.costPerHour = costPerHour;
    this.impactInGameplay = impactInGameplay;
    this.defaultAction = defaultAction;
  }
}
