/**
 * 
 */
package bases;

/**
 * @author m.lami & tbmsilva
 *
 */
public class LocationClass implements Location {
	private int latitude, longitude;
	private String coordinates;

	public LocationClass(int latitude, int longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		coordinates = "[" + latitude + "," + longitude + "]";
	}

	public int latitude() {
		return latitude;
	}

	public int longitude() {
		return longitude;
	}

	public String coordinates() {
		return coordinates;
	}

	public int calculateDistance(Location locationB) {
		int a = Math.abs(latitude - locationB.latitude());
		int b = Math.abs(longitude - locationB.longitude());
		return (int) Math.ceil(Math.sqrt(a*a + b*b));
	}

}
