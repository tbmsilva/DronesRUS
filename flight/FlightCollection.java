/**
 * 
 */
package flight;

import iterators.*;

/**
 * @author tbmsilva
 *
 */
public interface FlightCollection {

	/**
	 * Adds a flight to the flight collection
	 * 
	 * @param flight - flight to be added to the collection
	 */
	void addFlight(Flight flight);

	/**
	 * Returns the flight object of the flight being done by the given drone
	 * 
	 * @param droneID - ID of drone currently flying
	 * @return the flight object of the flight being done by the given drone
	 */
	Flight getFlight(String droneID);

	/**
	 * Returns a flight iterator
	 * 
	 * @return a flight iterator
	 */
	Iterator iterator();
}
