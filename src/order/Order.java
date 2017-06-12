package order;

import java.time.Instant;
import java.util.List;

public class Order {
	core.Register register;
	Instant init;
	public List<Item> items;
	
	Order(core.Register register){
		init = Instant.now();
		System.out.println(init);
	}
	
	public static String getOrder(){
		return null;
	}
}
