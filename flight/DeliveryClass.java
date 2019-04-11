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
	private Location destination;

	public DeliveryClass(Drone drone, Base origin, Location destination) {
		super(drone, origin);
		this.destination = destination;
	}

	public int distance() {
		return origin.location().calculateDistance(destination) * BOTH_WAYS;
	}
}
