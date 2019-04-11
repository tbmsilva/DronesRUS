package orders;

import iterators.*;

public interface OrderCollection {

	/**
	 * Adds given order to order collection
	 * 
	 * @param order - order to be added
	 */
	void addOrder(Order order);

	/**
	 * Returns an order iterator
	 * 
	 * @return an order iterator
	 */
	Iterator orderIterator();

	/**
	 * Returns an order by giving its id
	 * 
	 * @param orderID - order id to return
	 * @return order with given id
	 * @pre <code>existsOrder(id)</code>
	 */
	Order getOrder(String orderID);

	/**
	 * Checks if there is any order with the given id
	 * 
	 * @param orderID - id to be checked for existance
	 * @return <code>true</code> if there is an order with gievn id,
	 *         <code>false</code> otherwise
	 */
	boolean existsOrder(String orderID);

	/**
	 * Checks if there are no orders
	 * 
	 * @return <code>true</code> if there are no orders, <code>false</code>
	 *         otherwise.
	 */
	boolean isEmpty();

	/**
	 * Removes an order by giving its ID
	 * 
	 * @param orderID - order's ID
	 * @pre <code>existsOrder(orderID)</code>
	 */
	void removeOrder(String orderID);
}
