package drones;

import iterators.*; 

public interface DroneCollection {

	/**
	 * Adds a drone to the collection
	 * @param id drone's unique id
	 * @param capacity
	 * @param range	 
	 * @pre <code>!existsId && !existsLocation</code>
	 */
	void addDrone(Drone drone);

	
	Iterator iterator();
	
	/**
	 * Checks if there is already a drone with the same id
	 * @param id drone id to be checked
	 * @return <code>true</code> if there is a drone already created with the same id, <code>false</code> otherwise
	 */
	boolean existsId(String id);
	
	/**
	 * Returns a drone by giving its ID
	 * 
	 * @param id - ID of wanted drone
	 * @return Drone with matching ID
	 */
	Drone getDrone(String id);

	/**
	 * Checks if there are 0 drones created
	 * @return <code>true</code> if there are 0 drones in collection, <code>false</code> otherwise
	 */
	boolean isEmpty();

	/**
	 * Removes a drone from the drone collection
	 * 
	 * @param droneID - drone to be removed
	 */
	void removeDrone(String droneID);

}
