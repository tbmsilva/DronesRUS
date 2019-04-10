/**
 * 
 */
package flight;

import bases.*;
import drones.*;

public abstract class AbstractFlight implements Flight {
	
	public static final int DISTANCE_PER_TICK = 10;

	protected Location origin, destination;
	private int distance, distanceTraveled;
	private Drone drone;
	
	
	public AbstractFlight(Drone drone, Location origin, Location destination) {
		this.drone = drone;
		this.origin = origin;
		this.destination = destination;
		distanceTraveled = 0;
	}
	
	public Drone drone() {
		return drone;
	}

	public abstract int distance();
	
	public Location origin() {
		return origin;
	}
	
	public Location destination() {
		return destination;
	}
	
	public int distanceTraveled(int tick) {
		return distanceTraveled = tick * DISTANCE_PER_TICK;
	}
}
