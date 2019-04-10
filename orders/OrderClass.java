/**
 * 
 */
package orders;

import bases.*;

/**
 * @author tbmsilva
 *
 */
public class OrderClass implements Order {
	
	private String id, info;
	private int dimension;
	private Location destination;

	public OrderClass(String id, int dimension, Location destination) {
		this.id = id;
		this.dimension = dimension;
		this.destination = destination;
		info = id + "; " + dimension + "; " + this.destination.coordinates();
	}

	public String id() {
		return id;
	}

	public Location destination() {
		return destination;
	}

	public int dimension() {
		return dimension;
	}
	
	public String info() {
		return info;
	}

}
