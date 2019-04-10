/**
 * 
 */
package orders;

import bases.*;

/**
 * @author tbmsilva & m.lami
 *
 */
public interface Order {
	
	/**
	 * Returns the order's id
	 * @return the order's id
	 */
	String id();
	
	/**
	 * Returns the order's destination
	 * @return the order's destination
	 */
	Location destination();
	
	/**
	 * Returns the order's dimension
	 * @return the order's dimension
	 */
	int dimension();
	
	/**
	 * Returns order info in String format
	 * @return order info in string format
	 */
	String info();
}
