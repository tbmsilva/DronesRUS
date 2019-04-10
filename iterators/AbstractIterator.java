/**
 * 
 */
package iterators;

/**
 * @author m.lami & tbmsilva
 *
 */
public abstract class AbstractIterator implements Iterator {
	private int current, counter;
	private Object[] objects;

	/**
	 * 
	 */
	public AbstractIterator(Object[] objects, int counter) {
		// TODO Auto-generated constructor stub
		this.objects = objects;
		this.counter = counter;
		current = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iterators.Iterator#initialize()
	 */
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		current = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iterators.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return current < counter;
	}

	public Object next() {
		return objects[current++];
	}

}
