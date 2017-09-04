package order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

public class Order {
	core.Register register;
	Instant init;
	public static List<Item> items;
	static String buffer;
	static int i;
	
	public Order(core.Register register){
		init = Instant.now();
		items = new LinkedList<Item>();
	}
	
	/**
	 * Returns a string representation of the status of the order
	 * @return
	 */
	
	public static String getOrder(){
		buffer = "";
		BigDecimal total = BigDecimal.valueOf(0);
		for(i=0;i < items.size();i++){
			String name = items.get(i).name;
			
			//Format price of item
			BigDecimal price = BigDecimal.valueOf(items.get(i).price);
			price = BigDecimal.valueOf(items.get(i).quantity).multiply(price);
			String Price = String.valueOf(price);
			
			//Format item name
			while(name.length() < (25 - Price.length()))name = name + " ";
			String quant = String.valueOf(items.get(i).quantity);
			
			//Format Item Quantity
			while(quant.length() < 3)quant = " " + quant;
			
			//Update the buffer
			buffer = buffer + quant + "|" + name + "$" + Price + "\n";
			//Update the total
			total = total.add(price);
		}
		buffer = buffer + "\nTotal           $" + total.setScale(2,4) + "\n";
		if(core.Register.tax > 0){
			BigDecimal tax = total.multiply(BigDecimal.valueOf(core.Register.tax));
			buffer = buffer + "Tax             $" + tax.setScale(2, 4) + "\n";
			total = total.add(tax);
			buffer = buffer + "Total After Tax $" + total.setScale(2, 4) + "\n";
		}
		return buffer;
	}
}
