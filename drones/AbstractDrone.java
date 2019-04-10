/**
 * 
 */
package drones;

/**
 * @author m.lami & tbmsilva
 *
 */
public abstract class AbstractDrone implements Drone {

	private String droneID, info;
	private int capacity, range;

	public AbstractDrone(String droneID, int capacity, int range) {
		this.droneID = droneID;
		this.capacity = capacity;
		this.range = range;
		info = droneID + " " + capacity + " " + range;
	}

	public String droneID() {
		return droneID;
	}


	public int capacity() {
		return capacity;
	}

	public int range() {
		return range;
	}
	
	public String info() {
		return info;
	}

	/*public int addRange() {
		return range + 10;
	}

	public int removeRange() {
		return range -10;
	}
	*/
	

}
