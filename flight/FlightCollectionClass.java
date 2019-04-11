/**
 * 
 */
package flight;

import iterators.*;
import orders.*;

/**
 * @author tbmsilva & m.lami
 *
 */
public class FlightCollectionClass implements FlightCollection {

	private static final int FLIGHT_SIZE_START = 10;
	private static final int GROWTH_FACTOR = 2;

	private Flight[] flights;
	private OrderCollection deliveredOrders;
	private int counter;

	public FlightCollectionClass() {
		flights = new Flight[FLIGHT_SIZE_START];
		deliveredOrders = new OrderCollectionClass();
		counter = 0;
	}

	public void addFlight(Flight flight) {
		if (isFull())
			resize();
		flights[counter++] = flight;
	}

	public Flight getFlight(String droneID) {
		return flights[searchIndexId(droneID)];
	}

	public Iterator iterator() {
		return new IteratorFlightsClass(flights, counter);
	}

	public boolean isEmpty() {
		return counter == 0;
	}

	public Iterator orderIterator() {
		return deliveredOrders.orderIterator();
	}

	public boolean noOrderDelivered() {
		return deliveredOrders.isEmpty();
	}

	public void removeFlight(String droneID) {
		removeAt(searchIndexId(droneID));
	}
	
	public void deliverOrder(Order order) {
		deliveredOrders.addOrder(order);
	}

	/**
	 * Search index by ID of flights
	 * 
	 * @param droneID - flight of drone's id to be searched for
	 * @return index position of flight with given droneID, unless there is no drone
	 *         with that ID currently flying, in that case, it returns -1
	 */
	private int searchIndexId(String droneID) {
		int i = 0;
		int res = -1;
		while ((i < counter) && (res == -1)) {
			if (flights[i].drone().droneID().equals(droneID))
				res = i;
			i++;
		}
		return res;
	}

	/**
	 * Doubles the flight array size
	 */
	private boolean isFull() {
		return flights.length == counter;
	}

	/**
	 * Checks if flight array is full
	 * 
	 * @return <code>true</code> if flight array is full, <code>false</code>
	 *         otherwise
	 */
	private void resize() {
		Flight[] temp = new Flight[flights.length * GROWTH_FACTOR];
		for (int i = 0; i <= flights.length; i++) {
			temp[i] = flights[i];
		}
		flights = temp;
	}

	/**
	 * Removes the flight in the given index
	 * 
	 * @param index - index of flight to be removed
	 * @pre <code>index >= 0 && index <= counter </code>
	 */
	private void removeAt(int index) {
		for (int i = index; i < counter; i++) {
			flights[i] = flights[i + 1];
		}
		counter--;
	}
}
