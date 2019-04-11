/**
 * 
 */
package drones;

/**
 * @author m.lami & tbmsilva
 *
 */
public interface Drone {
	
	/**
	 * Returns the drone's unique ID
	 * @return the drone's unique ID
	 */
	String droneID();
	
	/**
	 * Returns the drone's order capacity
	 * @return the drone's order capacity
	 */
	int capacity();
	
	/**
	 * Returns the drone's range
	 * @return the drone's range
	 */
	int range();
	
	/**
	 * Returns a string with drone's info in "(id) (capacity) (range)" format 
	 * @return drone's info in String format
	 */
	String info();
	
	/**
	 * Sets range to max
	 */
	void maxRange();
	
	/**
	 * Removes 10 units of range
	 */
	void removeRange();
	
	/**
	 * Sets the drone's range to the given range
	 * @param newRange - drone's new range
	 */
	void setRange(int newRange);
	
}
