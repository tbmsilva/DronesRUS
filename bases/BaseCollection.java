/**
 * 
 */
package bases;

import drones.Drone;
import iterators.*;

/**
 * @author tbmsilva & m.lami
 *
 */
public interface BaseCollection {

	/**
	 * Adds a base to the collection
	 * 
	 * @param id       base's unique id
	 * @param location base's location
	 * @pre <code>!existsId && !existsLocation</code>
	 */
	void addBase(String id, Location location);

	/**
	 * Checks if there are any bases created
	 * 
	 * @return <code>true</code> if there are no bases created, <code>false</code>
	 *         otherwise
	 */
	boolean noBases();

	/**
	 * Checks if there is already a base with the same id
	 * 
	 * @param id base id to be checked
	 * @return <code>true</code> if there is a base already created with the same
	 *         id, <code>false</code> otherwise
	 */
	boolean existsId(String id);

	/**
	 * Checks if there is already a base with the same location
	 * 
	 * @param location base's location to be checked
	 * @return <code>true</code> if there is a base already created with the same
	 *         location, <code>false</code> otherwise
	 */
	boolean existsLocation(Location location);

	/**
	 * Returns a base iterator
	 * 
	 * @return a base iterator
	 */
	Iterator iterator();

	/**
	 * Returns a base by giving its unique id
	 * 
	 * @param id - unique base id
	 * @return base with given id
	 */
	Base getBase(String id);

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
	 * Moves the given drone from the hangar of the given base to the service bay of
	 * the given the base
	 * 
	 * @param base  - base object where the given drone belongs to
	 * @param drone - drone to be moved
	 */
	void moveDroneToServiceBay(Base base, Drone drone);
}

