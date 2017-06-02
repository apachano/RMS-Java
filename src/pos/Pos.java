package pos;

import java.io.File;
import java.io.FileNotFoundException;

public class Pos {
	static menu.Menu menu;
	
	public static void main(String[] args){
		File fingredients = new File("database/menu/ingredients.dat");
		File fitems = new File("database/menu/menuitems.dat");
		try {
			menu = new menu.Menu(fingredients,fitems);
		} catch (FileNotFoundException e) {
			System.out.println("Files could not be found, Check database!");
		}
	}
}