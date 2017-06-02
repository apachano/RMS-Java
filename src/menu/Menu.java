package menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Menu {
	Ingredient ingredient[];
	Ingredient temp;
	Item item[];
	static int i;
	static Scanner file;
	static String buffer;
	
	public Menu(File fingredients, File fitems) throws FileNotFoundException{
		//Scan in the ingredients
		file = new Scanner(new FileReader(fingredients));
		buffer = file.nextLine();
		while(buffer != "terminate"){
			i = Integer.parseInt(buffer.substring(0,6));
			System.out.println(i);			
			System.out.println(buffer);
			temp =  new Ingredient(buffer);
			System.out.println(temp);
			ingredient[i] = temp;
			System.out.println("?");
			buffer = file.nextLine();
		}
		
		//Scan in the Menu Items
		file = new Scanner(new FileReader(fitems));
		buffer = file.nextLine();
		while(buffer != "terminate"){
			item[i] = new Item(buffer);
			buffer = file.nextLine();
			check(i);
		}
	}
	public boolean check(int id){
		//TODO add code to check items active flag against ingredients.
		return true;
	}
}
