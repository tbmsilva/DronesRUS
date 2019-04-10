/**
 * 
 */
package flight;

import bases.*;
import drones.*;

/**
 * @author tbmsilva
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
	Location origin();

	/**
	 * Returns the location of the destination
	 * 
	 * @return Location of the destination
	 */
	Location destination();

	/**
	 * Returns distance traveled at point of given tick
	 * 
	 * @param currentTick - tick of simulation
	 * @return distance traveled at point of given tick
	 */
	int distanceTraveled(int tick);

}
