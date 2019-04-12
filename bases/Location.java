/**
 * 
 */
package bases;

/**
 * @author m.lami & tbmsilva
 *
 */
public interface Location {

	/**
	 * Returns the latitude
	 * 
	 * @return the latitude
	 */
	int latitude();

	/**
	 * Returns the longitude
	 * 
	 * @return the longitude
	 */
	int longitude();

	/**
	 * Returns coordinates String
	 * 
	 * @return coordinates String
	 */
	String coordinates();

	/**
	 * Calculates the distance between two locations for a relocation
	 * 
	 * @param locationB the other point to calculate the distance
	 * @return the distance between the two locations
	 */
	int distanceToRelocation(Location locationB);


	/**
	 * Calculates the distance between two locations for a delivery
	 * 
	 * @param locationB the other point to calculate the distance
	 * @return the distance between the two locations
	 */
	int distanceToDelivery(Location locationB);
	
}
