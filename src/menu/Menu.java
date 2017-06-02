package menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Menu {
	Ingredient ingredient[];
	Item item[];
	static int i;
	static Scanner file;
	static String buffer;
	
	public Menu(File fingredients, File fitems) throws FileNotFoundException{
		//Scan in the ingredients
		System.out.println("Loading Ingredients");
		file = new Scanner(new FileReader(fingredients));
		ingredient = new Ingredient[1000000];
		buffer = file.nextLine();
		while(! buffer.equals("terminate")){
			i = Integer.parseInt(buffer.substring(0,6));
			ingredient[i] =  new Ingredient(buffer);
			buffer = file.nextLine();
		}
		
		//Scan in the Menu Items
		System.out.println("Loading menu items");
		file = new Scanner(new FileReader(fitems));
		item = new Item[1000000];
		buffer = file.nextLine();
		while(! buffer.equals("terminate")){
			item[i] = new Item(buffer);
			buffer = file.nextLine();
			check(i);
		}
		System.out.println("Menu Loaded");
	}
	public boolean check(int id){
		//TODO add code to check items active flag against ingredients.
		return true;
	}
}
