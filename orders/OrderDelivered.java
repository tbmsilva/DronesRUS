package orders;

import bases.*;

public interface OrderDelivered extends Order {

	int timeStamp();
	
	void setTimeStamp(int tick);
	
	Base origin();
	
	void setOrigin(Base origin);

}
