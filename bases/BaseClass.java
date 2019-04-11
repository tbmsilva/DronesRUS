/**
 * 
 */
package bases;

import drones.*;
import iterators.Iterator;
import orders.*;

/**
 * @author tbmsilva & m.lami
 *
 */
public class BaseClass implements Base {

	private String id;
	private Location location;
	private DroneCollection[] serviceBay;
	private DroneCollection hangar;
	private OrderCollection orders;

	private static final int TICKS_PER_SERVICE = 3;

	public BaseClass(String id, Location location) {
		this.id = id;
		this.location = location;
		hangar = new DroneCollectionClass();
		serviceBay = new DroneCollectionClass[TICKS_PER_SERVICE];
		for (int i = 0; i < TICKS_PER_SERVICE; i++)
			serviceBay[i] = new DroneCollectionClass();
		orders = new OrderCollectionClass();
	}

	public String baseID() {
		return id;
	}

	public Location location() {
		return location;
	}

	public String coordinates() {
		return location.coordinates();
	}

	public void addDrone(Drone drone) {
		hangar.addDrone(drone);
	}

	public boolean noDronesInServiceBay() {
		return serviceBay[0].isEmpty() && serviceBay[1].isEmpty() && serviceBay[2].isEmpty();
	}

	public boolean noDronesInHangar() {
		return hangar.isEmpty();
	}

	public boolean isInHangar(String droneID) {
		return hangar.existsId(droneID);
	}

	public void removeDroneHangar(String droneID) {
		hangar.removeDrone(droneID);
	}

	public Iterator droneIteratorHangar() {
		return hangar.iterator();
	}

	public Iterator droneIteratorServiceBay() {
		DroneCollection temp = new DroneCollectionClass();
		for (int i = 0; i < 3; i++) {
			Iterator it = serviceBay[i].iterator();
			while (it.hasNext())
				temp.addDrone((Drone) it.next());
		}
		return temp.iterator();
	}

	public void addOrder(Order order) {
		orders.addOrder(order);
	}

	public Iterator orderIterator() {
		return orders.orderIterator();
	}

	public void moveToServiceBay(Drone drone) {
		serviceBay[0].addDrone(drone);
		removeDroneHangar(drone.droneID());
	}

	public Drone getDrone(String droneID) {
		return hangar.getDrone(droneID);
	}

	public void removeOrder(String orderID) {
		orders.removeOrder(orderID);
	}

	public void tickService() {
		if (!serviceBay[2].isEmpty()) {
			Iterator it1 = serviceBay[2].iterator();
			while (it1.hasNext()) {
				Drone d1 = (Drone) it1.next();
				d1.maxRange();
				hangar.addDrone(d1);
				Iterator it2 = serviceBay[0].iterator();
				while (it2.hasNext()) {
					Drone d2 = (Drone) it2.next();
					serviceBay[2].removeDrone(d2.droneID());
				}
			}
		}
		serviceBay[2] = serviceBay[1];
		serviceBay[1] = serviceBay[0];

	}
}
