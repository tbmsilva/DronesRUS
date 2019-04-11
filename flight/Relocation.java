/**
 * 
 */
package flight;

import bases.Base;

/**
 * @author tbmsilva
 *
 */
public interface Relocation extends Flight {
	
	int distance();
	
	
	Base destination();

}
