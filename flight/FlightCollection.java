/**
 * 
 */
package flight;

import iterators.*;
import orders.*;

/**
 * @author tbmsilva & m.lami
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

	/**
	 * Checks if there are no flights
	 * 
	 * @return <code>true</code> if there are no flights, <code>false</code>
	 *         otherwise.
	 */
	boolean isEmpty();

	/**
	 * Returns a order iterator
	 * 
	 * @return a order iterator
	 */
	Iterator orderIterator();

	/**
	 * @return
	 */
	boolean noOrderDelivered();

	/**
	 * Adds a given order to delivered order collection
	 * 
	 * @param order - order to be added to collection
	 */
	void deliverOrder(Order order);

	/**
	 * Removes a flight by giving the drone ID currently flying
	 * 
	 * @param droneID - ID of drone flying to be removed
	 */
	void removeFlight(String droneID);
}
