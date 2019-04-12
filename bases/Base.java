/**
 * 
 */
package bases;

import drones.*;
import iterators.*;
import orders.Order;

/**
 * @author tbmsilva & m.lami
 *
 */
public interface Base {

	/**
	 * Returns the base's location
	 * 
	 * @return the base's location
	 */
	Location location();

	/**
	 * Returns the base's unique ID
	 * 
	 * @return the base's unique ID
	 */
	String baseID();

	/**
	 * Returns the base's coordinates in String format
	 * 
	 * @return the base's coordinates in String format
	 */
	String coordinates();

	/**
	 * Adds a given drone to the hangar
	 * 
	 * @param drone to be added to the hangar
	 * @pre <code>!isInHangar(drone)</code>
	 */
	void addDrone(Drone drone);
	
	/**
	 * Checks if there is an order with given ID
	 * 
	 * @param orderID - order's ID to be checked for existance
	 * @return <code>true</code> if an order exists with given id,
	 *         <code>false</code> otherwise
	 */
	public boolean existsOrder(String orderID);

	/**
	 * Removes a given drone from hangar
	 * 
	 * @param droneID - drone to be removed from hangar
	 */
	void removeDroneHangar(String droneID);

	/**
	 * Checks if there are no drones in the service bay
	 * 
	 * @return <code>true</code> if there are no drones in the service bay,
	 *         <code>false</code> otherwise
	 */
	boolean noDronesInServiceBay();

	/**
	 * Checks if there are no drones in the hangar
	 * 
	 * @return <code>true</code> if there are no drones in the hangar,
	 *         <code>false</code> otherwise
	 */
	boolean noDronesInHangar();

	/**
	 * Checks if a drone is in base's hangar by giving its ID
	 * 
	 * @param droneID - drone id to be checked for existence in hangar drone
	 *                collection
	 * @return <code>true</code> if the drone is in hangar, <code>false</code>
	 *         otherwise
	 */
	boolean isInHangar(String droneID);

	/**
	 * Returns a drone iterator for the base's hangar
	 * 
	 * @return a drone iterator for the base's hangar
	 */
	Iterator droneIteratorHangar();

	/**
	 * Returns a drone iterator for the base's service bay
	 * 
	 * @return a drone iterator for the base's service bay
	 */
	Iterator droneIteratorServiceBay();

	/**
	 * Adds a given order to base
	 * 
	 * @param order - order to be added
	 */
	void addOrder(Order order);

	/**
	 * Returns an order iterator for the base's order collection
	 * 
	 * @return an order iterator
	 */
	Iterator orderIterator();

	/**
	 * Moves the given drone from the hangar to the service bay
	 * 
	 * @param drone - drone to be moved
	 */
	void moveToServiceBay(Drone drone);

	/**
	 * Returns a drone with given droneID
	 * 
	 * @param droneID - droneID of wanted drone
	 * @return a drone with given droneID
	 */
	Drone getDrone(String droneID);

	/**
	 * Removes an order by giving its ID
	 * 
	 * @param orderID - order's ID
	 * @pre <code>existsOrder(orderID)</code>
	 */
	void removeOrder(String orderID);

	/**
	 * Ticks the service
	 */
	void tickService();

}
