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
public interface Flight {

	/**
	 * Returns the flying drone
	 * 
	 * @return the flying drone
	 */
	Drone drone();

	/**
	 * Returns the distance of the flight
	 * 
	 * @return the distance of the flight
	 */
	int distance();

	/**
	 * Returns the location of the point of origin
	 * 
	 * @return Location of the point of origin
	 */
	Base origin();

	/**
	 * Calculates distance traveled at point of given tick
	 */
	void increaseDistanceTraveled();

	/**
	 * Returns distance covered in flight
	 * 
	 * @return distance covered in flight
	 */
	int distanceCovered();

	/**
	 * Sets the drone's range to the given range
	 * 
	 * @param newRange - drone's new range
	 */
	void setRange(int newRange);

}
