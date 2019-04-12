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
	
	/**
	 * Returns the distance of the relocation
	 * 
	 * @return the distance of the relocation
	 */
	int distance();
	
	
	Base destination();

}
