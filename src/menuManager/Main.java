package menuManager;

import java.io.File;
import java.io.FileNotFoundException;

import menu.Ingredient;
import menu.Menu;

public class Main {
	static File database = new File("database");
	static File fingredients = new File(database + "/menu/ingredients.dat");
	static File fitems = new File(database + "/menu/menuitems.dat");
	static File fpos = new File(database + "/pos/menu.dat");
	private static Menu menu;
	
	public static void main(String[] args) {
		System.out.println("Menu manager V. 2017.6.3");
		load();
	}
	public static void load(){
		try{
			menu = new Menu(fingredients, fitems);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void create(){
		menu = new Menu();
	}
	public static void save() throws Exception {
		menu.writeOut(fingredients, fitems);
	}
	public static void insertIngredient(int id, String name, Boolean active, int charge){
		menu.ingredient[id] = new Ingredient(name, active, charge);
	}
	public static void removeIngredient(int id){
		menu.ingredient[id] = null;
	}

}
