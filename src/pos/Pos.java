package pos;

import java.io.File;
import java.io.FileNotFoundException;

public class Pos {
	static menu.Menu menu;
	static order.Order order;
	
	public static void main(String[] args){
		
		//Load the menu from the database
		loadMenu();
		
		System.out.println(menu.getItem(1));
		
		
	}
	public static void loadMenu(){
		File fingredients = new File("database/menu/ingredients.dat");
		File fitems = new File("database/menu/menuitems.dat");
		try {
			menu = new menu.Menu(fingredients,fitems);
		} catch (FileNotFoundException e) {
			System.out.println("Files could not be found, Check the database!");
		}
		try {
			menu.writeOut(new File("database2/menu/ingredients.dat"), new File("database2/menu/menuitems.dat"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void add(int item, int quantity){
		if(! menu.item[item].active())return; //Check to see if the menu item is active.
		
		order.Item oitem = new order.Item(item, menu);
		if(quantity == -1)oitem.quantity = 1;
		else oitem.quantity = quantity;
		order.items.add(oitem);
	}
	
	
}