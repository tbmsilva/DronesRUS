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
	
	int distance();
	
	Order getOrder();

}
