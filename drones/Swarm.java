/**
 * 
 */
package drones;

/**
 * @author tbmsilva
 *
 */
public interface Swarm extends Drone{
	
	/**
	 * Gives access to forming drones drone collection
	 * @return drone collection of forming drones
	 */
	DroneCollection getFormingDrones();
}
