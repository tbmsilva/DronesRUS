/**
 * 
 */
package flight;

import bases.*;
import drones.*;

/**
 * @author tbmsilva & m.lami
 *
 */
public class DeliveryClass extends AbstractFlight implements Delivery {
	
	private static final int BOTH_WAYS = 2;


	public DeliveryClass(Drone drone, Location origin, Location destination) {
		super(drone, origin, destination);
	}

	public int distance() {
		return origin.calculateDistance(destination) * BOTH_WAYS;
	}

}
