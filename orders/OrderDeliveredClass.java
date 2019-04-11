/**
 * 
 */
package orders;

import bases.Location;

/**
 * @author tbmsilva
 *
 */
public class OrderDeliveredClass extends OrderClass implements OrderDelivered {

	private int timeStamp;
	
	public OrderDeliveredClass(String id, int dimension, Location destination, int timeStamp) {
		super(id, dimension, destination);
		this.timeStamp = timeStamp;
	}

	public int timeStamp() {
		return timeStamp;
	}
}
