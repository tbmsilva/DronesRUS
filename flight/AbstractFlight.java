/**
 * 
 */
package flight;

import bases.*;
import drones.*;

 /**
  * @author tbmsilva & m.lami
  */
public abstract class AbstractFlight implements Flight {
	
	public static final int DISTANCE_PER_TICK = 10;

	protected Base origin;
	private int distanceTraveled;
	private Drone drone;
	
	
	public AbstractFlight(Drone drone, Base origin) {
		this.drone = drone;
		this.origin = origin;
		distanceTraveled = 0;
	}
	
	public Drone drone() {
		return drone;
	}

	public abstract int distance();
	
	public Base origin() {
		return origin;
	}
	
	public void increaseDistanceTraveled() {
		distanceTraveled += 10;
		drone.removeRange();
	}
	
	public int distanceCovered() {
		return distanceTraveled;
	}
	
	public void setRange(int newRange) {
		drone.setRange(newRange);
	}
}
