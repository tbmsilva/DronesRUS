/**
 * 
 */
package drones;

import iterators.*;

/**
 * @author tbmsilva
 *
 */
public class DroneCollectionClass implements DroneCollection {
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

	private int searchIndexId(String id) {
		int i = 0;
		int res = -1;
		while ((i < counter) && (res == -1)) {
			if (drones[i].droneID().equals(id))
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
		for (int i = 0; i <= drones.length; i++) {
			temp[i] = drones[i];
		}
		drones = temp;
	}

	private void removeAt(int index) {
		for(int i = index; i < counter; i++) {
			drones[i] = drones[i+1];
		}
		counter--;
	}

}
