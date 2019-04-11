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
	private DroneCollection serviceBay, hangar;
	private OrderCollection orders;

	public BaseClass(String id, Location location) {
		this.id = id;
		this.location = location;
		hangar = new DroneCollectionClass();
		serviceBay = new DroneCollectionClass();
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
		return serviceBay.isEmpty();
	}

	public boolean noDronesInHangar() {
		return hangar.isEmpty();
	}

	public boolean isInHangar(String droneID) {
		return hangar.existsId(droneID);
	}
	
	public void removeDroneHangar(String droneID ) {
		hangar.removeDrone(droneID);
	}
	
	public Iterator droneIteratorHangar() {
		return hangar.iterator();
	}

	public Iterator droneIteratorServiceBay() {
		return serviceBay.iterator();
	}
	
	public void addOrder(String orderID, int dimension, int latitude, int longitude) {
		orders.addOrder(orderID, dimension, latitude, longitude);
	}
	
	public Iterator orderIterator() {
		return orders.orderIterator();
	}
	
	public void moveToServiceBay(Drone drone) {
		removeDroneHangar(drone.droneID());
		serviceBay.addDrone(drone);
	}
	
	public Drone getDrone(String droneID) {
		return hangar.getDrone(droneID);
	}
}
