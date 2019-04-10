/**
 * 
 */
package manager;

import bases.*;
import orders.*;
import drones.*;
import flight.*;
import iterators.*;

/**
 * @authors m.lami & tbmsilva
 */

public class ManagerClass implements Manager {

	// Type of drone diferentiation
	private static final String HERMIT = "hermit";

	// Swarm Error Codes
	private static final int ERROR_SAME_DRONE = 0;
	private static final int ERROR_HERMIT_DRONE = 1;
	private static final int ERROR_DRONE_UNAVAILABLE = 2;
	private static final int ERROR_SWARM_ID = 3;
	private static final int SWARM_CHECK_OK = 4;

	// Collections
	private BaseCollection bases;
	private DroneCollection drones;
	private OrderCollection orders;
	private FlightCollection flights;

	// Current simulation tick
	private int tick;

	public ManagerClass() {
		bases = new BaseCollectionClass();
		drones = new DroneCollectionClass();
		orders = new OrderCollectionClass();
		flights = new FlightCollectionClass();
		tick = 0;
	}

	public void addBase(String id, int latitude, int longitude) {
		Location l = new LocationClass(latitude, longitude);
		bases.addBase(id, l);
	}

	public boolean isBaseIdValid(String id) {
		if (!bases.noBases())
			return !existsBase(id);
		else
			return true;
	}

	public boolean existsBase(String id) {
		return bases.existsId(id);
	}

	public boolean areBaseCoordinatesValid(int latitude, int longitude) {
		Location l = new LocationClass(latitude, longitude);
		return !bases.existsLocation(l);
	}

	public Iterator iteratorBases() {
		return bases.iterator();
	}

	public boolean areThereBases() {
		return !bases.noBases();
	}

	public Base getBase(String base) {
		return bases.getBase(base);
	}

	public boolean existsDrone(String id) {
		if (noDrones())
			return false;
		else
			return drones.existsId(id);
	}

	public void addDrone(String id, String base, String kind, int range, int capacity) {
		if (kind.equals(HERMIT)) {
			addHermitDrone(id, base, range, capacity);
		} else {
			addSociableDrone(id, base, range, capacity);
		}
	}

	public boolean noDrones() {
		return drones.isEmpty();
	}

	public boolean noDronesInHangar(String base) {
		return bases.noDronesInHangar(base);
	}

	public boolean noDronesInServiceBay(String base) {
		return bases.noDronesInServiceBay(base);
	}

	public void moveDroneToServiceBay(Base base, Drone drone) {
		bases.moveDroneToServiceBay(base, drone);
	}

	public Iterator iteratorDrones() {
		return drones.iterator();
	}

	public void addSwarm(String swarmID, String baseID, String[] formingDronesIDS) {
		DroneCollection formingDrones = new DroneCollectionClass();
		Base b = getBase(baseID);
		int range = (int) Double.POSITIVE_INFINITY;
		int capacity = 0;
		for (int i = 0; i < formingDronesIDS.length; i++) {
			Drone d = drones.getDrone(formingDronesIDS[i]);
			formingDrones.addDrone(d);
			drones.removeDrone(formingDronesIDS[i]);
			b.removeDroneHangar(formingDronesIDS[i]);
			if (d.range() < range)
				range = d.range();
			capacity += d.capacity();
		}
		Drone s = new SwarmClass(swarmID, capacity, range, formingDrones);
		b.addDrone(s);
		drones.addDrone(s);
	}

	public Iterator swarmComponentsIterator(String swarmID) {
		Swarm s = (Swarm) drones.getDrone(swarmID);
		return s.getFormingDrones().iterator();
	}

	public Swarm getSwarm(String swarmID) {
		return (Swarm) drones.getDrone(swarmID);
	}

	public int[] swarmCheck(String baseID, String swarmID, String[] droneIDS) {
		int[] errorArray = new int[2];
		int swarmSameID = swarmSameID(droneIDS);
		if (swarmSameID != -1) {
			errorArray[0] = ERROR_SAME_DRONE;
			errorArray[1] = swarmSameID;
			return errorArray;
		} else {
			int swarmIsHermit = swarmIsHermit(droneIDS);
			if (swarmIsHermit != -1) {
				errorArray[0] = ERROR_HERMIT_DRONE;
				errorArray[1] = swarmIsHermit;
				return errorArray;
			} else {
				int swarmDroneUnavailable = swarmDroneUnavailable(droneIDS, baseID);
				if (swarmDroneUnavailable != -1) {
					errorArray[0] = ERROR_DRONE_UNAVAILABLE;
					errorArray[1] = swarmDroneUnavailable;
					return errorArray;
				} else {
					if (drones.existsId(swarmID)) {
						errorArray[0] = ERROR_SWARM_ID;
						errorArray[1] = -1;
						return errorArray;
					} else {
						errorArray[0] = SWARM_CHECK_OK;
						errorArray[1] = -1;
						return errorArray;
					}
				}
			}
		}
	}

	public boolean existsOrder(String orderID) {
		if (noOrders())
			return false;
		else
			return orders.existsOrder(orderID);
	}

	public boolean noOrders() {
		return orders.isEmpty();
	}

	public void addOrder(String baseID, String orderID, int dimension, int latitude, int longitude) {
		orders.addOrder(orderID, dimension, latitude, longitude);
		bases.getBase(baseID).addOrder(orderID, dimension, latitude, longitude);
	}

	public void disband(String swarmID, Base base) {
		Swarm swarm = getSwarm(swarmID);
		DroneCollection temp = swarm.getFormingDrones();
		Iterator it = temp.iterator();
		while (it.hasNext()) {
			Drone d = (Drone) it.next();
			base.addDrone(d);
			drones.addDrone(d);
		}
		base.removeDroneHangar(swarmID);
		drones.removeDrone(swarmID);
	}
	
	public void changeBase(String droneID, String originBase, String destinationBase) {
		Base b1 = getBase(originBase);
		Base b2 = getBase(destinationBase);
		Drone drone = drones.getDrone(droneID);
		b1.removeDroneHangar(droneID);
		b2.addDrone(drone);
	}

	/**
	 * Creates and adds a hermit drone to a given base and to the universe drone
	 * collection
	 * 
	 * @param id       - drone id
	 * @param base     - base to add drone to
	 * @param kind     - kind of drone
	 * @param range    - drone range
	 * @param capacity - drone capacity
	 */
	private void addHermitDrone(String id, String base, int range, int capacity) {
		Drone d = new HermitClass(id, capacity, range);
		drones.addDrone(d);
		addDroneToBase(d, base);
	}

	/**
	 * Creates and adds a sociable drone to a given base and to the universe drone
	 * collection
	 * 
	 * @param id       - drone id
	 * @param base     - base to add drone to
	 * @param kind     - kind of drone
	 * @param range    - drone range
	 * @param capacity - drone capacity
	 */
	private void addSociableDrone(String id, String base, int range, int capacity) {
		Drone d = new SociableClass(id, capacity, range);
		drones.addDrone(d);
		addDroneToBase(d, base);
	}

	/**
	 * Adds a given drone to a given base
	 * 
	 * @param drone - drone to be added
	 * @param base  - base id to add the drone to
	 */
	private void addDroneToBase(Drone drone, String base) {
		bases.getBase(base).addDrone(drone);
	}

	/**
	 * Checks if there is any drone IDs alike withing given droneIDs String array.
	 * 
	 * @param droneIDS - String array with inputed drone IDs.
	 * @return -1 if there are no drones with same IDs. If there are, returns the
	 *         index of repeated droneID in String array.
	 */
	private int swarmSameID(String[] droneIDS) {
		int sameDroneID = -1;
		for (int j = 0; j < droneIDS.length - 1 && sameDroneID == -1; j++) {
			for (int i = j + 1; i < droneIDS.length && sameDroneID == -1; i++) {
				if (droneIDS[j].equals(droneIDS[i]))
					sameDroneID = i;
			}
		}
		return sameDroneID;
	}

	/**
	 * Checks if there are any hermit drones in inputed droneIDs.
	 * 
	 * @param droneIDS - String array with inputed drone IDs.
	 * @return -1 if there are no hermit drones. If there are, returns the index of
	 *         hermit drone in String array.
	 */
	private int swarmIsHermit(String[] droneIDS) {
		int isHermit = -1;
		for (int i = 0; i < droneIDS.length - 1 && isHermit == -1; i++) {
			if (drones.getDrone(droneIDS[i]) instanceof Hermit)
				isHermit = i;
		}
		return isHermit;
	}

	/**
	 * Checks if there are any drones in other bases other than the inputed base.
	 * 
	 * @param droneIDS - String array with inputed drone IDs.
	 * @param baseID   - baseID of base to check if there are the inputed drones.
	 * @return -1 if there are no drones outside of inputed base. If there are,
	 *         returns the index of away drone in String array .
	 */
	private int swarmDroneUnavailable(String[] droneIDS, String baseID) {
		int unavailable = -1;
		Base b = getBase(baseID);
		if (b.noDronesInHangar())
			return unavailable;
		else {
			for (int i = 0; i < droneIDS.length - 1 && unavailable == -1; i++) {
				if (!b.isInHangar(droneIDS[i]))
					unavailable = i;
			}
			return unavailable;
		}
	}
}
