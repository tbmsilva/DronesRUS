package iterators;

/**
 * @author m.lami & tbmsilva
 *
 */
public interface Iterator {

	/**
	 * Initializes the iterator
	 */
	void initialize();

	/**
	 * Tests if there is more to iterate
	 * 
	 * @return <code>true</code> if there is more to iterate, <code>false</code>
	 *         otherwise
	 */
	boolean hasNext();
	
	/**
	 *  Returns the next object to iterate
	 * @return the next object to iterate
	 */
	Object next();

}
