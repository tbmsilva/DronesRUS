/**
 * 
 */
package flight;

import orders.*;

/**
 * @author tbmsilva
 *
 */
public interface Delivery extends Flight {
	
	/**
	 * Returns the distance of the delivery
	 * 
	 * @return the distance of the delivery
	 */
	int distance();
	
	/**
	 * Returns the order to deliver
	 * 
	 * @return the order to deliver
	 */
	Order getOrder();

}
