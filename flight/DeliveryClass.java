/**
 * 
 */
package flight;

import bases.*;
import drones.*;
import orders.*;

/**
 * @author tbmsilva & m.lami
 *
 */
public class DeliveryClass extends AbstractFlight implements Delivery {

	private Location destination;
	private Order order;

	public DeliveryClass(Drone drone, Base origin, Location destination, Order order) {
		super(drone, origin);
		this.destination = destination;
		this.order = order;
	}

	public int distance() {
		return origin.location().distanceToDelivery(destination);
	}
	
	public Order getOrder() {
		return order;
	}
}
