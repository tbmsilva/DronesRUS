/**
 * 
 */
package flight;

import iterators.*;

/**
 * @author tbmsilva & m.lami
 *
 */
public class FlightCollectionClass implements FlightCollection {

	private static final int FLIGHT_SIZE_START = 10;
	private static final int GROWTH_FACTOR = 2;

	private Flight[] flights;
	private int counter;

	public FlightCollectionClass() {
		flights = new Flight[FLIGHT_SIZE_START];
		counter = 0;
	}

	@Override
	public void addFlight(Flight flight) {
		if (isFull())
			resize();
		flights[counter++] = flight;
	}

	@Override
	public Flight getFlight(String droneID) {
		return flights[searchIndexId(droneID)];
	}

	@Override
	public Iterator iterator() {
		return new IteratorFlightsClass(flights, counter);
	}

	private int searchIndexId(String id) {
		int i = 0;
		int res = -1;
		while ((i < counter) && (res == -1)) {
			if (flights[i].drone().droneID().equals(id))
				res = i;
			i++;
		}
		return res;
	}

	/**
	 * Doubles the base array size
	 */
	private boolean isFull() {
		return flights.length == counter;
	}

	/**
	 * Checks if base array is full
	 * 
	 * @return <code>true</code> if base array is full, <code>false</code> otherwise
	 */
	private void resize() {
		Flight[] temp = new Flight[flights.length * GROWTH_FACTOR];
		for (int i = 0; i <= flights.length; i++) {
			temp[i] = flights[i];
		}
		flights = temp;
	}

}
