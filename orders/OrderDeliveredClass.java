/**
 * 
 */
package orders;

import bases.*;

/**
 * @author tbmsilva & m.lami
 *
 */
public class OrderDeliveredClass extends OrderClass implements OrderDelivered {

	private int timeStamp;
	private Base origin;
	
	public OrderDeliveredClass(String id, int dimension, Location destination) {
		super(id, dimension, destination);
		timeStamp = 0;
		origin = null;
	}

	public int timeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(int tick) {
		timeStamp = tick;
	}
	
	public Base origin() {
		return origin;
	}
	
	public void setOrigin(Base origin) {
		this.origin = origin;
	}
}
