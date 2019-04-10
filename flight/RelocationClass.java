/**
 * 
 */
package flight;

import bases.Location;
import drones.Drone;

/**
 * @author tbmsilva
 */

public class RelocationClass extends AbstractFlight implements Relocation {

	public RelocationClass(Drone drone, Location origin, Location destination) {
		super(drone, origin, destination);
	}

	public int distance() {
		return origin.calculateDistance(destination);
	}

}
