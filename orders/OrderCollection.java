package orders;

import iterators.*;

public interface OrderCollection {

	/**
	 * Adds an order to the order collection
	 * 
	 * @param id        - order id
	 * @param dimension - order dimension
	 * @param latitude  - order destination latitude
	 * @param longitude - order destination longitude
	 */
	void addOrder(String id, int dimension, int latitude, int longitude);

	/**
	 * Returns an order iterator
	 * 
	 * @return an order iterator
	 */
	Iterator orderIterator();

	/**
	 * Returns an order by giving its id
	 * 
	 * @param id - order id to return
	 * @return order with given id
	 * @pre <code>existsOrder(id)</code>
	 */
	Order getOrder(String id);

	/**
	 * Checks if there is any order with the given id
	 * 
	 * @param id - id to be checked for existance
	 * @return <code>true</code> if there is an order with gievn id,
	 *         <code>false</code> otherwise
	 */
	boolean existsOrder(String id);
	
	/**
	 * Checks if there are no orders
	 * @return <code>true</code> if there are no orders, <code>false</code> otherwise.
	 */
	boolean isEmpty();
}
