/**
 * 
 */
package drones;

import iterators.*;

/**
 * @author tbmsilva & m.lami
 *
 */
public class DroneCollectionClass implements DroneCollection {
	
	//Drone array stating size
	private static final int DRONE_SIZE_START = 10;
	
	private static final int GROWTH_FACTOR = 2;

	private Drone[] drones;
	private int counter;

	public DroneCollectionClass() {
		drones = new Drone[DRONE_SIZE_START];
	}

	public Iterator iterator() {
		return new IteratorDronesClass(drones, counter);
	}

	public boolean existsId(String id) {
		return searchIndexId(id) != -1;
	}

	public void addDrone(Drone drone) {
		if (isFull())
			resize();
		drones[counter++] = drone;
	}

	public boolean isEmpty() {
		return counter == 0;
	}

	public Drone getDrone(String id) {
		return drones[searchIndexId(id)];
	}

	public void removeDrone(String droneID) {
		removeAt(searchIndexId(droneID));
	}

	/**
	 * Search index by ID of drones
	 * 
	 * @param droneID - drone's id to be searched for
	 * @return index position of drone with given ID, unless there is no drone with
	 *         that ID, in that case, it returns -1
	 */
	private int searchIndexId(String droneID) {
		int i = 0;
		int res = -1;
		while ((i < counter) && (res == -1)) {
			if (drones[i].droneID().equals(droneID))
				res = i;
			i++;
		}
		return res;
	}

	/**
	 * Doubles the drones array size
	 */
	private boolean isFull() {
		return drones.length == counter;
	}

	/**
	 * Checks if base array is full
	 * 
	 * @return <code>true</code> if base array is full, <code>false</code> otherwise
	 */
	private void resize() {
		Drone[] temp = new Drone[drones.length * GROWTH_FACTOR];
		for (int i = 0; i < counter; i++) {
			temp[i] = drones[i];
		}
		drones = temp;
	}

	/**
	 * Removes the drone in the given index
	 * 
	 * @param index - index of drone to be removed
	 * @pre <code>index >= 0 && index <= counter </code>
	 */
	private void removeAt(int index) {
		for (int i = index; i < counter; i++) {
			drones[i] = drones[i + 1];
		}
		counter--;
	}
}
