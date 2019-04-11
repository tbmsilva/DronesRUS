/**
 * 
 */
package drones;

/**
 * @author m.lami & tbmsilva
 *
 */
public abstract class AbstractDrone implements Drone {

	private String droneID;
	private int capacity, currentRange, maxRange;

	private static final int RANGE_PER_TICK = 10;

	public AbstractDrone(String droneID, int capacity, int range) {
		this.droneID = droneID;
		this.capacity = capacity;
		this.maxRange = range;
		currentRange = range;
		maxRange = range;
	}

	public String droneID() {
		return droneID;
	}

	public int capacity() {
		return capacity;
	}

	public int range() {
		return currentRange;
	}

	public String info() {
		return droneID + " " + capacity + " " + currentRange;
	}

	public void maxRange() {
		currentRange = maxRange;
	}

	public void removeRange() {
		currentRange -= RANGE_PER_TICK;
	}

	public void setRange(int newRange) {
		currentRange = newRange;
	}

}
