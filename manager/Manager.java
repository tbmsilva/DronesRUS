/**
 * 
 */
package manager;

import iterators.*;
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
	 * @param id        base's unique id
	 * @param latitude  base location's latitude
	 * @param longitude base location's longitude
	 * @pre <code>!existsId && !existsLocation</code>
	 */
	void addBase(String id, int latitude, int longitude);

	/**
	 * Checks if a base's id is valid for creation
	 * 
	 * @param id base's unique id
	 * @return <code>true</code> if there isn't a base already with same id,
	 *         <code>false</code> otherwise
	 */
	boolean isBaseIdValid(String id);

	/**
	 * Checks if a base exists with given id
	 * 
	 * @param id - id to check
	 * @return <code>true</code> if a base exists with given id, <code>false</code>
	 *         otherwise
	 */
	boolean existsBase(String id);

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
	 */
	Base getBase(String baseID);

	/**
	 * Checks if there is already a drone with given id
	 * 
	 * @param id id to be checked for existance
	 * @return <code>true</code> if a drone exists with given id, <code>false</code>
	 *         otherwise
	 */
	boolean existsDrone(String id);

	/**
	 * Adds a drone, depending on its kind, to the given base hangar and to the
	 * universe drone collection
	 * 
	 * @param id       - drone id
	 * @param base     - base to add drone to
	 * @param kind     - kind of drone
	 * @param range    - drone range
	 * @param capacity - drone capacity
	 */
	void addDrone(String id, String base, String kind, int range, int capacity);

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
	 * @param base - unique base id
	 * @return <code>true</code> if there are no drones in the service bay of a
	 *         base, <code>false</code> otherwise
	 */
	boolean noDronesInServiceBay(String base);

	/**
	 * Checks if there are no drones in the hangar of the base given
	 * 
	 * @param base - unique base id
	 * @return <code>true</code> if there are no drones in the hangar of a base,
	 *         <code>false</code> otherwise
	 */
	boolean noDronesInHangar(String base);

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
	 */
	Iterator swarmComponentsIterator(String swarmID);

	/**
	 * Returns a swarm by giving its ID
	 * 
	 * @param swarmID - swarm ID of respective swarm
	 * @return swarm with given ID
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
	public boolean noOrders();

	/**
	 * Creates and adds an order to the universal order collection and to the
	 * specified base's order collection
	 * 
	 * @param baseID    - baseID to add the order to
	 * @param orderID   - unique order ID
	 * @param dimension - order dimension
	 * @param latitude  - destination latitude
	 * @param longitude - destination longitude
	 */
	void addOrder(String baseID, String orderID, int dimension, int latitude, int longitude);

	/**
	 * Does all the necessary verifications for the creation of a swarm
	 * 
	 * @param droneIDS - ids inputed by the user regarding the drones wanted to form
	 *                 the swarm
	 * @param baseID   - base in which the swarm will be created
	 * @param swarmID  - swarm's unique identifier
	 * @return an <code>int</code> from 0 to 4 indicating the error or success of
	 *         the verifications in index position 0, and in index position 1, the
	 *         droneIDS index position of the drone causing the error
	 */
	int[] swarmCheck(String baseID, String swarmID, String[] droneIDS);

	/**
	 * Disbands a swarm
	 * 
	 * @param swarmID - swarmID of swarm to be disbanded
	 * @param base    - base which swarm belongs to
	 */
	void disband(String swarmID, Base base);
	
	int distance(String droneId, String origin, String target);
	
	boolean hasRange (String droneId, int distance);
	
	void relocation(String droneId, String origin, String target);

}
