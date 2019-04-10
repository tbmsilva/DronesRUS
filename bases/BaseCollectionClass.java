/**
 * 
 */
package bases;

import drones.Drone;
import iterators.*;

/**
 * @author tbmsilva & m.lami
 *
 */
public class BaseCollectionClass implements BaseCollection {

	private static final int BASE_SIZE_START = 10;
	private static final int GROWTH_FACTOR = 2;

	private Base[] bases;
	private int counter;

	public BaseCollectionClass() {
		bases = new Base[BASE_SIZE_START];
		counter = 0;
	}

	public void addBase(String id, Location location) {
		if (isFull())
			resize();
		Base b = new BaseClass(id, location);
		bases[counter++] = b;
	}

	public boolean noBases() {
		return counter == 0;
	}

	public boolean existsId(String id) {
		return searchIndexId(id) != -1;
	}

	public boolean existsLocation(Location location) {
		return searchIndexLocation(location) != -1;
	}

	public Iterator iterator() {
		return new IteratorBasesClass(bases, counter);
	}
	
	public Base getBase(String id) {
		return bases[searchIndexId(id)];
	}

	public boolean noDronesInHangar(String base) {
		return getBase(base).noDronesInHangar();
	}
	
	public boolean noDronesInServiceBay(String base) {
		return getBase(base).noDronesInServiceBay();
	}
	
	public void moveDroneToServiceBay(Base base,Drone drone) {
		bases[searchIndexId(base.baseID())].moveToServiceBay(drone);
	}
	
	private int searchIndexId(String id) {
		int i = 0;
		int res = -1;
		while ((i < counter) && (res == -1)) {
			if (bases[i].baseID().equals(id))
				res = i;
			i++;
		}
		return res;
	}

	private int searchIndexLocation(Location location) {
		int i = 0;
		int res = -1;
		while ((i < counter) && (res == -1)) {
			if (bases[i].location().latitude() == location.latitude()
					&& bases[i].location().longitude() == location.longitude())
				res = i;
			i++;
		}
		return res;
	}

	/**
	 * Doubles the base array size
	 */
	private boolean isFull() {
		return bases.length == counter;
	}

	/**
	 * Checks if base array is full
	 * 
	 * @return <code>true</code> if base array is full, <code>false</code> otherwise
	 */
	private void resize() {
		Base[] temp = new Base[bases.length * GROWTH_FACTOR];
		for (int i = 0; i <= bases.length; i++) {
			temp[i] = bases[i];
		}
		bases = temp;
	}

}
