/**
 * 
 */
package drones;

/**
 * @author tbmsilva m.lami
 *
 */
public abstract class Atomic extends AbstractDrone {

	/**
	 * @param droneID
	 * @param capacity
	 * @param range
	 */
	public Atomic(String droneID, int capacity, int range) {
		super(droneID, capacity, range);
	}

}
