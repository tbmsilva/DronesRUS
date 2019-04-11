/**
 * 
 */
package flight;

import bases.*;
import drones.*;

/**
 * @author tbmsilva & m.lami
 */

public class RelocationClass extends AbstractFlight implements Relocation {

	private Base destination;

	public RelocationClass(Drone drone, Base origin, Base destination) {
		super(drone, origin);
		this.destination = destination;
	}

	public int distance() {
		return origin.location().calculateDistance(destination.location());
	}

	public Base destination() {
		return destination;
	}
	
	public void tickFlight() {
		
	}
	
}
