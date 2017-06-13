package order;

import menu.Menu;

public class Item {
	public double price;
	String name;
	public int id;
	public int quantity;
	int voids;
	
	public Item(int item, Menu menu){
		name = menu.item[item].getName();
		price = menu.item[item].getPrice();
		price = price / 100;
		id = item;
	}
}
