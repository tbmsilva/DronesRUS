package orders;

import bases.*;

public interface OrderDelivered extends Order {

	/**
	 * Returns tick of when the order was delivered
	 * 
	 * @return tick of when the order was delivered
	 */
	int timeStamp();

	/**
	 * Sets the tick of delivery point
	 * 
	 * @param tick - tick of delivery
	 */
	void setTimeStamp(int tick);

	/**
	 * Returns the base of departure
	 * 
	 * @return the base of departure
	 */
	Base origin();

	/**
	 * Sets the base of departure
	 * 
	 * @param origin - base of departure
	 */
	void setOrigin(Base origin);

}
