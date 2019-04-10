package drones;

import drones.AbstractDrone;
import drones.Sociable;

/**
 * 
 */

/**
 * @author tbmsilva
 *
 */
public class SwarmClass extends AbstractDrone implements Sociable, Swarm {

	private DroneCollection formingDrones;
	
	/**
	 * @param droneID       - swarm unique ID
	 * @param capacity      - swarm capacity
	 * @param range         - swarm range
	 * @param formingDrones - drone collection of sociable drones forming the swarm
	 */
	public SwarmClass(String swarmID, int capacity, int range, DroneCollection formingDrones) {
		super(swarmID, capacity, range);
		this.formingDrones = formingDrones;
	}

	public DroneCollection getFormingDrones() {
		return formingDrones;
	}
	
	

}
