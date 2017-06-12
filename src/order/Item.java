package order;

import menu.Menu;

public class Item {
	String name;
	public int quantity;
	
	public Item(int item, Menu menu){
		name = menu.item[item].getName();
	}
}
