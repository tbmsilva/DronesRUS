/**
 * 
 */
package orders;

import iterators.*;
import bases.*;

/**
 * @author tbmsilva & m.lami
 *
 */
public class OrderCollectionClass implements OrderCollection {

	private static final int ORDERS_SIZE_START = 10;
	private static final int GROWTH_FACTOR = 2;

	private Order[] orders;
	private int counter;

	public OrderCollectionClass() {
		orders = new OrderClass[ORDERS_SIZE_START];
		counter = 0;
	}

	public void addOrder(String id, int dimension, int latitude, int longitude) {
		if (isFull())
			resize();
		Location l = new LocationClass(latitude, longitude);
		orders[counter++] = new OrderClass(id, dimension, l);
	}

	public Iterator orderIterator() {
		return new IteratorOrdersClass(orders, counter);
	}

	public Order getOrder(String id) {
		return orders[searchIndex(id)];
	}
	
	public boolean existsOrder(String id) {
		return searchIndex(id) != -1;
	}
	
	public boolean isEmpty() {
		return counter == 0;
	}

	/**
	 * Doubles the orders array size
	 */
	private boolean isFull() {
		return orders.length == counter;
	}

	/**
	 * Checks if orders array is full
	 * 
	 * @return <code>true</code> if base array is full, <code>false</code> otherwise
	 */
	private void resize() {
		Order[] temp = new Order[orders.length * GROWTH_FACTOR];
		for (int i = 0; i <= orders.length; i++) {
			temp[i] = orders[i];
		}
		orders = temp;
	}

	/**
	 * Order search index
	 * 
	 * @param id - order id to be searched for
	 * @return index of order with matching id
	 */
	private int searchIndex(String id) {
		int i = 0;
		int res = -1;
		while ((i < counter) && (res == -1)) {
			if (orders[i].id().equals(id))
				res = i;
			i++;
		}
		return res;
	}
}
