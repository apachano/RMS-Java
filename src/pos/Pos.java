package pos;

import java.io.File;
import java.io.FileNotFoundException;

import order.Order;

public class Pos {
	static menu.Menu menu;
	static order.Order order;
	static int i;
	
	public static void main(String[] args){
		
		//Load the menu from the database
		loadMenu();
		add(1,1);
		add(1,2);
		add(2,3);
		add(3,1);
		add(2,-1);
		System.out.println(Order.getOrder());
		
	}
	
	public static void loadMenu(){
		File fingredients = new File("database/menu/ingredients.dat");
		File fitems = new File("database/menu/menuitems.dat");
		try {
			menu = new menu.Menu(fingredients,fitems);
		} catch (FileNotFoundException e) {
			System.out.println("Files could not be found, Check the database!");
		}
	}
	
	public static void add(int item, int quantity){
		
		System.out.println(item + quantity);
		if(order == null)order = new Order(null);
		
		if(! menu.item[item].active())return; //Check to see if the menu item is active.
		
		for(i=0;i < Order.items.size();i++){//If the item is already in the list
			if(Order.items.get(i).id == item){
				if(quantity == 0)Order.items.remove(i);
				else if(quantity == -1)Order.items.get(i).quantity++;
				else Order.items.get(i).quantity = quantity;
				return;
			}
		}
		
		//If the item is not in the list add it to the list
		order.Item oitem = new order.Item(item, menu);
		if(quantity == -1)oitem.quantity = 1;
		else oitem.quantity = quantity;
		Order.items.add(oitem);
	}
	
	public static void remove(int item){
		add(item,0);
	}
}