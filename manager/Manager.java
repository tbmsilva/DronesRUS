/**
 * 
 */
package manager;

import iterators.*;
import orders.*;
import bases.*;
import drones.*;

/**
 * @author m.lami & tbmsilva
 *
 */
public interface Manager {

	/**
	 * Creates a location object for the base and creates the base
	 * 
	 * @param baseID    - base's unique id
	 * @param latitude  - base location's latitude
	 * @param longitude - base location's longitude
	 * @pre <code>!existsBase(baseID) && !existsLocation(l)</code>
	 */
	void addBase(String baseID, int latitude, int longitude);

	/**
	 * Checks if a base's id is valid for creation
	 * 
	 * @param baseID - base's unique id
	 * @return <code>true</code> if there isn't a base already with same id,
	 *         <code>false</code> otherwise
	 */
	boolean isBaseIdValid(String baseID);

	/**
	 * Checks if a base exists with given id
	 * 
	 * @param baseID - baseID to check
	 * @return <code>true</code> if a base exists with given id, <code>false</code>
	 *         otherwise
	 */
	boolean existsBase(String baseID);

	/**
	 * Checks if a base's coordinates is valid for creation
	 * 
	 * @param latitude  latitude to be checked
	 * @param longitude longitude to be checked
	 * @return <code>true</code> if there isn't a base already with same
	 *         coordinates, <code>false</code> otherwise
	 */
	boolean areBaseCoordinatesValid(int latitude, int longitude);

	/**
	 * Returns a base iterator
	 * 
	 * @return a base iterator
	 */
	Iterator iteratorBases();

	/**
	 * Checks if there have been any bases created
	 * 
	 * @return <code>true</code> if there are any bases, <code>false</code>
	 *         otherwise
	 */
	boolean areThereBases();

	/**
	 * Gives access to Base object by giving its ID
	 * 
	 * @param baseID - baseID of base to return
	 * @return Base object with matching ID
	 * @pre <code>existsBase(baseID)</code>
	 */
	Base getBase(String baseID);

	/**
	 * Checks if there is already a drone with given id
	 * 
	 * @param droneID - id to be checked for existance
	 * @return <code>true</code> if a drone exists with given id, <code>false</code>
	 *         otherwise
	 */
	boolean existsDrone(String droneID);

	/**
	 * Adds a drone, depending on its kind, to the given base hangar and to the
	 * universe drone collection
	 * 
	 * @param droneID  - drone id
	 * @param baseID   - base to add drone to
	 * @param kind     - kind of drone
	 * @param range    - drone range
	 * @param capacity - drone capacity
	 * @pre <code>existsBase(baseID)</code>
	 */
	void addDrone(String droneID, String baseID, String kind, int range, int capacity);

	/**
	 * Checks if there are no drones in universe
	 * 
	 * @return <code>true</code> if there are no drones in universe,
	 *         <code>false</code> otherwise
	 */
	boolean noDrones();

	/**
	 * Checks if there are no drones in the service bay of the base given
	 * 
	 * @param baseID - unique base id
	 * @return <code>true</code> if there are no drones in the service bay of a
	 *         base, <code>false</code> otherwise
	 * @pre <code>existsBase(baseID)</code>
	 */
	boolean noDronesInServiceBay(String baseID);

	/**
	 * Checks if there are no drones in the hangar of the base given
	 * 
	 * @param baseID - unique base id
	 * @return <code>true</code> if there are no drones in the hangar of a base,
	 *         <code>false</code> otherwise
	 * @pre <code>existsBase(baseID)</code>
	 */
	boolean noDronesInHangar(String baseID);

	/**
	 * Moves the given drone from the hangar to the service bay
	 * 
	 * @param drone - drone to be moved
	 */
	void moveDroneToServiceBay(Base base, Drone drone);

	/**
	 * Creates a swarm. Creates a Drone Collection with its forming drones,
	 * calculates the range and capacity of swarm.
	 * 
	 * @param swarmID          - swarm's unique ID
	 * @param baseID           - base to add swarm to
	 * @param formingDronesIDS - drone IDS of drones belonging to swarm
	 * @pre <code>existsBase(baseID)</code>
	 */
	void addSwarm(String swarmID, String baseID, String[] formingDronesIDS);

	/**
	 * Returns a drone iterator
	 * 
	 * @return a drone iterator
	 */
	Iterator iteratorDrones();

	/**
	 * Returns a drone iterator for the drones forming the inputed swarm
	 * 
	 * @param swarmID - swarm ID of swarm we want to know the drones of
	 * @return a drone iterator for the drones forming the swarm
	 * @pre <code>existsDrone(swarmID)</code>
	 */
	Iterator swarmComponentsIterator(String swarmID);

	/**
	 * Returns a swarm by giving its ID
	 * 
	 * @param swarmID - swarm ID of respective swarm
	 * @return swarm with given ID
	 * @pre <code>existsDrone(swarmID)</code>
	 */
	Swarm getSwarm(String swarmID);

	/**
	 * Checks if there is an order with given ID
	 * 
	 * @param orderID - order's ID to be checked for existance
	 * @return <code>true</code> if an order exists with given id,
	 *         <code>false</code> otherwise
	 */
	boolean existsOrder(String orderID);

	/**
	 * Checks if there are no orders in order collection
	 * 
	 * @return <code>true</code> if there are no orders, <code>false</code>
	 *         otherwise
	 */
	boolean noOrders();

	/**
	 * Checks if no orders were delivered
	 * 
	 * @return <code>true</code> if no orders were delivered, <code>false</code>
	 *         otherwise
	 */
	boolean noOrderDelivered();

	/**
	 * Creates and adds an order to the universal order collection and to the
	 * specified base's order collection
	 * 
	 * @param baseID    - baseID to add the order to
	 * @param orderID   - unique order ID
	 * @param dimension - order dimension
	 * @param latitude  - destination latitude
	 * @param longitude - destination longitude
	 * @pre <code>existsBase(baseID) && dimension > 0</code>
	 */
	void addOrder(String baseID, String orderID, int dimension, int latitude, int longitude);

	/**
	 * Does all the necessary verifications for the creation of a swarm
	 * 
	 * @param droneIDS - ids inputed by the user regarding the drones wanted to form
	 *                 the swarm
	 * @param baseID   - base in which the swarm will be created
	 * @param swarmID  - swarm's unique identifier
	 * @return and int array with 2 positions: The first position with the error
	 *         code and the second position with the index position of the array
	 *         <code>droneIDS</code> of the droneID causing the error.
	 */
	int[] swarmCheck(String baseID, String swarmID, String[] droneIDS);

	/**
	 * Disbands a swarm
	 * 
	 * @param swarmID - swarmID of swarm to be disbanded
	 * @param base    - base which swarm belongs to
	 */
	void disband(String swarmID, Base base);

	/**
	 * Calculates distance of an order delivery
	 * 
	 * @param origin - location from where the drone departs
	 * @param target - location where the drone will land and deliver the order
	 * @return distance value between the two locations
	 */
	int distanceToDelivery(Location origin, Location target);
	
	/**
	 * Calculates distance of a drone relocation
	 * 
	 * @param origin - location from where the drone departs
	 * @param target - location where the drone will land
	 * @return distance value between the two locations
	 */
	int distanceToRelocation(Location origin, Location target);

	/**
	 * Checks if the given drone has enough range to get to destination
	 * 
	 * @param droneID  - ID of drone to check range of
	 * @param distance - distance to compare to drone's range
	 * @return <code>true</code> if drone's range is equal or greater than the given
	 *         distance, <code>false</code> otherwise
	 */
	boolean hasRange(String droneID, int distance);

	/**
	 * Creates a Flight Object for the relocation
	 * 
	 * @param droneID - ID of drone being relocated
	 * @param origin  - base of departure
	 * @param target  - base of arrival
	 */
	void relocation(String droneID, String origin, String target);

	/**
	 * Returns a flight iterator
	 * 
	 * @return a flight iterator
	 */
	Iterator iteratorFlights();

	/**
	 * Checks if there are no flights in flight collection
	 * 
	 * @return <code>true</code> if there are no flights, <code>false</code>
	 *         otherwise
	 */
	boolean noFlights();

	/**
	 * Returns a orders delivered iterator
	 * 
	 * @return a orders delivered iterator
	 */
	Iterator iteratorOrderDelivered();

	/**
	 * Returns an order with given orderID
	 * 
	 * @param orderID - orderID of wanted order
	 * @return an order with given orderID
	 */
	Order getOrder(String orderID);

	/**
	 * Starts a delivery for an order
	 * 
	 * @param base    - base which the drone departs from and where the order is
	 *                pending
	 * @param droneID - droneID of drone delivering the order
	 * @param order   - order to be delivered
	 */
	void startDelivery(Base base, String droneID, Order order);

	/**
	 * Advances simulation tick
	 * 
	 * @param tickAdvance - how many ticks to advance
	 */
	void advanceTick(int tickAdvance);

}
